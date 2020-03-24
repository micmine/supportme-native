package ch.iso.m120.model;

import ch.iso.m120.view.MessageListView;

public class General {

	private static volatile General instance;

	private General() {
	}

	public static General getInstance() {
		if (instance == null) {
			synchronized (General.class) {
				if (instance == null) {
					instance = new General();
				}
			}
		}
		return instance;
	}

	private Chat selected;
	private MessageListView messageListView;

	public Chat getSelected() {
		return selected;
	}

	public void setSelected(Chat selected) {
		this.selected = selected;
	}

	public MessageListView getMessageListView() {
		return messageListView;
	}

	public void setMessageListView(MessageListView messageListView) {
		this.messageListView = messageListView;
	}
}
