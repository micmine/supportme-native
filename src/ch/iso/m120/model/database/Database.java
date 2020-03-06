package ch.iso.m120.model.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private static volatile Database instance;

	private static String connectionString = "jdbc:postgresql://localhost/supportme?allowPublicKeyRetrieval=true&useSSL=false";
	private static String connectionUser = "postgres";
	private static String connectionPassword = "pass";

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			synchronized (Database.class) {
				if (instance == null) {
					instance = new Database();
				}
			}
		}
		return instance;
	}

	public static Connection getDatabaseConnection() {
		try {
			Class.forName("org.postgresql.Driver");

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
