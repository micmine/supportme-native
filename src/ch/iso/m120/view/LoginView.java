package ch.iso.m120.view;

import ch.iso.m120.controller.LoginEventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginView extends VBox {

	public LoginView() {
		super();
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setMinSize(300, 300);

		Text username = new Text("Username:");
		this.getChildren().add(username);

		TextField usernametext = new TextField();
		usernametext.setPrefColumnCount(10);
		this.getChildren().add(usernametext);

		Text password = new Text("Password:");
		this.getChildren().add(password);

		TextField passwordtext = new TextField();
		passwordtext .setPrefColumnCount(10);
		this.getChildren().add(passwordtext);

		Button submit = new Button("Login");
		submit.setPadding(new Insets(5, 50, 5, 50));
		this.getChildren().add(submit);
		submit.setOnAction(new LoginEventHandler());
	}

}
