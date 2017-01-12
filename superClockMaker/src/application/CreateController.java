package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import util.PropUtil;

public class CreateController implements Initializable {
	@FXML
	private TextField inputX;

	@FXML
	private Label labelX;

	@FXML
	private TextField inputY;

	@FXML
	private Label labelY;

	@FXML
	private ComboBox<Integer> sizeCombobox;

	@FXML
	private Label sizeLabel;

	@FXML
	private ColorPicker color;

	@FXML
	private Label colorLabel;

	@FXML
	private ComboBox<String> fonts;

	@FXML
	private Label fontLabel;

	@FXML
	private CheckBox originalFontCheckbox;

	@FXML
	private ComboBox<String> originalFonts;

	@FXML
	private ColorPicker background;

	@FXML
	private Label backgroundLabel;

	@FXML
	private TextField message;

	@FXML
	private Label messageLabel;

	@FXML
	private ChoiceBox<Integer> startHour;

	@FXML
	private ChoiceBox<Integer> startMinute;

	@FXML
	private Label startLabel;

	@FXML
	private ChoiceBox<Integer> endHour;

	@FXML
	private ChoiceBox<Integer> endMinute;

	@FXML
	private Label endLabel;

	@FXML
	private Button addButton;

	@FXML
	private Button clearButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Button okButton;

	@FXML
	private TextField title;

	@FXML
	private Canvas canvas;

	private final Properties prop = new Properties();
	private int messageCnt = 0;
	private final List<Message> messageList = new ArrayList<>();
	private GraphicsContext gc;
	private final List<Image> fontImages = new ArrayList<>();
	private Color fontColor;
	private Color backgroundColor;
	private int highScore;
	private final Properties record = new Properties();

	private static final String DEFAULT_X = "10";
	private static final String DEFAULT_Y = "30";
	private static final int CANVAS_WIDTH = 600;
	private static final int CANVAS_HEIGHT = 400;
	private static final int FONT_MIN = 10;
	private static final int FONT_MAX = 300;
	private static final int FONT_MARGIN = 10;
	private static final int DEFAULT_SIZE = 30;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComboBox();
		gc = canvas.getGraphicsContext2D();
		String title = Main.getArg();
		try {
			record.load(new FileInputStream("score.properties"));
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		highScore = Integer.valueOf(record.getProperty("score"));
		if (title == null) {
			initByDefaultValue();
		} else {
			initByExcistingProperty(title);
		}
		startClock();
	}

	@FXML
	public void onAddButtonClicked(ActionEvent e) {
		messageList.add(new Message(message.getText(), startHour.getValue() + ":" + String.valueOf(startMinute.getValue()),
				endHour.getValue() + ":" + String.valueOf(endMinute.getValue())));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.getDialogPane().setHeaderText("Success");
		alert.getDialogPane().setContentText("New messsage is successfully added.");
		alert.show();
	}

	@FXML
	public void onClearButtonClicked(ActionEvent e) {
		for(Message m : messageList){
			m.clearMessage();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.getDialogPane().setHeaderText("Success");
		alert.getDialogPane().setContentText("All messages are cleared.");
		alert.show();
	}

	@FXML
	public void onCancelButtonClicked(ActionEvent e) {
		Main.getInstance().sendPage(Main.Page.TOP);
	}

	@FXML
	public void onOkButtonClicked(ActionEvent e) {
		if (title.getText().length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.getDialogPane().setHeaderText("Title is empty");
			alert.getDialogPane().setContentText("Please input title.");
			alert.show();
			return;
		}
		saveProperty();
		Main.getInstance().sendPage(Main.Page.TOP);
	}

	@FXML
	public void sizeChanged(ActionEvent e) {
		updateFont();
	}

	@FXML
	public void colorChanged(ActionEvent e) {
		fontColor = color.getValue();
	}

	@FXML
	public void fontChanged(ActionEvent e) {
		updateFont();
	}

	@FXML
	public void originalFontCheckboxStateChanged(ActionEvent e) {
		if (originalFontCheckbox.isSelected()) {
			originalFonts.setDisable(false);
			fonts.setDisable(true);
		} else {
			originalFonts.setDisable(true);
			fonts.setDisable(false);
		}
		updateFont();
	}

	@FXML
	public void originalFontChanged(ActionEvent e) {
		updateFont();
	}

	@FXML
	public void backgroundChanged(ActionEvent e) {
		backgroundColor = background.getValue();
	}

	private void initByDefaultValue() {
		fonts.setValue(Font.getDefault().getName());
		color.setValue(Color.BLACK);
		fontColor = Color.BLACK;
		sizeCombobox.setValue(DEFAULT_SIZE);
		background.setValue(Color.WHITE);
		backgroundColor = Color.WHITE;
		originalFonts.setDisable(true);
	}

	private void initByExcistingProperty(String title) {
		loadProperty(title);
		inputX.setText(prop.getProperty(Key.X.name().toLowerCase()));
		inputY.setText(prop.getProperty(Key.Y.name().toLowerCase()));
		sizeCombobox.setValue(Integer.valueOf(prop.getProperty(Key.SIZE.name().toLowerCase())));
		color.setValue(PropUtil.generateColor(prop.getProperty(Key.COLOR.name().toLowerCase())));
		boolean isOrig = Boolean.valueOf(prop.getProperty(Key.USE_ORIGINAL.name().toLowerCase()));
		originalFontCheckbox.setSelected(isOrig);
		if (isOrig) {
			originalFonts.setValue(prop.getProperty(Key.FONT.name().toLowerCase()));
			fonts.setDisable(true);
		} else {
			fonts.setValue(prop.getProperty(Key.FONT.name().toLowerCase()));
			originalFonts.setDisable(true);
		}
		background.setValue(PropUtil.generateColor(prop.getProperty(Key.BACKGROUND.name().toLowerCase())));
		fontColor = color.getValue();
		backgroundColor = background.getValue();
		initMessage();
		updateFont();
	}

	private void initMessage(){
		int num = 0;
		String start = prop.getProperty(Key.START.name().toLowerCase() + num);
		String end = prop.getProperty(Key.END.name().toLowerCase() + num);
		String message = prop.getProperty(Key.MESSAGE.name().toLowerCase() + num);
		while(start != null){
			messageList.add(new Message(message, start, end));
			num++;
			start = prop.getProperty(Key.START.name().toLowerCase() + num);
			end = prop.getProperty(Key.END.name().toLowerCase() + num);
			message = prop.getProperty(Key.MESSAGE.name().toLowerCase() + num);
		}
	}

	private void saveProperty() {
		fillEmptyValueWithDefault();
		updateProp(Key.X, inputX.getText());
		updateProp(Key.Y, inputY.getText());
		updateProp(Key.SIZE, String.valueOf(sizeCombobox.getValue()));
		updateProp(Key.COLOR, PropUtil.getColorCode(color.getValue()));
		boolean isOrig = originalFontCheckbox.isSelected();
		updateProp(Key.USE_ORIGINAL, String.valueOf(isOrig));
		if (isOrig) {
			updateProp(Key.FONT, originalFonts.getValue());
		} else {
			updateProp(Key.FONT, fonts.getValue());
		}
		updateProp(Key.BACKGROUND, PropUtil.getColorCode(background.getValue()));
//		updateProp(Key.MESSAGE, message.getText());
//		updateProp(Key.START, String.valueOf(startHour.getValue() + String.valueOf(startMinute.getValue())));
//		updateProp(Key.END, String.valueOf(endHour.getValue() + String.valueOf(endMinute.getValue())));
		saveMessage();
		writeProperty();
	}

	private void saveMessage(){
		int num = 0;
		for(Message m : messageList){
			prop.setProperty(Key.START.name().toLowerCase() + num, m.getStart());
			prop.setProperty(Key.END.name().toLowerCase() + num, m.getEnd());
			prop.setProperty(Key.MESSAGE.name().toLowerCase() + num, m.getMessage());
			num++;
		}
	}

	private void fillEmptyValueWithDefault() {
		if (inputX.getText().length() == 0) {
			inputX.setText(DEFAULT_X);
		}
		if (inputY.getText().length() == 0) {
			inputY.setText(DEFAULT_Y);
		}
		if (originalFontCheckbox.isSelected() && originalFonts.getValue() == null) {
			originalFontCheckbox.setSelected(false);
		}
		if (!originalFontCheckbox.isSelected() && fonts.getValue() == null) {
			fonts.setValue(Font.getDefault().getName());
		}

	}

	private void loadProperty(String title) {
		try {
			prop.load(new FileInputStream("conf/" + title + ".properties"));
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void writeProperty() {
		try {
			prop.store(new FileOutputStream("conf/" + title.getText() + ".properties"), null);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void updateProp(Key key, String value) {
		if (key == Key.MESSAGE || key == Key.START || key == Key.END) {
			prop.setProperty(key.name().toLowerCase() + messageCnt, value);
			return;
		}
		prop.setProperty(key.name().toLowerCase(), value);
	}

	private void updateFont() {
		if (originalFontCheckbox.isSelected() && originalFonts.getValue() != null) {
			loadOriginalFont(originalFonts.getValue());
		} else {
			fontColor = color.getValue();
			gc.setFill(fontColor);
			gc.setFont(new Font(fonts.getValue(), sizeCombobox.getValue()));
		}
	}

	private void loadOriginalFont(String name) {
		fontImages.clear();
		double size = sizeCombobox.getValue();
		for (int i = 0; i < 11; i++) {
			StringBuilder sb = new StringBuilder();
			String path = sb.append(System.getProperty("user.dir")).append("/font/").append(name).append("/").append(i)
					.append(".png").toString();
			InputStream is;
			try {
				is = new FileInputStream(path);
				if(i == 10){
					fontImages.add(new Image(is, size / 2, size, false, true));
				}else{
					fontImages.add(new Image(is, size, size, true, true));
				}
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	private void startClock() {
		gc.setFont(new Font(30));
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDateTime now = LocalDateTime.now();
				gc.setFill(backgroundColor);
				gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
				if (originalFontCheckbox.isSelected()) {
					drawOriginalFontClock(now);
				} else {
					gc.setFill(fontColor);
					gc.fillText(now.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
							Double.valueOf(inputX.getText()), Double.valueOf(inputY.getText()));
				}
			}
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	private void drawOriginalFontClock(LocalDateTime now) {
		int hour1 = now.getHour() / 10;
		int hour2 = now.getHour() % 10;
		int min1 = now.getMinute() / 10;
		int min2 = now.getMinute() % 10;
		int sec1 = now.getSecond() / 10;
		int sec2 = now.getSecond() % 10;
		double x = Double.valueOf(inputX.getText());
		double y = Double.valueOf(inputY.getText());
		double size = sizeCombobox.getValue();
		gc.setFont(new Font(size));
		gc.setFill(fontColor);
		gc.drawImage(fontImages.get(hour1), x, y);
		gc.drawImage(fontImages.get(hour2), x + size, y);
		//gc.fillText(":", x + 2 * size, y + size);
		gc.drawImage(fontImages.get(10), x + 2 * size, y);
		gc.drawImage(fontImages.get(min1), x + 2.5 * size, y);
		gc.drawImage(fontImages.get(min2), x + 3.5 * size, y);
		//gc.fillText(":", x + 4 * size + COLLON_WIDTH, y + size);
		gc.drawImage(fontImages.get(10), x + 4.5 * size, y);
		gc.drawImage(fontImages.get(sec1), x + 5 * size, y);
		gc.drawImage(fontImages.get(sec2), x + 6 * size, y);
	}

	private void initComboBox() {
		ObservableList<String> fl = FXCollections.observableArrayList(Font.getFontNames());
		fonts.getItems().addAll(fl);

		String path = System.getProperty("user.dir") + "/font";
		File f = new File(path);
		File[] files = f.listFiles();
		List<String> dirs = new ArrayList<>();
		for (File file : files) {
			dirs.add(file.getName());
		}
		ObservableList<String> ofl = FXCollections.observableArrayList(dirs);
		originalFonts.getItems().addAll(ofl);

		List<Integer> sizes = new ArrayList<>();
		for (int i = FONT_MIN; i < FONT_MAX; i += FONT_MARGIN) {
			sizes.add(i);
		}
		ObservableList<Integer> sl = FXCollections.observableArrayList(sizes);
		sizeCombobox.getItems().addAll(sl);

		List<Integer> hour = new ArrayList<>();
		for(int i = 0; i < 24; i++){
			hour.add(i);
		}
		ObservableList<Integer> ohl = FXCollections.observableArrayList(hour);
		startHour.getItems().addAll(ohl);
		endHour.getItems().addAll(ohl);

		List<Integer> minute = new ArrayList<>();
		for(int i = 0; i < 60; i += 15){
			minute.add(i);
		}
		ObservableList<Integer> oml = FXCollections.observableArrayList(minute);
		startMinute.getItems().addAll(oml);
		endMinute.getItems().addAll(oml);
	}

//	private void disableSecretSettings(){
//		//カスタマイズできる項目を制限する
//	}
//
//	private void unlock(){
//		//highScoreに応じてカスタマイズできるパーツを解禁する
//	}
}
