package ch.iso.m120.model;

import java.util.ArrayList;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ChatObservableList {

	private final static ObservableList<Chat> data = FXCollections.observableArrayList();
	private static TableView<Chat> table = null;

	public static ObservableList<Chat> get() {
		return data;
	}

	public static TableView<Chat> getTable() {
		return table;
	}

	public static void setTable(TableView<Chat> table) {
		ChatObservableList.table = table;
	}

	public static void loadData() {
		ArrayList<Chat> chats = DatabaseEngine.getInstance().all(Chat.class);

		for (Chat chat : chats) {
			data.add(chat);
		}

	}
}
