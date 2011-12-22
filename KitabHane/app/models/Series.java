package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Series extends Model {
	@Column(unique = true) 
	public String seriesID;
	
    public String name;
	
	public String startDate;
	
	public String content;
	
	public Integer country;
	
	public Integer totalSeasons;
	
	public String banner;
	
	public String genres;
	
	public String status;
	
	public Series(String seriesID,String name, String startDate,Integer totalSeasons,String genres,Integer country) {
		this.seriesID = seriesID;
		this.name = name;
		this.startDate = startDate;
		this.totalSeasons = totalSeasons;
		this.genres = genres;
	}
	
		

	
}
