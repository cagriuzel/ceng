package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Director extends Model {

	public String name;

	public Director() {
		// TODO Auto-generated constructor stub
	}
}
