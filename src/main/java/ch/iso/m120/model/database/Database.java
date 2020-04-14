package ch.iso.m120.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

	private static volatile Database instance;
	private static volatile Connection conn;

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
					try {
						Class.forName("org.postgresql.Driver");
						conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		return instance;
	}

	public Connection getDatabaseConnection() {
		try {
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				// nop
			}
		}
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// nop
			}
		}
	}
}
