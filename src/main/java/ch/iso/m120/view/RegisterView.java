package ch.iso.m120.view;

import ch.iso.m120.controller.RegisterEventHandler;
import ch.iso.m120.controller.SceneManager;
import ch.iso.m120.controller.SwitchEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RegisterView extends VBox {

	public RegisterView() {
		super();
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setMinSize(300, 300);

		Text username = new Text("Username:");
		this.getChildren().add(username);

		TextField usernametext = new TextField();
		usernametext.setPrefColumnCount(10);
		this.getChildren().add(usernametext);

		Text email = new Text("Email:");
		this.getChildren().add(email);

		TextField emailtext = new TextField();
		emailtext.setPrefColumnCount(10);
		this.getChildren().add(emailtext);

		Text password = new Text("Password:");
		this.getChildren().add(password);

		PasswordField passwordtext = new PasswordField();
		passwordtext.setPrefColumnCount(10);
		this.getChildren().add(passwordtext);

		Text password2 = new Text("Password wiederholen:");
		this.getChildren().add(password2);

		PasswordField password2text = new PasswordField();
		password2text.setPrefColumnCount(10);
		this.getChildren().add(password2text);

		Button submit = new Button("Login");
		submit.setPadding(new Insets(5, 50, 5, 50));
		this.getChildren().add(submit);
		submit.setOnAction(new RegisterEventHandler());

		Button switchToLogin = new Button("Switch to Login");
		switchToLogin.setPadding(new Insets(5, 50, 5, 50));
		this.getChildren().add(switchToLogin);
		switchToLogin.setOnAction(new SwitchEventHandler());
	}
}
