package ch.iso.m120.view;

import javafx.scene.layout.BorderPane;

public class MainSplit extends BorderPane {

	public MainSplit() {
		super();
		this.setId("mainSplit");

		// if user.hasPermission
		// ChatSelector
		ChatSelector chatSelector = new ChatSelector();
		this.setLeft(chatSelector);

		// Chat view
		ChatSplit chatSplit = new ChatSplit();
		this.setCenter(chatSplit);

	}
}
