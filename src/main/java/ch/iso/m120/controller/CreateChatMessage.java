/*
 * CreateChatMessage.java
 *
 * Copyright by proserium, all rights reserved.
 * MIT License: https://mit-license.org
 */
package ch.iso.m120.controller;

import java.util.Date;

import ch.iso.m120.model.General;
import ch.iso.m120.model.Message;
import ch.iso.m120.model.database.DatabaseEngine;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class CreateChatMessage implements EventHandler {

	private TextField field;

	public CreateChatMessage(TextField textField) {
		this.field = textField;
	}

	@Override
	public void handle(Event arg0) {
		Message message = new Message(DatabaseEngine.getInstance().getNextId(Message.class),
				General.getInstance().getSelected().getId(), field.getText(), new Date());

		DatabaseEngine.getInstance().save(message);
		field.clear();
		
		General.getInstance().getMessageListView().reload();
	}

}
