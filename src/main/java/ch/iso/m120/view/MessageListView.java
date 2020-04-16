package ch.iso.m120.view;

import ch.iso.m120.controller.Auth;
import ch.iso.m120.model.General;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.MessageObservableList;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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
		this.setStyle("-fx-background-color: #dbdbdb;");

		for (Message message : messages) {
			Person author = DatabaseEngine.getInstance().find(Person.class, message.getPersonid());
			String value = message.getValue() + " - " + author.getName();

			HBox align = new HBox();

			if (author.getId().equals(Auth.getInstance().getPerson().getId())) {
				align.setAlignment(Pos.CENTER_RIGHT);
			} else {
				align.setAlignment(Pos.CENTER_LEFT);
			}

			Pane box = new Pane();
			Label text = new Label(value);
			text.setTextAlignment(TextAlignment.RIGHT);
			//text.setStyle("-fx-text-fill: green;");
			//text.setStyle("-fx-background-color: green;");

			setMargin(text, new Insets(5, 5, 5, 5));
			//setMargin(box, new Insets(2, 2, 2, 2));
			String margin = "-fx-padding: 5px;" +
				"-fx-border-insets: 5px;" +
				"-fx-background-insets: 5px;";
			box.getChildren().add(text);


			//box.setStyle("-fx-background-color: #f0a500;");
			box.setStyle("-fx-background-color: #f0a500;" + margin);
			text.setStyle(margin);
			align.getChildren().add(box);
			this.getChildren().add(align);
		}
	}

}
