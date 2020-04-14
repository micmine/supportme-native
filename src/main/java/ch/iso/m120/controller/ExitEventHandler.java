package ch.iso.m120.controller;

import java.sql.SQLException;
import ch.iso.m120.model.database.Database;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitEventHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		try {
			Database.getInstance().getDatabaseConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Platform.exit();
	}

}
