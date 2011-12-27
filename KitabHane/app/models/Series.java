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
	
	public Date startDate;
	
	public Date endDate;
	
	@Column(length=3000)
	public String content;
	
	public String country;
	
	public Integer totalSeasons;
	
	public String banner;
	
	public String smallBanner;
	
	public String status;
	
	public Float rating;
	
	public BigDecimal vote; 
	
	public Series(String seriesID,String name, Date startDate,Integer totalSeasons,Integer country) {
		this.seriesID = seriesID;
		this.name = name;
		this.startDate = startDate;
		this.totalSeasons = totalSeasons;
	}
	
		

	
}
