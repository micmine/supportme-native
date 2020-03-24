package ch.iso.m120.model;

import java.util.ArrayList;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class MessageObservableList {

  private final static ObservableList<Message> data = FXCollections.observableArrayList();
  private static TableView<Message> table = null;

  public static ObservableList<Message> get() {
    return data;
  }

  public static TableView<Message> getTable() {
    return table;
  }

  public static void setTable(TableView<Message> table) {
    MessageObservableList.table = table;
  }

  public static void loadData() {
    ArrayList<Message> messages = DatabaseEngine.getInstance().select(
        "select * from " + DatabaseEngine.getInstance().getTableName(Message.class)
            + " where chatid = " + General.getInstance().getSelected().getId() + ";",
        Message.class);

    for (Message message : messages) {
      data.add(message);
    }

  }
}
