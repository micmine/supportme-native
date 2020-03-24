package ch.iso.m120.model;

import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chat implements DatabaseObject {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty();

	public Chat(Integer id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}

	public Chat() {
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
}
