package ch.iso.m120.model;

import java.util.ArrayList;
import java.util.HashMap;
import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person extends DatabaseObject{

	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleStringProperty name = new SimpleStringProperty();
	public SimpleStringProperty email = new SimpleStringProperty();

	public Person(Integer id, String name, String email) {
		super();
		this.id.set(id);
		this.name.set(name);
		this.email.set(email);
	}

	public Person(HashMap<String, String> map) {
		this.id.set(Integer.parseInt(map.get("id")));
		this.name.set(map.get("name"));
		this.email.set(map.get("email"));
	}

	public Person() {}

  public static ArrayList<Person> fromList(ArrayList<HashMap<String, String>> list) {
		ArrayList<Person> out = new ArrayList<>();
		for (HashMap<String, String> hashMap : list) {
			Person person = new Person(hashMap);
			out.add(person);
		}
		return out;
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public Integer getId() {
		return idProperty().get();
	}

	private void setId(Integer id) {
		idProperty().set(id);
		this.save();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return nameProperty().get();
	}

	public void setName(String name) {
		nameProperty().set(name);
		this.save();
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}

	public String getEmail() {
		return emailProperty().get();
	}

	public void setEmail(String email) {
		emailProperty().set(email);
		this.save();
	}


}
