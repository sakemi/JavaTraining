package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class TopController implements Initializable {
	@FXML
	private Button createButton;

	@FXML
	private Button displayButton;

	@FXML
	private Button addFontButton;

	@FXML
	private Button editFontButton;

	@FXML
	private Button omakeButton;

	@FXML
	private TextField fontTitle;

	@FXML
	private ComboBox<String> clockComboBox;

	@FXML
	private Canvas canvas;

	/**splash img path**/
	private String path = getClass().getResource("scm.png").toExternalForm();

	/**splash**/
	private Image image = new Image(path);

	private GraphicsContext scm;

	@FXML
	public void onCreateButtonClicked(ActionEvent event) {
		Main.getInstance().sendPage(Main.Page.CREATE);
	}

	@FXML
	public void onDisplayButtonClicked(ActionEvent display) {
		if (clockComboBox.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.getDialogPane().setHeaderText("Clock is not selected");
			alert.getDialogPane().setContentText("Please select clock.");
			alert.show();
			return;
		}
		Main.getInstance().sendPage(Main.Page.SELECT, clockComboBox.getValue());
	}

	@FXML
	public void onEditButtonClicked(ActionEvent e) {
		if (clockComboBox.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.getDialogPane().setHeaderText("Clock is not selected");
			alert.getDialogPane().setContentText("Please select clock.");
			alert.show();
			return;
		}
		Main.getInstance().sendPage(Main.Page.CREATE, clockComboBox.getValue());
	}

	@FXML
	public void onAddFontButtonClicked(ActionEvent event) {
		if (fontTitle.getText().length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.getDialogPane().setHeaderText("Title is empty");
			alert.getDialogPane().setContentText("Please input title.");
			alert.show();
			return;
		}
		Main.getInstance().sendPage(Main.Page.ADD_FONT, fontTitle.getText());
	}

	@FXML
	public void onOmakeButtonClicked(ActionEvent e){
		if (clockComboBox.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.getDialogPane().setHeaderText("Clock is not selected");
			alert.getDialogPane().setContentText("Please select clock.");
			alert.show();
			return;
		}
		Main.getInstance().sendPage(Main.Page.OMAKE, clockComboBox.getValue());
	}

	private void initComboBox() {
		String path = System.getProperty("user.dir") + "/conf";
		File f = new File(path);
		File[] files = f.listFiles();
		List<String> dirs = new ArrayList<>();
		for (File file : files) {
			dirs.add(file.getName().replace(".properties", ""));
		}
		ObservableList<String> ol = FXCollections.observableArrayList(dirs);
		clockComboBox.getItems().addAll(ol);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自動生成されたメソッド・スタブ
		scm = canvas.getGraphicsContext2D();
		scm.drawImage(image, 0, 0);
		initComboBox();
	}
}
