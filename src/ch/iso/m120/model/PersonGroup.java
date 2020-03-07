package ch.iso.m120.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonGroup {

  private SimpleIntegerProperty id = new SimpleIntegerProperty();
  private SimpleStringProperty name = new SimpleStringProperty();
  private SimpleIntegerProperty supportLevel = new SimpleIntegerProperty();
  private SimpleBooleanProperty personCanSeeOthers = new SimpleBooleanProperty();
  private SimpleBooleanProperty personCanSeeGroup = new SimpleBooleanProperty();

  public PersonGroup(Integer id, String name, Integer supportLevel, Boolean personCanSeeOthers,
      Boolean personCanSeeGroup) {
    super();
    this.id.set(id);
    this.name.set(name);
    this.supportLevel.set(supportLevel);
    this.personCanSeeOthers.set(personCanSeeOthers);
    this.personCanSeeGroup.set(personCanSeeGroup);
  }

  public SimpleIntegerProperty idProperty() {
    return id;
  }

  public Integer getsupportLevel() {
    return idProperty().get();
  }

  public void setId(Integer id) {
    idProperty().set(id);
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public String getName() {
    return nameProperty().get();
  }

  public void setName(String name) {
    nameProperty().set(name);
  }


  public SimpleIntegerProperty supportLevelProperty() {
    return supportLevel;
  }

  public Integer getSupportLevel() {
    return supportLevelProperty().get();
  }

  public void setSupportLevel(Integer id) {
    supportLevelProperty().set(id);
  }


  public SimpleBooleanProperty personCanSeeOthersProperty() {
    return personCanSeeOthers;
  }

  public boolean getPersonCanSeeOthers() {
    return personCanSeeOthersProperty().get();
  }

  public void setPersonCanSeeOthers(Boolean personCanSeeOthers) {
    personCanSeeOthersProperty().set(personCanSeeOthers);
  }

  public SimpleBooleanProperty personCanSeeGroupProperty() {
    return personCanSeeGroup;
  }

  public boolean getPersonCanSeeGroup() {
    return personCanSeeGroupProperty().get();
  }

  public void setPersonCanSeeGroup(Boolean personCanSeeGroup) {
    personCanSeeGroupProperty().set(personCanSeeGroup);
  }
}
