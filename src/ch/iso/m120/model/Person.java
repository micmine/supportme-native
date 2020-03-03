package ch.iso.m120.model;

import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();

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

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public Integer getId() {
		return idProperty().get();
	}

	public void setId(Integer id) {
		idProperty().set(id);
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return nameProperty().get();
	}

	public void setName(String name) {
		nameProperty().set(name);
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}

	public String getEmail() {
		return emailProperty().get();
	}

	public void setEmail(String email) {
		emailProperty().set(email);
	}


}
