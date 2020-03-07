package ch.iso.m120.model.database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import ch.iso.m120.model.Person;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DatabaseEngine {

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

  public <T> HashMap<String, String> find(int id, Class<? extends DatabaseObject> type) {
    try {
      String query = "select * from " + getTableName(type) + " where id = " + id + ";";
      System.out.println(query);
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);
      rs.next();

      HashMap<String, String> newObject = new HashMap<>();
      Field[] fields = type.getDeclaredFields();
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

  public ArrayList<HashMap<String, String>> selectMany(String query,
      Class<? extends DatabaseObject> type) {
    try {
      ArrayList<HashMap<String, String>> objects = new ArrayList<>();

      System.out.println(query);
      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {

        HashMap<String, String> newObject = new HashMap<>();
        Field[] fields = type.getDeclaredFields();
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

  public int getNextId(Class<? extends DatabaseObject> type) {
    try {
      String tableName = type.getSimpleName().toLowerCase();
      String query = "select max(id) as id from " + tableName;
      System.out.println(query);

      Statement stmt = Database.getDatabaseConnection().createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        return rs.getInt("id") + 1;
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }


  public <T extends DatabaseObject> ArrayList<T> all(Class<T> object) {
    String tableName = getTableName(object);
    ArrayList<T> list = fromList(selectMany("select * from " + tableName, object), object);
    return list;
  }

  public <T extends DatabaseObject> ArrayList<T> fromList(ArrayList<HashMap<String, String>> list,
      Class<T> object) {
    ArrayList<T> out = new ArrayList<>();
    for (HashMap<String, String> hashMap : list) {

      T t;
      try {
        t = object.newInstance();
        toObject(hashMap, t);
        out.add(t);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return out;
  }


  public <T extends DatabaseObject> T find(Class<T> objectClass, int id) {
    T t = null;
    try {
      t = objectClass.newInstance();
      HashMap<String, String> hashMap = find(id, objectClass);
      toObject(hashMap, t);

    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return t;
  }



  public String getTableName(Class<? extends DatabaseObject> object) {
    return object.getSimpleName().toLowerCase();
  }

  public void save(DatabaseObject object) {
    String query = "";
    int id = 0;
    try {
      id = ((SimpleIntegerProperty) object.getClass().getField("id").get(object)).intValue();
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      e.printStackTrace();
    }
    if (this.exists(id, object)) {
      query = this.getUpdateQuery(object.getClass());
    } else {
      query = this.getInsetQuery(object.getClass());
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

  public boolean exists(int id, DatabaseObject object) {
    String query = "select count(*) from " + getTableName(object.getClass()) + " where id = " + id;
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

  public void toObject(HashMap<String, String> map, DatabaseObject object) {
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
  }

  @SuppressWarnings("unchecked")
  private String getInsetQuery(Class<? extends DatabaseObject> object) {
    String query = "insert into " + getTableName(object) + " (";

    Field[] fields = object.getDeclaredFields();
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
  private String getUpdateQuery(Class<? extends DatabaseObject> object) {
    String query = "update " + getTableName(object) + " set ";

    Field[] fields = object.getDeclaredFields();
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
          + ((SimpleIntegerProperty) object.getField("id").get(object)).intValue() + ";";
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      e.printStackTrace();
    }
    return query;
  }
}
