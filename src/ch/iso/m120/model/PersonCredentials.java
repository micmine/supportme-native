package ch.iso.m120.model;

import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonCredentials extends DatabaseObject {

	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleStringProperty password = new SimpleStringProperty();

	public PersonCredentials(Integer id, String password) {
		super();
		this.id.set(id);
		this.password.set(password);
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
