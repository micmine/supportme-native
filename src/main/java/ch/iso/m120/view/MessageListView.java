package ch.iso.m120.view;

import ch.iso.m120.model.Chat;
import ch.iso.m120.model.General;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.MessageObservableList;
import ch.iso.m120.model.Person;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MessageListView extends VBox {

  private Text text;

  public MessageListView() {
    General.getInstance().setMessageListView(this);
  }

  public void reload() {
    MessageListView.clearConstraints(this);
    MessageObservableList.getInstance().loadData();

    ObservableList<Message> messages = MessageObservableList.getInstance().get();

    this.getChildren().clear();
    
    for (Message message : messages) {
      this.getChildren().add(new Text(message.getValue()));
    }
  }

}
