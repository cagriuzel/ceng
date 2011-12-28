package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Genre extends Model {
	@Column(unique = true)
	public String genreName;

	public Genre(String genreName) {
		this.genreName = genreName;
	}

}
