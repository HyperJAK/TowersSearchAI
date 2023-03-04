package com.searchai.towerssearchai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TowersFXMLController implements Initializable {

	@FXML
	public StackPane parent_main_scene;
	@FXML
	public Pane main_pane;
	@FXML
	public ChoiceBox<String> algorithm_choiceBox = new ChoiceBox<>();
	@FXML
	private ChoiceBox<Integer> start_pos_choices = new ChoiceBox<>();

	@FXML
	private ChoiceBox<Integer> target_pos_choices = new ChoiceBox<>();
	private ArrayList<Integer> finalPath = new ArrayList<>();

	@FXML
	private TextArea path_textBox;

	private String selected_algorithm;
	private int selected_target;
	private int selected_start;

	private boolean choiceBoxes_selected = false;

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

	//Method used to initialize the Controller class
	// (Its automatically used by the fxml file when we call it in main) and is needed for the choiceboxes to work
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		algorithm_choiceBox.getItems().addAll("Breadth search", "Depth search", "Best-first search", "A* search");
		start_pos_choices.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
		target_pos_choices.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
	}
	@FXML
	public void init_search(ActionEvent event) {

		initChoiceBoxes();
		//Checks if the 3 choiceboxes are selected else displays in textbox a message
		if (choiceBoxes_selected) {
			switch (selected_algorithm.toLowerCase()) {
				case "breadth search" -> {
					finalPath = Algorithms.BreadthFirst(selected_start, selected_target);
				}
				case "depth search" -> {
					finalPath = Algorithms.DepthSearch(selected_start, selected_target);
				}
				case "best-first search" -> {

				}
				case "a* search" -> {

				}

			}
			if (!finalPath.isEmpty()) {
				writePath();
			}
		} else {
			path_textBox.setText("Please select a value from each of the choiceBoxes");
		}

	}
	//This function initialises the choice boxes by filling them with options
	public void initChoiceBoxes() {
		if (algorithm_choiceBox.getValue() != null && start_pos_choices.getValue() != null && target_pos_choices.getValue() != null) {
			selected_algorithm = algorithm_choiceBox.getValue();
			selected_start = start_pos_choices.getValue();
			selected_target = target_pos_choices.getValue();

			choiceBoxes_selected = true;
		}
	}
	//This function is responsible for writing the path after each algorithm is finished computing
	private void writePath() {
		StringBuilder organize_paths = new StringBuilder(
				"Answer: (ALGORITHM: " + selected_algorithm + " / START: " + selected_start + " -> END: " + selected_target + "\n");
		for (int i = 0; i < finalPath.size(); i++) {
			organize_paths.append(" -> State: ").append(finalPath.get(i));
		}
		organize_paths.append("\n");

		path_textBox.setText(String.valueOf(organize_paths));
	}

}
