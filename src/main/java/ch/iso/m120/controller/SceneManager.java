package ch.iso.m120.controller;

import java.util.HashMap;
import ch.iso.m120.view.LoginView;
import ch.iso.m120.view.MainSplit;
import ch.iso.m120.view.RegisterView;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public final class SceneManager {
	private static volatile SceneManager instance;

	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Stage stage;
	private String current;

	private SceneManager() {
	}

	public static SceneManager getInstance() {
		if (instance == null) {
			synchronized (SceneManager.class) {
				if (instance == null) {
					instance = new SceneManager();
				}
			}
		}
		return instance;
	}

	public static SceneManager getInstance(Stage stage) {
		if (instance == null) {
			synchronized (SceneManager.class) {
				if (instance == null) {
					instance = new SceneManager();
					instance.stage = stage;
				}
			}
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
		Panel panel = new Panel();
		panel.setBody(screenMap.get(name));
		Scene scene = new Scene(panel);
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		stage.setScene(scene);
		if (name.equals("main")) {
			stage.setWidth(910);
			stage.setMinWidth(910);
			stage.setMaxWidth(910);

			stage.setHeight(740);
			stage.setMinHeight(740);
			stage.setMaxHeight(740);
		} else {
			stage.sizeToScene();
		}

		this.setCurrent(name);
	}

	public void loadAuth() {
		VBox login = new LoginView();
		this.add("login", login);

		VBox register = new RegisterView();
		this.add("register", register);
	}

	public void load() {
		MainSplit mainContent = new MainSplit();
		BorderPane main = new BorderPane();
		main.setCenter(mainContent);
		this.add("main", main);
	}



	/**
	 * Get current.
	 *
	 * @return current as String.
	 */
	public String getCurrent() {
		return current;
	}

	/**
	 * Set current.
	 *
	 * @param current the value to set.
	 */
	public void setCurrent(String current) {
		this.current = current;
	}
}
