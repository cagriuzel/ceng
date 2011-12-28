package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Episode extends Model {
    
	public String title;
    
	/*
	 * O sezonun kaçıncı bölümü olduğu
	 */
    public Integer seasonNumber;
    
    /*
     * Serinin kaçıncı bölümü olduğu
     */
    public Integer episodeNumber;
    
    public Date airDate;
    
    public Float rating;
    
    public String screenCap;
    
    public Long seasonID;
    
    public Episode(String title, Integer seasonNumber,Integer episodeNumber,Date airDate, Float rating,Long seasonID) {
    	this.title = title;
    	this.seasonNumber = seasonNumber;
    	this.episodeNumber = episodeNumber;
    	this.airDate = airDate;
    	this.rating = rating;
    	this.seasonID = seasonID;
	}
}
