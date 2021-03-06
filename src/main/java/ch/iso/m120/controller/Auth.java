package ch.iso.m120.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ch.iso.m120.model.Chat;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.PersonCredentials;
import ch.iso.m120.model.database.Database;
import ch.iso.m120.model.database.DatabaseEngine;

public final class Auth {
	private static volatile Auth instance;
	private boolean loggedIn = false;

	private Person person;

	private Auth() {
	}

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
			Statement stmt = Database.getInstance().getDatabaseConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next() != false) {
				String databasePassword = rs.getString("password");

				rs.close();
				stmt.close();

				BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), databasePassword);
				if (result.verified) {
					this.loggedIn = true;
					this.setPerson(DatabaseEngine.getInstance().find(Person.class, id));
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

		password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		PersonCredentials credentials = new PersonCredentials(id, password);
		DatabaseEngine.getInstance().save(credentials);

		Chat chat = new Chat(DatabaseEngine.getInstance().getNextId(Chat.class), username);
		DatabaseEngine.getInstance().save(chat);

		this.loggedIn = true;
		this.setPerson(DatabaseEngine.getInstance().find(Person.class, id));
		SceneManager.getInstance().load();
		SceneManager.getInstance().select("main");
	}

	private int getId(String username) {
		try {
			String query = "select id from person where name = '" + username + "' limit 1";
			Statement stmt;
			stmt = Database.getInstance().getDatabaseConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Boolean isLoggedIn() {
		return this.loggedIn;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
