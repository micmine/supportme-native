package ch.iso.m120.view;

import ch.iso.m120.controller.Auth;
import ch.iso.m120.model.General;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.MessageObservableList;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MessageListView extends VBox {

	public MessageListView() {
		General.getInstance().setMessageListView(this);
	}

	public void reload() {
		MessageListView.clearConstraints(this);
		MessageObservableList.getInstance().loadData();

		Auth.getInstance().setPerson(DatabaseEngine.getInstance().find(Person.class, 1));

		ObservableList<Message> messages = MessageObservableList.getInstance().get();

		this.getChildren().clear();
		this.setWidth(Double.MAX_VALUE);

		Region spacer = new Region();
		spacer.setPrefWidth(600);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		this.getChildren().add(spacer);

		messages.forEach(message -> {
			this.getChildren().add(new ChatMessageView(message));
		});
	}

}
