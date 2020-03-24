package ch.iso.m120.view;

import ch.iso.m120.controller.SceneManager;
import ch.iso.m120.model.Chat;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    try {
      SceneManager manager = SceneManager.getInstance(stage);
      manager.loadAuth();

      stage.setTitle("supportme-native");
      manager.select("login");
      stage.sizeToScene();
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
