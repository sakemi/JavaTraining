package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AddFontController implements Initializable {
	@FXML
	private Label colorLabel;
	@FXML
	private Label widthLabel;
	@FXML
	private Label numLabel;
	@FXML
	private ColorPicker lineColor;
	@FXML
	private Slider widthSlider;
	@FXML
	private Button clearButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button nextButton;
	@FXML
	private Canvas canvas;

	private GraphicsContext gc;
	private int fileNum = 0;
	private int width = 10;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		lineColor.setValue(Color.BLACK);
		gc.setFill(Color.BLACK);
		widthSlider.setValue(width);
		canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO 自動生成されたメソッド・スタブ
				width = (int) widthSlider.getValue();
				gc.fillOval(event.getX(), event.getY(), width, width);
			}
		});
		numLabel.setText("Drow\"" + fileNum + "\"");
	}

	@FXML
	public void onNextButtonClicked(ActionEvent event) {
		String name = Main.getArg();
		File dir = new File(new StringBuilder().append(System.getProperty("user.dir")).append("/font/").append(name).toString());
		dir.mkdir();
		String path = new StringBuilder().append(System.getProperty("user.dir")).append("/font/").append(name)
				.append("/").append(fileNum).append(".png").toString();
		File f = new File(path);
		WritableImage img = canvas.snapshot(new SnapshotParameters(), null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", f);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		fileNum++;
		gc.clearRect(0, 0, 1000, 1000);
		if(fileNum == 10){
			numLabel.setText("Draw Separator");
		}else{
			numLabel.setText("Drow\"" + fileNum + "\"");
		}

		if(fileNum == 11){
			Main.getInstance().sendPage(Main.Page.TOP);
		}
	}

	@FXML
	public void onClearButtonClicked(ActionEvent event) {
		gc.clearRect(0, 0, 1000, 1000);
	}

	@FXML
	public void onLineColorSelected(ActionEvent event) {
		gc.setFill(lineColor.getValue());
	}

	@FXML
	public void onCancelButtonClicked(ActionEvent event) {
		Main.getInstance().sendPage(Main.Page.TOP);
	}
}
