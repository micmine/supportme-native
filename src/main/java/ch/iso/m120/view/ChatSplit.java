package ch.iso.m120.view;

import javafx.scene.layout.VBox;

public class ChatSplit extends VBox {
	public ChatSplit() {
		this.getStyleClass().add("panel-primary");

		this.setId("chatSplit");

		VBox messageListView = new MessageListView();
		this.getChildren().add(messageListView);

		ChatForm chatForm = new ChatForm();
		this.getChildren().add(chatForm);
	}
}
