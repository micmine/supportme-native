package ch.iso.m120.view;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MessageListView extends VBox {

	public MessageListView(ChatSplit chatSplit, ChatSelector chatSelector) {
		System.out.println();

		this.getChildren().add(new Text(ChatSelector.getSelected().getName()));
	}

}
