package com.searchai.towerssearchai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("towersGui.fxml"));

		Scene scene = new Scene(fxmlLoader.load(), 600, 500);
		stage.setTitle("Towers Search Algorithms!");
		stage.setScene(scene);
		stage.show();
	}
}