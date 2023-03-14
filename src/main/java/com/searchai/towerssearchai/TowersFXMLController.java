package com.searchai.towerssearchai;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
	int currentPosition_Small = -1; // either 0 or 1 ( 0 bottom, 1 top )
	int currentPosition_Big = -1;
	int nextPosition_Small = -1;
	int nextPosition_Big = -1;
	int currentTower_Small = -1; // either 1 or 2 or 3
	int currentTower_Big = -1;
	int nextTower_Small = -1;
	int nextTower_Big = -1;
	int currentIteration = 0;
	int pathSize = 0;
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

	private int previousState = 0;

	@FXML
	private Pane graphPane;

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
	private Button testButton;
	@FXML
	private Button left_arrow_button;
	@FXML
	private Button right_arrow_button;

	@FXML
	private Circle stateOne;
	@FXML
	private Circle stateTwo;
	@FXML
	private Circle stateThree;
	@FXML
	private Circle stateFour;
	@FXML
	private Circle stateFive;
	@FXML
	private Circle stateSix;
	@FXML
	private Circle stateSeven;
	@FXML
	private Circle stateEight;
	@FXML
	private Circle stateNine;

	@FXML
	private Line state1_state2_line;//
	@FXML
	private Line state1_state3_line1;//
	@FXML
	private Line state1_state3_line2;//
	@FXML
	private Line state2_state3_line;//
	@FXML
	private Line state6_state3_line;//
	@FXML
	private Line state4_state6_line;//
	@FXML
	private Line state4_state5_line;//
	@FXML
	private Line state7_state4_line;//
	@FXML
	private Line state8_state7_line;//
	@FXML
	private Line state7_state9_line;//
	@FXML
	private Line state2_state8_line;//
	@FXML
	private Line state8_state9_line;//
	@FXML
	private Line state5_state6_line;//

	@FXML
	private void mouseMoved_mainPane(MouseEvent eventMove) {
		EventHandler<MouseEvent> handler = this::mouseMoved_mainPane;
		if (target_pos_choices.getValue() != null) {
			search_button.setDisable(false);
		}
		main_pane.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
	}

	@FXML
	private void left_arrow_handler(MouseEvent event) throws InterruptedException {

		if (currentIteration != 0) {
			disableAnimation_btns();
			ManualAnimation(finalPath, true);

		}

	}

	@FXML
	private void right_arrow_handler(MouseEvent event) throws InterruptedException {
		if (currentIteration != finalPath.size() - 1) {
			disableAnimation_btns();
			ManualAnimation(finalPath, false);
			//for first time iteration when iteration is at 0, we had it disabled
		}
	}

	@FXML
	public void animationTest(ActionEvent event) throws InterruptedException {
		resetAnimation();
		currentIteration = 0;
		disableAnimation_btns();
		Animation(finalPath);
	}

	// Method used to initialize the Controller class
	// (Its automatically used by the fxml file when we call it in main) and is
	// needed for the choiceboxes to work
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		algorithm_choiceBox.getItems().addAll("Breadth search", "Depth search", "Best-first search", "A* search");
		start_pos_choices.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
		target_pos_choices.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
	}

	@FXML
	public void init_search(ActionEvent event) {

		initChoiceBoxes();
		// Checks if the 3 choiceboxes are selected else displays in textbox a message
		if (choiceBoxes_selected) {
			switch (selected_algorithm.toLowerCase()) {
				case "breadth search" -> {
					finalPath = Algorithms.BreadthFirst(selected_start, selected_target);
					resetAnimation();
				}
				case "depth search" -> {
					finalPath = Algorithms.DepthFirst(selected_start, selected_target);
					resetAnimation();
				}
				case "best-first search" -> {
					finalPath = Algorithms.BestFirst(selected_start, selected_target);
					resetAnimation();
				}
				case "a* search" -> {
					finalPath = Algorithms.Astar(selected_start, selected_target);
					resetAnimation();
				}

			}
			if (!finalPath.isEmpty()) {
				writePath();
				towersStartingState();
				enableAnimation_btns();
				left_arrow_button.setDisable(true);
			}
		} else {
			path_textBox.setText("Please select a value from each of the choiceBoxes");
		}

	}

	private void enableAnimation_btns() {
		testButton.setDisable(false);
		right_arrow_button.setDisable(false);
		left_arrow_button.setDisable(false);
		search_button.setDisable(false);
	}

	private void disableAnimation_btns() {
		testButton.setDisable(true);
		right_arrow_button.setDisable(true);
		left_arrow_button.setDisable(true);
		search_button.setDisable(true);
	}

	// This function initialises the choice boxes by filling them with options
	public void initChoiceBoxes() {
		if (algorithm_choiceBox.getValue() != null && start_pos_choices.getValue() != null && target_pos_choices.getValue() != null) {
			selected_algorithm = algorithm_choiceBox.getValue();
			selected_start = start_pos_choices.getValue();
			selected_target = target_pos_choices.getValue();

			choiceBoxes_selected = true;
		}
	}

	// This function is responsible for writing the path after each algorithm is
	// finished computing
	private void writePath() {
		StringBuilder organize_paths = new StringBuilder(
				"Answer: (ALGORITHM: " + selected_algorithm + " / START: " + selected_start + " -> END: " + selected_target + "\n");
		for (Integer integer : finalPath) {
			organize_paths.append(" -> State: ").append(integer);
		}
		organize_paths.append("\n");

		path_textBox.setText(String.valueOf(organize_paths));
	}

	// to reset everything animation related
	public void resetAnimation() {
		currentIteration = 0;
		// reset graph
		stateOne.setFill(Color.BLUE);
		stateTwo.setFill(Color.WHITE);
		stateThree.setFill(Color.WHITE);
		stateFour.setFill(Color.WHITE);
		stateFive.setFill(Color.WHITE);
		stateSix.setFill(Color.WHITE);
		stateSeven.setFill(Color.WHITE);
		stateEight.setFill(Color.WHITE);
		stateNine.setFill(Color.RED);

		state5_state6_line.setStroke(Color.BLACK);
		state8_state9_line.setStroke(Color.BLACK);
		state2_state8_line.setStroke(Color.BLACK);
		state7_state9_line.setStroke(Color.BLACK);
		state8_state7_line.setStroke(Color.BLACK);
		state7_state4_line.setStroke(Color.BLACK);
		state4_state5_line.setStroke(Color.BLACK);
		state4_state6_line.setStroke(Color.BLACK);
		state6_state3_line.setStroke(Color.BLACK);
		state2_state3_line.setStroke(Color.BLACK);
		state1_state3_line2.setStroke(Color.BLACK);
		state1_state3_line1.setStroke(Color.BLACK);
		state1_state2_line.setStroke(Color.BLACK);

		// reset boxes
		small_box_S.setTranslateX(0);
		small_box_S.setTranslateY(0);

		large_box_L.setTranslateX(0);
		large_box_L.setTranslateY(0);

	}

	private void towersStartingState() {
		switch (finalPath.get(0)) {
			case 1 -> {
				small_box_S.setLayoutX(161);
				small_box_S.setLayoutY(148);

				large_box_L.setLayoutX(134);
				large_box_L.setLayoutY(173);
			}
			case 2 -> {
				small_box_S.setLayoutX(272);
				small_box_S.setLayoutY(173);
			}
			case 3 -> {
				small_box_S.setLayoutX(383);
				small_box_S.setLayoutY(173);
			}
			case 4 -> {
				large_box_L.setLayoutX(245);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(161);
				small_box_S.setLayoutY(173);
			}
			case 5 -> {
				large_box_L.setLayoutX(245);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(272);
				small_box_S.setLayoutY(148);
			}
			case 6 -> {
				large_box_L.setLayoutX(245);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(383);
				small_box_S.setLayoutY(173);
			}
			case 7 -> {
				large_box_L.setLayoutX(356);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(161);
				small_box_S.setLayoutY(173);
			}
			case 8 -> {
				large_box_L.setLayoutX(356);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(272);
				small_box_S.setLayoutY(173);
			}
			case 9 -> {
				large_box_L.setLayoutX(356);
				large_box_L.setLayoutY(173);

				small_box_S.setLayoutX(383);
				small_box_S.setLayoutY(148);
			}

		}
		small_box_S.setVisible(true);
		large_box_L.setVisible(true);
	}

	private void illuminatePath(int currentState) {
		// Here previous state is registered an the end of each iteration in function :
		// GraphAnimation
		// And cases are currentStates
		// And numbers inside if cases are states connected to currentState
		switch (currentState) {

			case 1 -> {
				if (previousState == 2) {
					state1_state2_line.setStroke(Color.RED);
				} else if (previousState == 3) {
					state1_state3_line1.setStroke(Color.RED);
				}
			}

			case 2 -> {

				if (previousState == 8) {
					state2_state8_line.setStroke(Color.RED);
				} else if (previousState == 3) {
					state2_state3_line.setStroke(Color.RED);
				} else if (previousState == 1) {
					state1_state2_line.setStroke(Color.RED);
				}

			}
			case 3 -> {

				if (previousState == 6) {
					state6_state3_line.setStroke(Color.RED);
				} else if (previousState == 2) {
					state2_state3_line.setStroke(Color.RED);
				} else if (previousState == 1) {
					state1_state3_line2.setStroke(Color.RED);
					state1_state3_line1.setStroke(Color.RED);
				}

			}
			case 4 -> {

				if (previousState == 6) {
					state4_state6_line.setStroke(Color.RED);
				} else if (previousState == 5) {
					state4_state5_line.setStroke(Color.RED);
				} else if (previousState == 7) {
					state7_state4_line.setStroke(Color.RED);
				}

			}
			case 5 -> {

				if (previousState == 6) {
					state5_state6_line.setStroke(Color.RED);
				} else if (previousState == 4) {
					state4_state5_line.setStroke(Color.RED);
				}

			}
			case 6 -> {

				if (previousState == 3) {
					state6_state3_line.setStroke(Color.RED);
				} else if (previousState == 5) {
					state5_state6_line.setStroke(Color.RED);
				} else if (previousState == 4) {
					state4_state6_line.setStroke(Color.RED);
				}

			}
			case 7 -> {

				if (previousState == 8) {
					state8_state7_line.setStroke(Color.RED);
				} else if (previousState == 9) {
					state7_state9_line.setStroke(Color.RED);
				} else if (previousState == 4) {
					state7_state4_line.setStroke(Color.RED);
				}

			}
			case 8 -> {

				if (previousState == 7) {
					state8_state7_line.setStroke(Color.RED);
				} else if (previousState == 9) {
					state8_state9_line.setStroke(Color.RED);
				} else if (previousState == 2) {
					state2_state8_line.setStroke(Color.RED);
				}

			}
			case 9 -> {

				if (previousState == 7) {
					state7_state9_line.setStroke(Color.RED);
				} else if (previousState == 8) {
					state8_state9_line.setStroke(Color.RED);
				}

			}
		}
	}

	// Graph Animation
	private void GraphAnimation(ArrayList<Integer> path) {
		switch (path.get(currentIteration)) {
			case 1 -> {
				stateOne.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateOne.setFill(Color.YELLOW);
				illuminatePath(1);
			}
			case 2 -> {
				stateTwo.setStyle("-dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateTwo.setFill(Color.YELLOW);
				illuminatePath(2);
			}
			case 3 -> {
				stateThree.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateThree.setFill(Color.YELLOW);
				illuminatePath(3);
			}
			case 4 -> {
				stateFour.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateFour.setFill(Color.YELLOW);
				illuminatePath(4);
			}
			case 5 -> {
				stateFive.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateFive.setFill(Color.YELLOW);
				illuminatePath(5);
			}
			case 6 -> {
				stateSix.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateSix.setFill(Color.YELLOW);
				illuminatePath(6);
			}
			case 7 -> {
				stateSeven.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateSeven.setFill(Color.YELLOW);
				illuminatePath(7);
			}
			case 8 -> {
				stateEight.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateEight.setFill(Color.YELLOW);
				illuminatePath(8);
			}
			case 9 -> {
				stateNine.setStyle("dropshadow(three-pass-box, rgba(255, 255, 0, 0.8), 10, 0, 0, 0);");
				stateNine.setFill(Color.YELLOW);
				illuminatePath(9);
			}
			default -> System.out.println("bruh");
		}
		previousState = path.get(currentIteration);
	}

	// its a BIG code, don't panic
	private void Animation(ArrayList<Integer> path) throws InterruptedException {

		// to move from 1 tower to another you need 111 px so x = 111
		// to move from bottom position to up you need 130 px so yBottom = -130 (
		// negative because men taht la fo2 )
		// to move from top position to up you need 105 px so yTop = -105

		// now you just need to know where the ractangles are with a switch case.

		// 9 states:
		// big rec | small rec
		// 1 ( B:1 , S:1 )
		// 2 ( B:1 , S:2 )
		// 3 ( B:1 , S:3 )
		// 4 ( B:2 , S:1 )
		// 5 ( B:2 , S:2 )
		// 6 ( B:2 , S:3 )
		// 7 ( B:3 , S:1 )
		// 8 ( B:3 , S:2 )
		// 9 ( B:3 , S:3 )
		int i = currentIteration;
		GraphAnimation(path);
		// now we get current position.
		switch (path.get(i)) {
			case 1 -> {
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 1;
			}
			case 2 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 1;
			}
			case 3 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 1;
			}
			case 4 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 2;
			}
			case 5 -> {
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 2;
			}
			case 6 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 2;
			}
			case 7 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 3;
			}
			case 8 -> {
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 3;
			}
			case 9 -> {
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 3;
			}
			default -> System.out.println("defaultDance.exe1");
		}
		// now we get next position.
		switch (path.get(i + 1)) {
			case 1 -> {
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 1;
			}
			case 2 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 1;
			}
			case 3 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 1;
			}
			case 4 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 2;
			}
			case 5 -> {
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 2;
			}
			case 6 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 2;
			}
			case 7 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 3;
			}
			case 8 -> {
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 3;
			}
			case 9 -> {
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 3;
			}
			default -> System.out.println("defaultDance.exe2");
		}

		// now we do cool math, so say current tower is 1 and next one is 2, do (
		// next-previous ) 2-1 = 1 so you move 1x aka you move 111px
		// and if current tower is 2 and the next one is 1 do ( next-previous ) 1-2 = -1
		// so you move -1x aka -111px.
		// very nice right?

		// conclusion to get the x movement do: next tower - previous tower

		// now lets do the up and down animations math
		// there's 2 stages, going up from current position and going down from up to
		// the next position

		// SO, its actually easy so if current position is 0 then y = -130 and if
		// position is 1 then y = -105
		// ok, now we are in the air and we move left or right and we want to come back
		// down.
		// SO, its also easy, if next position is 0 then y=130 and if position is 1 then
		// y = 105

		// conclusion to get the y movement just use the y we wrote at the beginning ,
		// there's no math involved lol.

		// NOW ANIMATION PART.
		// hmmmm, who should we move you ask? well who ever's turn to move is now.
		// to do that, see who has same value from current to next.

		char currentlyMoving = (currentPosition_Small == nextPosition_Small && currentTower_Small == nextTower_Small) ? 'B' : 'S';

		int movementX = 111;
		int movementY_BottomUp = -130;
		int movementY_TopUp = -105;

		PathTransition moveUpAnimation = new PathTransition();
		moveUpAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
			moveUpAnimation.setPath(new Line(moveUpAnimation.getNode().getTranslateX() + 27.5, moveUpAnimation.getNode().getTranslateY() + 12.5,
					moveUpAnimation.getNode().getTranslateX() + 27.5,
					moveUpAnimation.getNode().getTranslateY() + 12.5 + (currentPosition_Small == 1 ? movementY_TopUp : movementY_BottomUp)));
		} else { // we draw the path of the large disk
			moveUpAnimation.setPath(new Line(moveUpAnimation.getNode().getTranslateX() + 55, moveUpAnimation.getNode().getTranslateY() + 12.5,
					moveUpAnimation.getNode().getTranslateX() + 55,
					moveUpAnimation.getNode().getTranslateY() + 12.5 + (currentPosition_Big == 1 ? movementY_TopUp : movementY_BottomUp)));
		}
		moveUpAnimation.setDuration(Duration.millis(1000));
		moveUpAnimation.setOrientation(PathTransition.OrientationType.NONE);
		moveUpAnimation.play();

		// now for the left or right animation

		PathTransition sideAnimation = new PathTransition();
		sideAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		sideAnimation.setDuration(Duration.millis(1000));
		sideAnimation.setOrientation(PathTransition.OrientationType.NONE);

		moveUpAnimation.setOnFinished(e -> { // after first animation finishes we draw the path of the secon animation
			// and play it
			if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
				sideAnimation.setPath(new Line(sideAnimation.getNode().getTranslateX() + 27.5, sideAnimation.getNode().getTranslateY() + 12.5,
						sideAnimation.getNode().getTranslateX() + 27.5 + (nextTower_Small - currentTower_Small) * movementX,
						sideAnimation.getNode().getTranslateY() + 12.5));
			} else { // we draw the path of the large disk
				sideAnimation.setPath(new Line(sideAnimation.getNode().getTranslateX() + 55, sideAnimation.getNode().getTranslateY() + 12.5,
						sideAnimation.getNode().getTranslateX() + 55 + (nextTower_Big - currentTower_Big) * movementX, sideAnimation.getNode().getTranslateY() + 12.5));
			}
			sideAnimation.play();
		});

		// now for moving down animation

		PathTransition moveDownAnimation = new PathTransition();
		moveDownAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		moveDownAnimation.setDuration(Duration.millis(1000));
		moveDownAnimation.setOrientation(PathTransition.OrientationType.NONE);

		sideAnimation.setOnFinished(e -> { // after first animation finishes we draw the path of the secon animation and
			// play it
			if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
				moveDownAnimation.setPath(new Line(moveDownAnimation.getNode().getTranslateX() + 27.5, moveDownAnimation.getNode().getTranslateY() + 12.5,
						moveDownAnimation.getNode().getTranslateX() + 27.5,
						moveDownAnimation.getNode().getTranslateY() + 12.5 - (nextPosition_Small == 1 ? movementY_TopUp : movementY_BottomUp)));
			} else { // we draw the path of the large disk
				moveDownAnimation.setPath(new Line(moveDownAnimation.getNode().getTranslateX() + 55, moveDownAnimation.getNode().getTranslateY() + 12.5,
						moveDownAnimation.getNode().getTranslateX() + 55,
						moveDownAnimation.getNode().getTranslateY() + 12.5 - (nextPosition_Big == 1 ? movementY_TopUp : movementY_BottomUp)));
			}
			moveDownAnimation.play();
		});
		moveDownAnimation.setOnFinished(e -> {
			try {
				currentIteration++;
				if (currentIteration != path.size() - 1)
					Animation(path);
				else {
					GraphAnimation(path);
					enableAnimation_btns();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	private void ManualAnimation(ArrayList<Integer> path, boolean leftClick) {
		int i = currentIteration;
		int nextTarget;
		GraphAnimation(path);
		// now we get current position.
		switch (path.get(i)) {
			case 1:
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 1;
				break;
			case 2:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 1;
				break;
			case 3:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 1;
				break;
			case 4:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 2;
				break;
			case 5:
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 2;
				break;
			case 6:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 2;
				break;
			case 7:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 1;
				currentTower_Big = 3;
				break;
			case 8:
				currentPosition_Small = 0;
				currentPosition_Big = 0;
				currentTower_Small = 2;
				currentTower_Big = 3;
				break;
			case 9:
				currentPosition_Small = 1;
				currentPosition_Big = 0;
				currentTower_Small = 3;
				currentTower_Big = 3;
				break;
			default:
				System.out.println("defaultDance.exe1");
		}
		// now we get next position.
		if (!leftClick) {
			nextTarget = path.get(i + 1);
		} else {
			nextTarget = path.get(i - 1);
		}
		switch (nextTarget) {
			case 1:
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 1;
				break;
			case 2:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 1;
				break;
			case 3:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 1;
				break;
			case 4:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 2;
				break;
			case 5:
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 2;
				break;
			case 6:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 2;
				break;
			case 7:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 1;
				nextTower_Big = 3;
				break;
			case 8:
				nextPosition_Small = 0;
				nextPosition_Big = 0;
				nextTower_Small = 2;
				nextTower_Big = 3;
				break;
			case 9:
				nextPosition_Small = 1;
				nextPosition_Big = 0;
				nextTower_Small = 3;
				nextTower_Big = 3;
				break;
			default:
				System.out.println("defaultDance.exe2");

		}

		char currentlyMoving = (currentPosition_Small == nextPosition_Small && currentTower_Small == nextTower_Small) ? 'B' : 'S';

		int movementX = 111;
		int movementY_BottomUp = -130;
		int movementY_TopUp = -105;

		PathTransition moveUpAnimation = new PathTransition();
		moveUpAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
			moveUpAnimation.setPath(new Line(moveUpAnimation.getNode().getTranslateX() + 27.5, moveUpAnimation.getNode().getTranslateY() + 12.5,
					moveUpAnimation.getNode().getTranslateX() + 27.5,
					moveUpAnimation.getNode().getTranslateY() + 12.5 + (currentPosition_Small == 1 ? movementY_TopUp : movementY_BottomUp)));
		} else { // we draw the path of the large disk
			moveUpAnimation.setPath(new Line(moveUpAnimation.getNode().getTranslateX() + 55, moveUpAnimation.getNode().getTranslateY() + 12.5,
					moveUpAnimation.getNode().getTranslateX() + 55,
					moveUpAnimation.getNode().getTranslateY() + 12.5 + (currentPosition_Big == 1 ? movementY_TopUp : movementY_BottomUp)));
		}
		moveUpAnimation.setDuration(Duration.millis(1000));
		moveUpAnimation.setOrientation(PathTransition.OrientationType.NONE);
		moveUpAnimation.play();

		// now for the left or right animation

		PathTransition sideAnimation = new PathTransition();
		sideAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		sideAnimation.setDuration(Duration.millis(1000));
		sideAnimation.setOrientation(PathTransition.OrientationType.NONE);

		moveUpAnimation.setOnFinished(e -> { // after first animation finishes we draw the path of the secon animation
			// and play it
			if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
				sideAnimation.setPath(new Line(sideAnimation.getNode().getTranslateX() + 27.5, sideAnimation.getNode().getTranslateY() + 12.5,
						sideAnimation.getNode().getTranslateX() + 27.5 + (nextTower_Small - currentTower_Small) * movementX,
						sideAnimation.getNode().getTranslateY() + 12.5));
			} else { // we draw the path of the large disk
				sideAnimation.setPath(new Line(sideAnimation.getNode().getTranslateX() + 55, sideAnimation.getNode().getTranslateY() + 12.5,
						sideAnimation.getNode().getTranslateX() + 55 + (nextTower_Big - currentTower_Big) * movementX, sideAnimation.getNode().getTranslateY() + 12.5));
			}
			sideAnimation.play();
		});

		// now for moving down animation

		PathTransition moveDownAnimation = new PathTransition();
		moveDownAnimation.setNode(currentlyMoving == 'S' ? small_box_S : large_box_L);
		moveDownAnimation.setDuration(Duration.millis(1000));
		moveDownAnimation.setOrientation(PathTransition.OrientationType.NONE);

		sideAnimation.setOnFinished(e -> { // after first animation finishes we draw the path of the secon animation and
			// play it
			if (currentlyMoving == 'S') { // so if the currently moving is the small disk we draw its path
				moveDownAnimation.setPath(new Line(moveDownAnimation.getNode().getTranslateX() + 27.5, moveDownAnimation.getNode().getTranslateY() + 12.5,
						moveDownAnimation.getNode().getTranslateX() + 27.5,
						moveDownAnimation.getNode().getTranslateY() + 12.5 - (nextPosition_Small == 1 ? movementY_TopUp : movementY_BottomUp)));
			} else { // we draw the path of the large disk
				moveDownAnimation.setPath(new Line(moveDownAnimation.getNode().getTranslateX() + 55, moveDownAnimation.getNode().getTranslateY() + 12.5,
						moveDownAnimation.getNode().getTranslateX() + 55,
						moveDownAnimation.getNode().getTranslateY() + 12.5 - (nextPosition_Big == 1 ? movementY_TopUp : movementY_BottomUp)));
			}
			moveDownAnimation.play();
		});
		moveDownAnimation.setOnFinished(e -> {
			if (leftClick) {
				currentIteration -= 1;
			} else {
				currentIteration++;
			}

			enableAnimation_btns();

			if (currentIteration != path.size() - 1) {
				GraphAnimation(path);
			} else {
				right_arrow_button.setDisable(true);
				GraphAnimation(path);
			}
			if (currentIteration == 0) {
				left_arrow_button.setDisable(true);
				right_arrow_button.setDisable(false);
			}
		});

	}
}
