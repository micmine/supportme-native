package ch.iso.m120.model.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DatabaseEngine {

	private static volatile DatabaseEngine instance;

	private DatabaseEngine() {
	}

	public static DatabaseEngine getInstance() {
		if (instance == null) {
			synchronized (DatabaseEngine.class) {
				if (instance == null) {
					instance = new DatabaseEngine();
				}
			}
		}
		return instance;
	}

	protected <T> HashMap<String, String> selectOne(String query, DatabaseObject type) {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.println(query);
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			rs = stmt.executeQuery(query);
			rs.next();

			HashMap<String, String> newObject = new HashMap<>();
			Field[] fields = type.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				newObject.put(field.getName(), rs.getString(field.getName()));
			}

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			return newObject;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(rs);
			Database.getInstance().close(stmt);
		}

		return null;
	}

	protected <T> HashMap<String, String> find(int id, Class<? extends DatabaseObject> objectClass) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from " + getTableName(objectClass) + " where id = " + id + ";";
			System.out.println(query);
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			rs = stmt.executeQuery(query);
			rs.next();

			HashMap<String, String> newObject = new HashMap<>();
			Field[] fields = objectClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				newObject.put(field.getName(), rs.getString(field.getName()));
			}

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			return newObject;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(rs);
			Database.getInstance().close(stmt);
		}
		return null;
	}

	protected ArrayList<HashMap<String, String>> selectMany(String query, Class<? extends DatabaseObject> objectClass) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			ArrayList<HashMap<String, String>> objects = new ArrayList<>();

			System.out.println(query);
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				HashMap<String, String> newObject = new HashMap<>();
				Field[] fields = objectClass.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					newObject.put(field.getName(), rs.getString(field.getName()));
				}

				objects.add(newObject);
			}

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;

			return objects;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(rs);
			Database.getInstance().close(stmt);
		}
		return null;
	}

	public int getNextId(Class<? extends DatabaseObject> objectClass) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String tableName = objectClass.getSimpleName().toLowerCase();
			String query = "select max(id) as id from " + tableName;
			System.out.println(query);

			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				return rs.getInt("id") + 1;
			}

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(rs);
			Database.getInstance().close(stmt);
		}
		return 0;
	}

	public <T extends DatabaseObject> ArrayList<T> all(Class<T> objectClass) {
		String tableName = getTableName(objectClass);
		ArrayList<T> list = fromList(selectMany("select * from " + tableName + ";", objectClass), objectClass);
		return list;
	}

	public <T extends DatabaseObject> ArrayList<T> select(String query, Class<T> objectClass) {
		ArrayList<T> list = fromList(selectMany(query, objectClass), objectClass);
		return list;
	}

	public <T extends DatabaseObject> ArrayList<T> fromList(ArrayList<HashMap<String, String>> list, Class<T> object) {
		ArrayList<T> out = new ArrayList<>();
		for (HashMap<String, String> hashMap : list) {

			T t;
			try {
				t = object.getDeclaredConstructor().newInstance();
				//System.err.println(hashMap);
				toObject(hashMap, t);
				out.add(t);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	public <T extends DatabaseObject> T find(Class<T> objectClass, int id) {
		T t = null;
		try {
			t = objectClass.getDeclaredConstructor().newInstance();
			HashMap<String, String> hashMap = find(id, objectClass);
			toObject(hashMap, t);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return t;
	}

	public String getTableName(Class<? extends DatabaseObject> objectClass) {
		return objectClass.getSimpleName().toLowerCase();
	}

	public void save(DatabaseObject object) {
		Statement stmt = null;
		String query = "";
		int id = 0;
		try {
			Field field = object.getClass().getDeclaredField("id");
			field.setAccessible(true);
			id = ((SimpleIntegerProperty) field.get(object)).intValue();
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		if (this.exists(id, object)) {
			query = this.getUpdateQuery(object);
		} else {
			query = this.getInsetQuery(object);
		}

		try {
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(stmt);
		}

		System.out.println(query);
	}

	public boolean exists(int id, DatabaseObject object) {
		Statement stmt = null;
		ResultSet rs = null;

		String query = "select count(*) from " + getTableName(object.getClass()) + " where id = " + id;
		try {
			System.out.println(query);
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			rs = stmt.executeQuery(query);
			rs.next();

			int count = rs.getInt("count");

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;

			if (count >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.getInstance().close(rs);
			Database.getInstance().close(stmt);
		}
		return false;
	}

	protected void toObject(HashMap<String, String> map, DatabaseObject object) {
		Field[] fields = object.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				Object fieldType = field.get(object);
				String name = field.getName();
				if (fieldType instanceof SimpleIntegerProperty) {
					field.set(object, new SimpleIntegerProperty(Integer.parseInt(map.get(name))));
				} else if (fieldType instanceof SimpleStringProperty) {
					field.set(object, new SimpleStringProperty(map.get(name)));
				} else if (fieldType instanceof Date) {
					java.sql.Date sqlDate = new java.sql.Date(0).valueOf(map.get(name));
					Date date = new Date(sqlDate.getTime());
					field.set(object, date);
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

	protected String getInsetQuery(DatabaseObject object) {
		String query = "insert into " + getTableName(object.getClass()) + " (";

		Field[] fields = object.getClass().getDeclaredFields();
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
				field.setAccessible(true);
				if (fieldType.contains("int")) {
					field.get(object);
					int integer = ((SimpleIntegerProperty) field.get(object)).intValue();
					query = query + integer;
				} else if (fieldType.contains("string")) {
					String string = ((SimpleStringProperty) field.get(object)).getValue();
					query = query + "'" + string + "'";
				} else if (fieldType.contains("date")) {
					java.sql.Date sqlDate = new java.sql.Date(((Date) field.get(object)).getTime());
					query = query + "'" + sqlDate + "'";
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

	protected String getUpdateQuery(DatabaseObject object) {
		String query = "update " + getTableName(object.getClass()) + " set ";

		Field[] fields = object.getClass().getDeclaredFields();
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
				} else if (fieldType.contains("date")) {
					java.sql.Date sqlDate = new java.sql.Date(((Date) field.get(object)).getTime());
					query = query + "'" + sqlDate + "'";
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
				+ ((SimpleIntegerProperty) object.getClass().getDeclaredField("id").get(object)).intValue() + ";";
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return query;
	}
}
