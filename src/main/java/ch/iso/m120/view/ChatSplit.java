package ch.iso.m120.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChatSplit extends VBox {

	public ChatSplit() {
		this.getStyleClass().add("panel-primary");

		this.setId("chatSplit");

		MessageListView messageListView = new MessageListView();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(messageListView);

		this.getChildren().add(scrollPane);

		Pane spacer = new Pane();
		setVgrow(spacer, Priority.ALWAYS);
		this.getChildren().add(spacer);

		ChatForm chatForm = new ChatForm();
		this.getChildren().add(chatForm);
	}
}
