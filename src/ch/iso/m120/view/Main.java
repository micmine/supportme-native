package ch.iso.m120.view;

import java.nio.channels.NonWritableChannelException;
import java.util.HashMap;

import ch.iso.m120.controller.SceneManager;
import ch.iso.m120.model.Person;
import ch.iso.m120.model.database.DatabaseHelper;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			DatabaseHelper databaseHelper = new DatabaseHelper();
			
			Person person = new Person(databaseHelper.selectOne("select * from person", Person.class));
			
			System.out.println(person.getName());
			/*
			Scene scene = new Scene(new Pane(), 400, 400);
			SceneManager manager = SceneManager.getInstance(scene);
			
			manager.load();
			
			manager.select("login");
			//scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());

			stage.setTitle("supportme-native");
			stage.setScene(scene);
			stage.show(); */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
