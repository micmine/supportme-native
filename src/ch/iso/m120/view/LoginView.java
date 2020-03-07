package ch.iso.m120.view;

import ch.iso.m120.controller.LoginEventHandler;
import ch.iso.m120.controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    passwordtext.setPrefColumnCount(10);
    this.getChildren().add(passwordtext);

    Button submit = new Button("Login");
    submit.setPadding(new Insets(5, 50, 5, 50));
    this.getChildren().add(submit);
    submit.setOnAction(new LoginEventHandler());

    Button switchToRegister = new Button("Switch to Register");
    switchToRegister.setPadding(new Insets(5, 50, 5, 50));
    this.getChildren().add(switchToRegister);
    switchToRegister.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        SceneManager.getInstance().select("register");
      }
    });
  }
}
