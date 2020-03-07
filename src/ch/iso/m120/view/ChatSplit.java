package ch.iso.m120.view;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatSplit extends SplitPane {
  public ChatSplit(ChatSelector chatSelector) {
    this.setOrientation(Orientation.VERTICAL);
    this.setId("chatSplit");

    VBox messageListView = new MessageListView(this, chatSelector);
    this.getItems().add(messageListView);

    TextField textField = new TextField();
    this.getItems().add(textField);
  }
}
