package ch.iso.m120.view;

import ch.iso.m120.controller.CreateChatMessage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ChatForm extends HBox {

	@SuppressWarnings("unchecked")
	public ChatForm() {
		TextField textField = new TextField();
		this.getChildren().add(textField);

		setHgrow(textField, Priority.ALWAYS);

		Button button = new Button("send");
		button.getStyleClass().setAll("btn","btn-primary");
		this.getChildren().addAll(button);

		button.setOnAction(new CreateChatMessage(textField));
	}

}
