package ch.iso.m120.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.PersonCredentials;
import ch.iso.m120.model.database.Database;
import ch.iso.m120.model.database.DatabaseEngine;

public final class Auth {
  private static volatile Auth instance;
  private boolean loggedIn = false;

  private Person person;

  private Auth() {}

  public static Auth getInstance() {
    if (instance == null) {
      synchronized (Auth.class) {
        if (instance == null) {
          instance = new Auth();
        }
      }
    }
    return instance;
  }

  public void login(String username, String password) {
    try {
      int id = this.getId(username);

      String query = "select password from personcredentials where id = " + id + " limit 1;";
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      if (rs.next() != false) {
        String databasePassword = rs.getString("password");

        rs.close();
        stmt.close();

        if (password.equals(databasePassword)) {
          this.loggedIn = true;
          this.person = DatabaseEngine.getInstance().find(Person.class, id);
          SceneManager.getInstance().load();
          SceneManager.getInstance().select("main");
        } else {
          this.loggedIn = false;
          SceneManager.getInstance().select("login");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void register(String username, String email, String password) {
    int id = DatabaseEngine.getInstance().getNextId(Person.class);

    Person person = new Person(id, username, email);
    DatabaseEngine.getInstance().save(person);

    PersonCredentials credentials = new PersonCredentials(id, password);
    DatabaseEngine.getInstance().save(credentials);

    this.loggedIn = true;
    this.person = DatabaseEngine.getInstance().find(Person.class, id);
    SceneManager.getInstance().load();
    SceneManager.getInstance().select("main");
  }

  private int getId(String username) {
    try {
      String query = "select id from person where name = '" + username + "' limit 1";
      Statement stmt;
      stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      int id = 0;
      while (rs.next()) {
        id = rs.getInt("id");
      }
      rs.close();
      stmt.close();
      return id;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return 0;
  }

}
