package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DirectorEpisode extends Model {
    
	public Long directorID;
	
	public Long episodeID;
	
	public DirectorEpisode() {
		// TODO Auto-generated constructor stub
	}
}
