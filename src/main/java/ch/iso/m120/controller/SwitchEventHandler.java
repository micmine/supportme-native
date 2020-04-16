package ch.iso.m120.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SwitchEventHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		if (SceneManager.getInstance().getCurrent().equals("login")) {
			SceneManager.getInstance().select("register");
		} else {
			SceneManager.getInstance().select("login");
		}
	}
}
