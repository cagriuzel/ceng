package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Season extends Model {
    
	public Integer seasonNo;
	public Long seriesID;
	
	public Season(Integer seasonNo, Long seriesID) {
		this.seasonNo = seasonNo;
		this.seriesID = seriesID;
	}
}
