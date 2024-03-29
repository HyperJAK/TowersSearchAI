package com.searchai.towerssearchai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {

		StackPane stack = FXMLLoader.load(new URL(App.class.getResource("towersGui.fxml").toExternalForm()));

		Scene scene = new Scene(stack, 1000, 500);
		stage.setTitle("Towers Search Algorithms!");
		stage.setScene(scene);
		stage.show();
	}

}