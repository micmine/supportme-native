package ch.iso.m120.controller;

import ch.iso.m120.model.Person;
import ch.iso.m120.view.ChatSelector;
import ch.iso.m120.view.ChatSplit;
import ch.iso.m120.view.MainSplit;
import ch.iso.m120.view.MessageListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChatUpdateEvent implements ChangeListener<Person> {

  private MainSplit mainSplit;

  public ChatUpdateEvent(MainSplit mainSplit) {
    this.mainSplit = mainSplit;
  }

  @Override
  public void changed(ObservableValue<? extends Person> observable, Person oldValue,
      Person newValue) {
    ChatSplit chatSplit = (ChatSplit) this.mainSplit.getItems().get(1);
    MessageListView messageListView = (MessageListView) chatSplit.getItems().get(0);
    System.out.println("New selection: " + newValue.getName());
    ChatSelector.setSelected(newValue);
    System.out.println(ChatSelector.getSelected().getName());
  }

}
