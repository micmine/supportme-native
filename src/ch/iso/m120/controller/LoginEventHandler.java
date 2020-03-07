package ch.iso.m120.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginEventHandler implements EventHandler<ActionEvent> {

  @Override
  public void handle(ActionEvent event) {
    Button sumit = (Button) event.getSource();
    VBox form = (VBox) sumit.getParent();
    TextField usernameField = (TextField) form.getChildren().get(1);
    TextField passwordField = (TextField) form.getChildren().get(3);

    String username = usernameField.getCharacters().toString();
    String password = passwordField.getCharacters().toString();

    Auth.getInstance().login(username, password);
  }
}
