package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Series extends Model {
	@Column(unique = true) 
	public String seriesID;
	
    public String name;
	
	public Integer startDate;
	
	public Integer endDate;
	
	@Column(length=3000)
	public String content;
	
	public String country;
	
	public Integer totalSeasons;
	
	public String banner;
	
	public String smallBanner;
	
	public String status;
	
	public Float rating;
	
	public BigDecimal vote; 
	
	public Series(String seriesID,String name, Integer startDate,Integer totalSeasons,String country,String status) {
		this.seriesID = seriesID;
		this.name = name;
		this.startDate = startDate;
		this.totalSeasons = totalSeasons;
		this.country = country;
		this.status = status;
	}
	
		

	
}
