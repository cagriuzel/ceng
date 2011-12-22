package common;

import java.net.URLEncoder;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import play.Logger;
import play.Play;
import play.libs.Codec;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.WS.WSRequest;

public class FBUtil {
  private static final String FB_APP_SECRET = Play.configuration.getProperty("fb.app.secret");
  private static final String FB_APP_ID = Play.configuration.getProperty("fb.app.id");
  private static final String FB_GRAPH_API_URL = "https://graph.facebook.com/";
  private static FBUtil __INSTANCE = new FBUtil();
  
  private Gson json = new Gson();
  private JsonParser jsonParser = new JsonParser();
  private Mac mac = null;

  private FBUtil() {
    try {
      mac = Mac.getInstance("HMACSHA256");
      mac.init(new SecretKeySpec(FB_APP_SECRET.getBytes(), mac.getAlgorithm()));
    } catch (Exception exc) {
      Logger.error(exc, "Mac initiation failed for HMACSHA256");
    }
  }

  public static FBUtil getInstance() {
    return __INSTANCE;
  }
	
	public static boolean isBlank(String s) {
		return (s == null) || (s.length() == 0) || (s.trim().length() == 0);
	}
	
  public FBRequestInfo getUserInfoFromSignedRequest(String signedRequest) {
    String[] signedRequestParts = signedRequest.split("\\.");

    if (FBUtil.isBlank(signedRequestParts[0]) || (FBUtil.isBlank(signedRequestParts[0]))) {
      Logger.warn("Received invalid signed_request from fb: %s", signedRequest);
      return null;
    }

    byte[] decodedSignature = null;
    String decodedPayload = null;
    FBRequestInfo returnVal = null;

    try {
      decodedSignature = Codec.decodeBASE64(signedRequestParts[0]);
      byte[] calculatedSignature = mac.doFinal(signedRequestParts[1].getBytes());

      if (!Arrays.equals(decodedSignature, calculatedSignature)) {
        Logger.warn("Invalid signature received from facebook: %s", signedRequest);
        return null;
      }

      decodedPayload = new String(Codec.decodeBASE64(signedRequestParts[1]), "utf-8");
      returnVal = json.fromJson(decodedPayload, FBRequestInfo.class);
      if (returnVal.user == null) {
        Logger.warn("Received empty data from facebook: %s", signedRequest);
        return null;
      }

      return returnVal;
    } catch (Exception exc) {
      Logger.warn(exc, "Failed base64-decoding facebook payload: %s", signedRequest);
    }

    return null;
  }
  
  public JsonElement executeGraphRequest(String requestRelativeURL, String oauthToken) {
    WSRequest request = WS.url(FB_GRAPH_API_URL + requestRelativeURL);
    request.setParameter("access_token", WS.encode(oauthToken));
    HttpResponse response = request.get();
    if (response.getStatus() != 200) {
    	return null;
    }
    
    return response.getJson();
  }
  
  public String getUserAuthorizationURL(String returnURL, String permissionList) {
    try {
      return "https://www.facebook.com/dialog/oauth?client_id=" + FB_APP_ID + 
             "&redirect_uri=" + URLEncoder.encode(returnURL, "utf-8") + 
             (isBlank(permissionList) ? "" : "&scope=" + URLEncoder.encode(permissionList, "utf-8"));
    } catch (Exception exc) {
      Logger.error(exc, "URL encoding failed for canvas URL: %s, scope: %s", returnURL, permissionList);
    }
    return null;
  }


}
