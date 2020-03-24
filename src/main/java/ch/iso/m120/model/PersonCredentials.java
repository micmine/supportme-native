package ch.iso.m120.model;

import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonCredentials implements DatabaseObject {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty password = new SimpleStringProperty();

	public PersonCredentials(Integer id, String password) {
		this.id.set(id);
		this.password.set(password);
	}

	public PersonCredentials() {
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

	public SimpleStringProperty passwordProperty() {
		return password;
	}

	public String getPassword() {
		return passwordProperty().get();
	}

	public void setPassword(String password) {
		passwordProperty().set(password);
	}
}
