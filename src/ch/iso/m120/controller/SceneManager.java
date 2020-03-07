package ch.iso.m120.controller;

import java.util.HashMap;
import ch.iso.m120.view.LoginView;
import ch.iso.m120.view.MainSplit;
import ch.iso.m120.view.RegisterView;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public final class SceneManager {
  private static SceneManager instance;

  private HashMap<String, Pane> screenMap = new HashMap<>();
  private Scene main;


  public static SceneManager getInstance() {
    if (instance == null) {
      instance = new SceneManager();
    }

    return instance;
  }

  public static SceneManager getInstance(Scene main) {
    if (instance == null) {
      instance = new SceneManager();
      instance.main = main;
    }

    return instance;
  }

  public void add(String name, Pane pane) {
    screenMap.put(name, pane);
  }

  public void remove(String name) {
    screenMap.remove(name);
  }

  public void select(String name) {
    Pane pane = screenMap.get(name);
    main.setRoot(pane);
  }

  public void loadAuth() {
    VBox login = new LoginView();
    this.add("login", login);

    VBox register = new RegisterView();
    this.add("register", register);
  }

  public void load() {
    SplitPane mainContent = new MainSplit();
    Pane main = new Pane();
    main.getChildren().add(mainContent);
    this.add("main", main);
  }

}
