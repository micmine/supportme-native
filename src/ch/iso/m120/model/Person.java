package ch.iso.m120.model;

import java.util.ArrayList;
import java.util.HashMap;
import ch.iso.m120.model.database.DatabaseHelper;
import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person extends DatabaseObject {

  private SimpleIntegerProperty id = new SimpleIntegerProperty();
  private SimpleStringProperty name = new SimpleStringProperty();
  private SimpleStringProperty email = new SimpleStringProperty();

  public Person(Integer id, String name, String email) {
    super();
    this.setId(id);
    this.setName(name);;
    this.setEmail(email);;
  }

  public Person(HashMap<String, String> map) {
    new DatabaseHelper().toObject(map, this);
  }

  public static ArrayList<Person> fromList(ArrayList<HashMap<String, String>> list) {
    ArrayList<Person> out = new ArrayList<>();
    for (HashMap<String, String> hashMap : list) {
      Person person = new Person(hashMap);
      out.add(person);
    }
    return out;
  }

  public Person() {}

  public static ArrayList<Person> all() {
    DatabaseHelper databaseHelper = new DatabaseHelper();
    ArrayList<Person> persons = Person.fromList(
        databaseHelper.selectMany("select * from " + new Person().getTableName(), new Person()));
    return persons;
  }

  public static Person find(int id) {
    return new Person(new DatabaseHelper().find(id, new Person()));
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
