package ch.iso.m120.model.database;

public class DatabaseObject {

  public void save() {
    new DatabaseHelper().save(this);
  }

  public String getTableName() {
    return new DatabaseHelper().getTableName(this);
  }
}
