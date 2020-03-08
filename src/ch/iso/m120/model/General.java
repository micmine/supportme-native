/*
 * General.java
 *
 * Copyright by proserium, all rights reserved.
 * MIT License: https://mit-license.org
 */
package ch.iso.m120.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

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
