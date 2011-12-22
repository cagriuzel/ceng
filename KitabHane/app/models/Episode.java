package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Episode extends Model {
    
	public String title;
    
    public Integer number;
    
    public String airDate;
    
    public String rating;
    
    public Long seasonID;
    
    public Episode(String title, Integer number, String airDate, String rating,Long seasonID) {
    	this.title = title;
    	this.number = number;
    	this.airDate = airDate;
    	this.rating = rating;
    	this.seasonID = seasonID;
	}
}
