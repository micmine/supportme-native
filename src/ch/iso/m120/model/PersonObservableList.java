package ch.iso.m120.model;

import java.util.ArrayList;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class PersonObservableList {

  private final static ObservableList<Person> data = FXCollections.observableArrayList();
  private static TableView<Person> table = null;

  public static ObservableList<Person> get() {
    return data;
  }

  public static TableView<Person> getTable() {
    return table;
  }

  public static void setTable(TableView<Person> table) {
    PersonObservableList.table = table;
  }

  public static void loadData() {
    ArrayList<Person> persons = DatabaseEngine.getInstance().all(Person.class);

    for (Person person : persons) {
      data.add(person);
    }

  }
}
