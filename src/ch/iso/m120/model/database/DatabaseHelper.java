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

  public <T> HashMap<String, String> selectOne(String query, DatabaseObject type) {
    try {
      System.out.println(query);
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      HashMap<String, String> newObject = new HashMap<>();
      Field[] fields = type.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
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

  public <T> HashMap<String, String> find(int id, DatabaseObject type) {
    try {
      String query = "select * from " + this.getTableName(type) + " where id = " + id + ";";
      System.out.println(query);
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      HashMap<String, String> newObject = new HashMap<>();
      Field[] fields = type.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
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

  public <T> ArrayList<HashMap<String, String>> selectMany(String query, DatabaseObject type) {
    try {
      ArrayList<HashMap<String, String>> objects = new ArrayList<>();

      System.out.println(query);
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {

        HashMap<String, String> newObject = new HashMap<>();
        Field[] fields = type.getClass().getDeclaredFields();
        for (Field field : fields) {
          field.setAccessible(true);
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

  public <T> int getNextId(DatabaseObject type) {
    try {
      String tableName = type.getClass().getSimpleName().toLowerCase();
      String query = "select id from " + tableName + " order by id desc limit 1;";
      System.out.println(query);

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

  public String getTableName(DatabaseObject object) {
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
    if (this.exists(id, (DatabaseObject) object)) {
      query = this.getUpdateQuery((DatabaseObject) object);
    } else {
      query = this.getInsetQuery((DatabaseObject) object);
    }

    try {
      Statement stmt = Database.getDatabaseConnection().createStatement();
      stmt.executeUpdate(query);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.println(query);
  }

  public <T> boolean exists(int id, DatabaseObject object) {
    String query = "select count(*) from " + object.getTableName() + " where id = " + id;
    try {
      System.out.println(query);
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

  public DatabaseObject toObject(HashMap<String, String> map, DatabaseObject object) {
    Field[] fields = object.getClass().getDeclaredFields();
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        String fieldType = field.getType().getSimpleName().toLowerCase();
        String name = field.getName();
        if (fieldType.contains("int")) {
          field.set(object, new SimpleIntegerProperty(Integer.parseInt(map.get(name))));
        } else if (fieldType.contains("string")) {
          field.set(object, new SimpleStringProperty(map.get(name)));
        }
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return object;
  }

  @SuppressWarnings("unchecked")
  private <T> String getInsetQuery(DatabaseObject object) {
    Class<T> objectClass = (Class<T>) object.getClass();
    String query = "insert into " + object.getTableName() + " (";

    Field[] fields = objectClass.getDeclaredFields();
    int numberOfFields = fields.length;
    int i = 0;
    for (Field field : fields) {
      field.setAccessible(true);
      i++;
      query = query + field.getName();
      if (i < numberOfFields) {
        query = query + ", ";
      }
    }

    query = query + ") values (";

    i = 0;
    for (Field field : fields) {
      field.setAccessible(true);
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
  private <T> String getUpdateQuery(DatabaseObject object) {
    Class<T> objectClass = (Class<T>) object.getClass();
    String query = "update " + object.getTableName() + " set ";

    Field[] fields = objectClass.getDeclaredFields();
    int numberOfFields = fields.length;
    int i = 0;

    i = 0;
    for (Field field : fields) {
      field.setAccessible(true);
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
