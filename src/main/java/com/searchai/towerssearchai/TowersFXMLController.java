package com.searchai.towerssearchai;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class TowersFXMLController {

	@FXML
	public StackPane parent_main_scene;
	private ArrayList<Integer> finalPath = new ArrayList<>();
	@FXML
	private Pane main_pane;

	@FXML
	private ComboBox<?> algorithm_comboBox;
	@FXML
	private Button search_button;

	@FXML
	private Line left_tower_a;
	@FXML
	private Line middle_tower_b;
	@FXML
	private Line right_tower_c;

	@FXML
	private Rectangle small_box_S;
	@FXML
	private Rectangle large_box_L;

	@FXML
	private Ellipse left_tower_support;
	@FXML
	private Ellipse middle_tower_support;
	@FXML
	private Ellipse right_tower_support;

	@FXML
	public void init_search(
			ActionEvent event) { // you really put "ActionEvent" on mouse release lol, anyways i made it on action now so its not giving argument mismatch error

		finalPath = Algorithms.DepthSearch(1);

		for (int i = 0; i < finalPath.size(); i++) {
			System.out.println(finalPath.get(i));
		}

	}
}
