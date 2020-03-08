package ch.iso.m120.view;

import ch.iso.m120.controller.ChatUpdateEvent;
import ch.iso.m120.model.General;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.PersonObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ChatSelector extends ListView<Person> {

  public ChatSelector() {
    this.setCellFactory(value -> new ListCell<Person>() {
      @Override
      protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null || item.getName() == null) {
          setText(null);
        } else {
          setText(item.getName() + " -  " + item.getEmail());
        }
      }
    });

    this.getSelectionModel().selectedItemProperty().addListener(new ChatUpdateEvent());
    PersonObservableList.loadData();
    this.getItems().setAll(PersonObservableList.get());

    General.getInstance().setSelected(this.getSelectionModel().getSelectedItem());
  }
}
