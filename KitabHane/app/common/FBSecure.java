package common;

import models.User;
import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;

public class FBSecure extends Controller {
  private final static String FB_DEFAULT_PERMISSION_LIST = Play.configuration.getProperty("fb.permission.list");
  private final static String APPLICATION_CANVAS_ROOT_URL_FOR_FACEBOOK = Play.configuration.getProperty("fb.canvas.url");
  private final static String FACEBOOK_APPLICATION_URL = Play.configuration.getProperty("fb.base.url");
  private final static String SESSION_DURATION = "1h";

  @Before
  static void checkAccess() {
    String error = params.get("error");
    if ((!FBUtil.isBlank(error)) && (error.equals("access_denied"))) {
      notFound("FB_REQUEST_ACCESS_DENIED_BY_USER");
    }
    
    String signedRequest = params.get("signed_request");
    
    if ((FBUtil.isBlank(signedRequest)) && (!FBUtil.isBlank(params.get("code")))) {
      /*
       * This is a complete hack: Facebook requires you to:
       *   -- Get permission authorization: If user hits Allow, it will call you with a request parameter: code
       *   -- You are supposed to get this code, make another request to get oauth token, and another request to get user info
       *   
       *    -- But, at the point when you get the code and permissions, if you reload the app,
       *       it just sends you a signed_request parameter which has everything for you;)
       *       So, a simple refresh here works fine for now! 
       */
    	
    	// [CLASS COMMENT]: What happens when user tries to hit a partial URL?
    	sendTOPRedirect(FACEBOOK_APPLICATION_URL);
    }
    
    User currentUser = null;

    if (!FBUtil.isBlank(signedRequest)) {
      FBRequestInfo fbRequestInfo = FBUtil.getInstance().getUserInfoFromSignedRequest(signedRequest);
      if (fbRequestInfo == null) {
        notFound("FB_REQUEST_INFO_NOT_FOUND");
      }

      if (FBUtil.isBlank(fbRequestInfo.oauth_token)) {
      	sendTOPRedirect(FBUtil.getInstance().getUserAuthorizationURL(APPLICATION_CANVAS_ROOT_URL_FOR_FACEBOOK, FB_DEFAULT_PERMISSION_LIST));
      }

      if (FBUtil.isBlank(fbRequestInfo.user_id)) {
        Logger.warn("Empty facebook userid received from FB request with oauth_token: %s, signed request: %s", fbRequestInfo.oauth_token, signedRequest);
        notFound("FB_REQUEST_INFO_NOT_FOUND");
      }

      currentUser = User.getByFacebookID(fbRequestInfo.user_id);
      if (currentUser == null) {
        currentUser = new User(fbRequestInfo.user_id);
        currentUser.save();
      }

      Cache.set("app.SESSION_" + session.getId(), currentUser.facebookID, SESSION_DURATION);
      Cache.set("app.OAUTH_" + currentUser.facebookID, fbRequestInfo.oauth_token, SESSION_DURATION);
      request.args.put("user.obj", currentUser);
      return;
    }

    String sessionID = session.getId();
    if (FBUtil.isBlank(sessionID)) {
    	sendTOPRedirect(FBUtil.getInstance().getUserAuthorizationURL(APPLICATION_CANVAS_ROOT_URL_FOR_FACEBOOK, FB_DEFAULT_PERMISSION_LIST));
    }
    
    String facebookID = Cache.get("app.SESSION_" + sessionID, String.class);
    String oauthToken = Cache.get("app.OAUTH_" + facebookID, String.class);

    if (!FBUtil.isBlank(facebookID)) {
      currentUser = User.getByFacebookID(facebookID);
    }

    if ((currentUser == null) || (FBUtil.isBlank(oauthToken))) {
    	sendTOPRedirect(FBUtil.getInstance().getUserAuthorizationURL(APPLICATION_CANVAS_ROOT_URL_FOR_FACEBOOK, FB_DEFAULT_PERMISSION_LIST));
    }

    Cache.set("app.SESSION_" + sessionID, facebookID, SESSION_DURATION);
    request.args.put("user.obj", currentUser);
  }

  
  private static void sendTOPRedirect(String url) {
  	renderHtml("<script type=\"text/javascript\">window.top.location='" + url + "';</script>");
  }
}
