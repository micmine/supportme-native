/*
 * DatabaseObject.java
 *
 * Copyright by proserium, all rights reserved.
 * MIT License: https://mit-license.org
 */
package ch.iso.m120.model.database;

public class DatabaseObject {

  public void save() {
    DatabaseHelper databaseHelper = new DatabaseHelper();
    // test
    databaseHelper.save(this);
  }

  public String getTableName() {
    DatabaseHelper databaseHelper = new DatabaseHelper();

    return databaseHelper.getTableName(this);
  }

}
