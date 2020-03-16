package ch.iso.m120.model;

import ch.iso.m120.controller.Auth;
import ch.iso.m120.model.database.DatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Message implements DatabaseObject {

  private SimpleIntegerProperty id = new SimpleIntegerProperty();
  private SimpleIntegerProperty chatid = new SimpleIntegerProperty();
  private SimpleIntegerProperty personid = new SimpleIntegerProperty();
  private SimpleIntegerProperty templateid = new SimpleIntegerProperty();
  private SimpleIntegerProperty formid = new SimpleIntegerProperty();
  private SimpleStringProperty value = new SimpleStringProperty();

  public Message(Integer id, Integer chatid, String value) {
    super();
    this.setId(id);
    this.setChatid(chatid);
    this.setPersonid(Auth.getInstance().getPerson().getId());
    this.templateid = new SimpleIntegerProperty(0);
    this.formid = new SimpleIntegerProperty(0);
    this.setValue(value);;
  }

  public Message() {}

  public final SimpleIntegerProperty idProperty() {
    return this.id;
  }


  public final int getId() {
    return this.idProperty().get();
  }


  public final void setId(final int id) {
    this.idProperty().set(id);
  }


  public final SimpleIntegerProperty chatidProperty() {
    return this.chatid;
  }


  public final int getChatid() {
    return this.chatidProperty().get();
  }


  public final void setChatid(final int chatid) {
    this.chatidProperty().set(chatid);
  }


  public final SimpleIntegerProperty personidProperty() {
    return this.personid;
  }


  public final int getPersonid() {
    return this.personidProperty().get();
  }


  public final void setPersonid(final int personid) {
    this.personidProperty().set(personid);
  }


  public final SimpleIntegerProperty templateidProperty() {
    return this.templateid;
  }


  public final int getTemplateid() {
    return this.templateidProperty().get();
  }


  public final void setTemplateid(final int templateid) {
    this.templateidProperty().set(templateid);
  }


  public final SimpleIntegerProperty formidProperty() {
    return this.formid;
  }


  public final int getFormid() {
    return this.formidProperty().get();
  }


  public final void setFormid(final int formid) {
    this.formidProperty().set(formid);
  }


  public final SimpleStringProperty valueProperty() {
    return this.value;
  }


  public final String getValue() {
    return this.valueProperty().get();
  }


  public final void setValue(final String value) {
    this.valueProperty().set(value);
  }
}
