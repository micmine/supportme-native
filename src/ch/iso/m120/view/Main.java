package ch.iso.m120.view;

import ch.iso.m120.controller.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			Scene scene = new Scene(new Pane(), 400, 400);
			SceneManager manager = SceneManager.getInstance(scene);
			
			manager.load();
			
			manager.select("login");
			//scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());

			stage.setTitle("supportme-native");
			stage.setScene(scene);
			stage.show(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
