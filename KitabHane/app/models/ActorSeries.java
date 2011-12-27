package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class ActorSeries extends Model {
    
	public Long actorID;
	
	public Long seriesID;
	
	public String nickName;
	
}
