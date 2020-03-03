package ch.iso.m120.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class PersonObservableList {

	private final static ObservableList<Person> data = FXCollections.observableArrayList();

	private static TableView<Person> table = null;

	private static String connectionString = "jdbc:postgresql://localhost/supportme?allowPublicKeyRetrieval=true&useSSL=false";
	private static String connectionUser = "postgres";
	private static String connectionPassword = "pass";

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
		try {
			Class.forName("org.postgresql.Driver");

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select * from person;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");

				data.add(new Person(id, name, email));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
			conn.setAutoCommit(false);

		/*
			Statement stmt = conn.createStatement();

			for (Person person : data) {
				int salId = 1;

				if (person.getSalutation().equals("Herr")) {
					salId = 1;
				}

				if (person.getSalutation().equals("Frau")) {
					salId = 2;
				}

				if (person.getSalutation().equals("Fluid")) {
					salId = 3;
				}

				stmt.execute("insert into adress (addSalId, addName, addPrename) "
						+ "values (" + salId + ", "
						+ "'" + person.getLastname() + "', "
						+ "'" + person.getFirstname() + "');");
			}
			stmt.close();
			
			*/

			conn.commit();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
