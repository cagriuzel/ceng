package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserSeries extends Model {
    
	public Long UserID;
	public Long SeriesID;
	
	public UserSeries(Long userID, Long seriesID) {
		this.UserID = userID;
		this.SeriesID = seriesID;
	}
}
