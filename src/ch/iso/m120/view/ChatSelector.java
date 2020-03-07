package ch.iso.m120.view;

import ch.iso.m120.controller.ChatUpdateEvent;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.PersonObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ChatSelector extends ListView<Person> {

  public static ObservableList<Person> selectedss = FXCollections.observableArrayList();

  public ChatSelector(MainSplit mainSplit) {
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

    this.getSelectionModel().selectedItemProperty().addListener(new ChatUpdateEvent(mainSplit));
    PersonObservableList.loadData();
    this.getItems().setAll(PersonObservableList.get());

    ChatSelector.setSelected(this.getItems().get(0));
  }

  public static Person getSelected() {
    return selectedss.get(0);
  }

  public static void setSelected(Person selected) {
    if (selectedss.isEmpty()) {
      selectedss.add(selected);
    } else {
      selectedss.set(0, selected);
    }
  }
}
