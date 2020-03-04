package ch.iso.m120.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ch.iso.m120.model.database.Database;
import ch.iso.m120.model.database.DatabaseHelper;
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
		DatabaseHelper databaseHelper = new DatabaseHelper();
		
		ArrayList<Person> persons = Person.fromList(databaseHelper.selectMany("select * from person", Person.class));
		
		for (Person person : persons) {
			data.add(person);
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
