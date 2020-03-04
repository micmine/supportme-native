package ch.iso.m120.model.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper {

	public <T> HashMap<String, String> selectOne(String query, Class<T> type) {
		try {
			
			Statement stmt = Database.getDatabaseConnection().createStatement();

			ResultSet rs = stmt.executeQuery(query);

			rs.next();

			HashMap<String, String> newObject = new HashMap<>();
			
			Field[] fields = type.getDeclaredFields();
			for (Field field : fields) {
				String fieldType = field.getType().getSimpleName().toLowerCase();
				
				newObject.put(field.getName(), rs.getString(field.getName()));
			}

			rs.close();
			stmt.close();
			
			return newObject;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
					String fieldType = field.getType().getSimpleName().toLowerCase();
					newObject.put(field.getName(), rs.getString(field.getName()));
				}
				
				objects.add(newObject);
			}

			rs.close();
			stmt.close();
			
			return objects;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
