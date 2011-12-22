package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Actor extends Model {
    public String name;
    public String picturePath;
    
    public Actor() {
    	
	}
}
