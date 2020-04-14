package ch.iso.m120.view;

import ch.iso.m120.model.General;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.MessageObservableList;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MessageListView extends VBox {

	public MessageListView() {
		General.getInstance().setMessageListView(this);
	}

	public void reload() {
		MessageListView.clearConstraints(this);
		MessageObservableList.getInstance().loadData();

		ObservableList<Message> messages = MessageObservableList.getInstance().get();

		this.getChildren().clear();

		for (Message message : messages) {
			String value = message.getValue() + " - " + DatabaseEngine.getInstance().find(Person.class, message.getPersonid()).getName();
			Text text = new Text(value);

			text.getStyleClass().setAll("p", "bg-success");

			this.getChildren().add(text);
		}
	}

}
