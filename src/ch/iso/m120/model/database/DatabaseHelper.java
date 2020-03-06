package ch.iso.m120.model.database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DatabaseHelper {

  public <T> HashMap<String, String> selectOne(String query, Class<T> type) {
    try {
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      HashMap<String, String> newObject = new HashMap<>();
      Field[] fields = type.getDeclaredFields();
      for (Field field : fields) {
        newObject.put(field.getName(), rs.getString(field.getName()));
      }

      rs.close();
      stmt.close();
      return newObject;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;

  }

  public <T> HashMap<String, String> find(int id, Object type) {
    try {
      String query = "select * from " + this.getTableName(type) + " where id = " + id + ";";
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      HashMap<String, String> newObject = new HashMap<>();
      Field[] fields = type.getClass().getDeclaredFields();
      for (Field field : fields) {
        newObject.put(field.getName(), rs.getString(field.getName()));
      }

      rs.close();
      stmt.close();
      return newObject;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;

  }

  public <T> ArrayList<HashMap<String, String>> selectMany(String query, Class<T> type) {
    try {
      ArrayList<HashMap<String, String>> objects = new ArrayList<>();

      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {

        HashMap<String, String> newObject = new HashMap<>();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
          newObject.put(field.getName(), rs.getString(field.getName()));
        }

        objects.add(newObject);
      }

      rs.close();
      stmt.close();

      return objects;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public <T> int getNextId(Class<T> type) {
    try {
      String tableName = type.getSimpleName().toLowerCase();
      String query = "select id from " + tableName + " order by id desc limit 1;";

      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        return rs.getInt("id") + 1;
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return 0;
  }

  public String getTableName(Object object) {
    return object.getClass().getSimpleName().toLowerCase();
  }

  public <T> void save(Object object) {
    String query = "";
    int id = 0;
    try {
      id = ((SimpleIntegerProperty) object.getClass().getField("id").get(object)).intValue();
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      e.printStackTrace();
    }
    if (this.exists(id, object.getClass())) {
      query = this.getUpdateQuery(object);
    } else {
      query = this.getInsetQuery(object);
    }

    try {
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      System.out.println(rs.next());
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.println(query);
  }

  public <T> boolean exists(int id, Class<T> type) {
    String tableName = type.getSimpleName().toLowerCase();

    String query = "select count(*) from " + tableName + " where id = " + id;
    try {

      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      int count = rs.getInt("count");

      rs.close();
      stmt.close();

      if (count >= 1) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  private <T> String getInsetQuery(Object object) {
    Class<T> objectClass = (Class<T>) object.getClass();
    String tableName = objectClass.getSimpleName().toLowerCase();
    String query = "insert into " + tableName + " (";

    Field[] fields = objectClass.getDeclaredFields();
    int numberOfFields = fields.length;
    int i = 0;
    for (Field field : fields) {
      i++;
      query = query + field.getName();
      if (i < numberOfFields) {
        query = query + ", ";
      }
    }

    query = query + ") values (";

    i = 0;
    for (Field field : fields) {
      i++;
      String fieldType = field.getType().getSimpleName().toLowerCase();

      try {
        if (fieldType.contains("int")) {
          int integer = ((SimpleIntegerProperty) field.get(object)).intValue();
          query = query + integer;
        } else if (fieldType.contains("string")) {
          String string = ((SimpleStringProperty) field.get(object)).getValue();
          query = query + "'" + string + "'";
        }

        if (i < numberOfFields) {
          query = query + ", ";
        }

      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    query = query + ");";
    return query;
  }

  @SuppressWarnings("unchecked")
  private <T> String getUpdateQuery(Object object) {
    Class<T> objectClass = (Class<T>) object.getClass();
    String tableName = objectClass.getSimpleName().toLowerCase();
    String query = "update " + tableName + " set ";

    Field[] fields = objectClass.getDeclaredFields();
    int numberOfFields = fields.length;
    int i = 0;

    i = 0;
    for (Field field : fields) {
      i++;
      String fieldType = field.getType().getSimpleName().toLowerCase();

      query = query + field.getName();
      query = query + " = ";
      try {
        if (fieldType.contains("int")) {
          int integer = ((SimpleIntegerProperty) field.get(object)).intValue();
          query = query + integer;
        } else if (fieldType.contains("string")) {
          String string = ((SimpleStringProperty) field.get(object)).getValue();
          query = query + "'" + string + "'";
        }

        if (i < numberOfFields) {
          query = query + ", ";
        }

      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    try {
      query = query + " where id = "
          + ((SimpleIntegerProperty) objectClass.getField("id").get(object)).intValue() + ";";
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      e.printStackTrace();
    }
    return query;
  }

}
