package ch.iso.m120.view;

import ch.iso.m120.model.General;
import ch.iso.m120.model.Person;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MessageListView extends VBox {

  private Text text;

  public MessageListView() {
    General.getInstance().setMessageListView(this);
    General.getInstance().setSelected(new Person(99, "NO", "NO@gmail.com"));
    this.text = new Text(General.getInstance().getSelected().getName());
    this.getChildren().add(text);
  }

  public void reload() {
    text.setText(General.getInstance().getSelected().getName());
  }

}
