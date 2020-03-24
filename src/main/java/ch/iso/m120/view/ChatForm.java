/*
 * ChatForm.java
 *
 * Copyright by proserium, all rights reserved.
 * MIT License: https://mit-license.org
 */
package ch.iso.m120.view;

import ch.iso.m120.controller.CreateChatMessage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ChatForm extends HBox {

	@SuppressWarnings("unchecked")
	public ChatForm() {
		TextField textField = new TextField();
		this.getChildren().add(textField);

		Button button = new Button("send");
		this.getChildren().addAll(button);

		button.setOnAction(new CreateChatMessage(textField));
	}

}
