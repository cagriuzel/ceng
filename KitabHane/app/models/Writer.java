package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Writer extends Model {

	public String name;

	public Writer() {
		// TODO Auto-generated constructor stub
	}
}
