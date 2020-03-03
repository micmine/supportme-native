package ch.iso.m120.controller;

import ch.iso.m120.model.Person;

public class Auth {
	private static volatile Auth instance;

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
	
	public static Person login(String username, String password) {
		
		return null;
	}

}
