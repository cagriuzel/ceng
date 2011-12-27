package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class WriterEpisode extends Model {
	
	public Long writerID;
	
	public Long episodeID;
    
}
