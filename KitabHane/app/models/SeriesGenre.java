package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class SeriesGenre extends Model {
	public Long seriesID;
	public Long genreID;

	public SeriesGenre(Long seriesID, Long genreID) {
		this.seriesID = seriesID;
		this.genreID = genreID;
	}
}
