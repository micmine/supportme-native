package ch.iso.m120.model;

import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person implements DatabaseObject {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();

	public Person(Integer id, String name, String email) {
		super();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
	}

	public Person() {
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public Integer getId() {
		return idProperty().get();
	}

	private void setId(Integer id) {
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
