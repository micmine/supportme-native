package ch.iso.m120.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonInformation {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty client = new SimpleStringProperty();
	private SimpleStringProperty ip = new SimpleStringProperty();
	private SimpleStringProperty language = new SimpleStringProperty();

	public PersonInformation(Integer id, String client, String ip, String language) {
		super();
		this.id.set(id);
		this.client.set(client);
		this.ip.set(ip);
		this.language.set(language);
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

	public SimpleStringProperty clientProperty() {
		return client;
	}

	public String getClient() {
		return clientProperty().get();
	}

	public void setClient(String client) {
		clientProperty().set(client);
	}

	public SimpleStringProperty ipProperty() {
		return ip;
	}

	public String getIp() {
		return ipProperty().get();
	}

	public void setIp(String ip) {
		ipProperty().set(ip);
	}

	public SimpleStringProperty languageProperty() {
		return client;
	}

	public String getLanguage() {
		return languageProperty().get();
	}

	public void setLanguage(String language) {
		languageProperty().set(language);
	}
}
