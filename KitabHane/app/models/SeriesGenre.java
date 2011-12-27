package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class SeriesGenre extends Model {
	public Long SeriesID;
	public Long GenreID;

	public SeriesGenre() {
		// TODO Auto-generated constructor stub
	}
}
