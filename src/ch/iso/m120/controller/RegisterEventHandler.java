package ch.iso.m120.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterEventHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Button sumit = (Button) event.getSource();
		VBox form = (VBox) sumit.getParent();
		TextField usernameField =  (TextField) form.getChildren().get(1);
		TextField emailField =  (TextField) form.getChildren().get(3);
		TextField passwordField =  (TextField) form.getChildren().get(5);
		TextField password2Field =  (TextField) form.getChildren().get(7);

		String username = usernameField.getCharacters().toString();
		String email = emailField.getCharacters().toString();
		String password = passwordField.getCharacters().toString();
		String password2 = password2Field.getCharacters().toString();

		System.out.println(username + " " + email + " " + password + " " + password2);

		Auth.getInstance().register(username, email, password);

	}



}
