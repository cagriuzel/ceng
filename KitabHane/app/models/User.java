package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class User extends Model {
	
	@Column(unique = true) 
	public String facebookID;
	public String name;
	public String profilePic; 
	
	
	public User(String facebookID) {
		this.facebookID = facebookID;
	}
	
	public User(String facebookID,String name,String picture) {
		this(facebookID);
		this.name = name;
		this.profilePic = picture;
	}
	
	public static User getByFacebookID(String facebookID) {
		return User.find("facebookID = ? ", facebookID).first();
	}
	
	public String getFacebookID() {
		return facebookID;
	}
}
