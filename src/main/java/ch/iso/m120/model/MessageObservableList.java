package ch.iso.m120.model;

import java.util.ArrayList;

import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class MessageObservableList {

	private static volatile MessageObservableList instance;

	private MessageObservableList() {
	}

	public static MessageObservableList getInstance() {
		if (instance == null) {
			synchronized (MessageObservableList.class) {
				if (instance == null) {
					instance = new MessageObservableList();
				}
			}
		}
		return instance;
	}

	private final ObservableList<Message> data = FXCollections.observableArrayList();
	private TableView<Message> table = null;

	public ObservableList<Message> get() {
		return data;
	}

	public TableView<Message> getTable() {
		return table;
	}

	public void setTable(TableView<Message> table) {
		this.table = table;
	}

	public void loadData() {
		this.data.clear();
		ArrayList<Message> messages = DatabaseEngine.getInstance()
				.select("select * from " + DatabaseEngine.getInstance().getTableName(Message.class) + " where chatid = "
						+ General.getInstance().getSelected().getId() + ";", Message.class);

		for (Message message : messages) {
			this.data.add(message);
		}

	}
}
