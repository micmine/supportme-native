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

		if (author.getId().equals(Auth.getInstance().getPerson().getId())) {
			this.setAlignment(Pos.CENTER_RIGHT);
		} else {
			this.setAlignment(Pos.CENTER_LEFT);
		}

		Pane box = new Pane();
		Text text = new Text(value);
		text.setTextAlignment(TextAlignment.RIGHT);
		//text.setStyle("-fx-text-fill: green;");
		//text.setStyle("-fx-background-color: green;");
		text.setX(50);
		text.setY(50);
		setMargin(text, new Insets(5, 5, 5, 5));
		//setMargin(box, new Insets(2, 2, 2, 2));
		String margin = "-fx-padding: 5px;" +
			"-fx-border-insets: 5px;" +
			"-fx-background-insets: 5px;";
		String corners = "-fx-background-size: 1200 900; " +
			"-fx-background-radius: 30;" +
			"-fx-border-radius: 30;" +
			"-fx-border-width:5;" +
			"-fx-border-color: #FC3D44;";

		box.getChildren().add(text);


		//box.setStyle("-fx-background-color: #f0a500;");
		box.setStyle("-fx-background-color: #f0a500;" + margin + corners);
		text.setStyle(margin);
		this.getChildren().add(box);
		//this.getChildren().add(align);
	}

}
