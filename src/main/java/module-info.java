module com.searchai.towerssearchai {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires javafx.graphics;

	opens com.searchai.towerssearchai to javafx.fxml;

	exports com.searchai.towerssearchai;
}