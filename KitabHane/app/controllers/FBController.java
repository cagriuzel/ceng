package controllers;

import models.User;
import play.cache.Cache;
import play.mvc.Controller;

public class FBController extends Controller {
	protected static User getUser() {
		return (User)request.args.get("user.obj");
	}

	protected static String getOAuthToken() {
		User user = getUser();
		return Cache.get("app.OAUTH_" + user.getFacebookID(), String.class);
	}
}
