package common;

public class FBRequestInfo {
	public String algorithm = null;
	public String oauth_token = null;
	public String user_id = null;
	public long expires = -1;
	public long issued_at = -1;
	public User user = null;
	
	public FBRequestInfo() {
	}
	
	public static class User {
		public String locale;
		public String country;
		
		
		public User() {}
	}
}