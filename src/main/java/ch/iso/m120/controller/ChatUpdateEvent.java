package ch.iso.m120.controller;

import ch.iso.m120.model.Chat;
import ch.iso.m120.model.General;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChatUpdateEvent implements ChangeListener<Chat> {

	@Override
	public void changed(ObservableValue<? extends Chat> observable, Chat oldValue, Chat newValue) {
		General.getInstance().setSelected(newValue);
		General.getInstance().getMessageListView().reload();
	}

}
