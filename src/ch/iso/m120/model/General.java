package ch.iso.m120.model;

public class General {

  private static volatile General instance;

  private General() {}

  public static General getInstance() {
    if (instance == null) {
      synchronized (General.class) {
        if (instance == null) {
          instance = new General();
        }
      }
    }
    return instance;
  }

  public Person getSelected() {
    return selected;
  }

  public void setSelected(Person selected) {
    this.selected = selected;
  }

  private Person selected;
}
