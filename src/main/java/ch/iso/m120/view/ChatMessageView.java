package ch.iso.m120.view;

import ch.iso.m120.controller.Auth;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ChatMessageView extends HBox {

	private Message message = null;

	public ChatMessageView(Message message) {
		this.message = message;
		this.build();
	}

	public void build() {
		System.out.println("1" + this.message.getValue());
		Person author = DatabaseEngine.getInstance().find(Person.class, this.message.getPersonid());
		String value = this.message.getValue() + " - " + author.getName();

		String forground = "#FFFFFF";
		String background = "";
		Pane box = new Pane();
		Text text = new Text(value);

		if (author.getId().equals(Auth.getInstance().getPerson().getId())) {
			this.setAlignment(Pos.CENTER_RIGHT);
			text.setTextAlignment(TextAlignment.RIGHT);
			background = "#3DC777";
		} else {
			this.setAlignment(Pos.CENTER_LEFT);
			text.setTextAlignment(TextAlignment.LEFT);
			background = "#3D95DE";
		}


		text.setX(20);
		text.setY(20);
		setMargin(text, new Insets(5, 5, 5, 5));

		String margin = "-fx-padding: 2px;" +
			"-fx-border-insets: 2px;" +
			"-fx-background-insets: 2px;";
		String corners = "-fx-background-size: 1200 900; " +
			"-fx-background-radius: 5px;" +
			"-fx-border-radius: 5px;" +
			"-fx-border-width:0;" +
			"-fx-border-color: " + background + ";";

		box.getChildren().add(text);


		box.setStyle("-fx-background-color: " + background + ";" + margin + corners);
		text.setStyle("-fx-text-fill: " + forground + ";" + margin);
		this.getChildren().add(box);
	}

}
