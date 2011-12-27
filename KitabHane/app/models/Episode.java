package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Episode extends Model {
    
	public String title;
    
    public Integer episodeNumber;
    
    public Date airDate;
    
    public Float rating;
    
    public String screenCap;
    
    public Long seasonID;
    
    public Episode(String title, Integer number, Date airDate, Float rating,Long seasonID) {
    	this.title = title;
    	this.episodeNumber = number;
    	this.airDate = airDate;
    	this.rating = rating;
    	this.seasonID = seasonID;
	}
}
