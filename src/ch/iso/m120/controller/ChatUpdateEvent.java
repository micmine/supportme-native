package ch.iso.m120.controller;

import ch.iso.m120.model.General;
import ch.iso.m120.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChatUpdateEvent implements ChangeListener<Person> {

  @Override
  public void changed(ObservableValue<? extends Person> observable, Person oldValue,
      Person newValue) {
    System.out.println("New selection: " + newValue.getName());

    General.getInstance().setSelected(newValue);
  }

}
