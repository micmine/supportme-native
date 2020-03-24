package ch.iso.m120.view;

import javafx.scene.control.SplitPane;

public class MainSplit extends SplitPane {

	public MainSplit() {
		super();
		this.setId("mainSplit");

		// if user.hasPermission
		// ChatSelector
		ChatSelector chatSelector = new ChatSelector();
		this.getItems().add(chatSelector);

		// Chat view
		SplitPane chatSplit = new ChatSplit();
		this.getItems().add(chatSplit);

		this.getDividers().get(0).setPosition(0.05);
	}
}
