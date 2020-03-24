package ch.iso.m120.view;

import ch.iso.m120.controller.ChatUpdateEvent;
import ch.iso.m120.model.General;
import ch.iso.m120.model.Chat;
import ch.iso.m120.model.ChatObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ChatSelector extends ListView<Chat> {

	public ChatSelector() {
		this.setCellFactory(value -> new ListCell<Chat>() {
			@Override
			protected void updateItem(Chat item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getName());
				}
			}
		});

		this.getSelectionModel().selectedItemProperty().addListener(new ChatUpdateEvent());
		ChatObservableList.loadData();
		this.getItems().setAll(ChatObservableList.get());
		General.getInstance().setSelected(this.getItems().get(0));

		General.getInstance().setSelected(this.getSelectionModel().getSelectedItem());
	}
}
