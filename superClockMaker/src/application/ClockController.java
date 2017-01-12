package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import util.PropUtil;

public class ClockController implements Initializable {
	@FXML
	private Canvas canvas;

	@FXML
	private Text messageArea;

	@FXML
	private Button backButton;

	private GraphicsContext gc;
	private Properties prop = new Properties();
	private int x;
	private int y;
	private int size;
	private Color color;
	private Color background;
	private String font;
	private boolean isOrig;
	private final List<Image> fontImages = new ArrayList<>();
	private final Map<Integer, Map<Integer, String>> messageMap = new HashMap<>();
	private String message;
	private ContextMenu menu = new ContextMenu();

	private static final int MESSAGE_DURATION = 10;
	private static final double X = 600;
	private static final double Y = 400;
	private static final int MESSAGE_FONT_SIZE = 30;
	private static final String BACK_LABEL = "Back";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		initByExcistingProperty(Main.getArg());
		initMessage();
		showMessage();
		// initMenu();
		startClock();
	}

	@FXML
	public void onBackClicked(ActionEvent e){
		Main.getInstance().sendPage(Main.Page.TOP);
	}

//	private void initMenu() {
//		List<MenuItem> menuList = new ArrayList<>();
//		MenuItem back = new MenuItem(BACK_LABEL);
//		menuList.add(back);
//
//		menu.getItems().addAll(menuList);
//	}

	private void initMessage() {
		int num = 0;
		while (true) {
			String val = prop.getProperty(Key.START.name().toLowerCase() + num);
			if (val == null) {
				break;
			}
			String[] start = prop.getProperty(Key.START.name().toLowerCase() + num).split(":");
			String[] end = prop.getProperty(Key.END.name().toLowerCase() + num).split(":");
			String mes = prop.getProperty(Key.MESSAGE.name().toLowerCase() + num);
			int startHour = Integer.valueOf(start[0]);
			int startMinute = Integer.valueOf(start[1]);
			int endHour = Integer.valueOf(end[0]);
			int endMinute = Integer.valueOf(end[1]);

			Map<Integer, String> startMap = new HashMap<>();
			for (int i = startMinute; i < 60; i += 15) {
				startMap.put(i, mes);
			}
			messageMap.put(startHour, startMap);

			for (int i = startHour + 1; i < endHour; i++) {
				Map<Integer, String> map = new HashMap<>();
				for (int j = 0; j < 60; j += 15) {
					map.put(j, mes);
				}
				messageMap.put(i, map);
			}

			Map<Integer, String> endMap = new HashMap<>();
			for (int i = 0; i <= endMinute; i += 15) {
				endMap.put(i, mes);
			}
			messageMap.put(endHour, endMap);
			num++;
		}
		LocalDateTime now = LocalDateTime.now();
		int minKey = now.getMinute() - now.getMinute() % 15;
		if (messageMap.get(now.getHour()) == null) {
			return;
		}
		message = messageMap.get(now.getHour()).getOrDefault(minKey, "");
		if (message == null || message.equals("null")) {
			message = "";
		}
	}

	private void showMessage() {
		messageArea.setText(message);
		messageArea.setFont(new Font(MESSAGE_FONT_SIZE));

		TranslateTransition messageTransition = new TranslateTransition(Duration.seconds(MESSAGE_DURATION),
				messageArea);
		messageTransition.setFromX(450);
		messageTransition.setToX(-messageArea.getLayoutBounds().getWidth());
		messageTransition.setInterpolator(Interpolator.LINEAR);
		messageTransition.setCycleCount(TranslateTransition.INDEFINITE);
		messageTransition.play();
	}

	private void startClock() {
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDateTime now = LocalDateTime.now();
				// update message
				if (now.getMinute() % 15 == 0) {
					message = messageMap.get(now.getHour()).getOrDefault(now.getMinute(), "");
					if (message.equals("null")) {
						message = "";
					}
				}
				// draw clock
				gc.setFill(background);
				gc.fillRect(0, 0, X, Y);
				if (isOrig) {
					drawOriginalFontClock(now);
				} else {
					gc.setFill(color);
					gc.fillText(now.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")), Double.valueOf(x),
							Double.valueOf(y));
				}
			}
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	private void initByExcistingProperty(String title) {
		loadProperty(title);
		x = Integer.valueOf(prop.getProperty(Key.X.name().toLowerCase()));
		y = Integer.valueOf(prop.getProperty(Key.Y.name().toLowerCase()));
		size = Integer.valueOf(prop.getProperty(Key.SIZE.name().toLowerCase()));
		color = PropUtil.generateColor(prop.getProperty(Key.COLOR.name().toLowerCase()));
		isOrig = Boolean.valueOf(prop.getProperty(Key.USE_ORIGINAL.name().toLowerCase()));
		font = prop.getProperty(Key.FONT.name().toLowerCase());
		background = PropUtil.generateColor(prop.getProperty(Key.BACKGROUND.name().toLowerCase()));
		updateFont();
	}

	private void updateFont() {
		if (isOrig) {
			loadOriginalFont(font);
		} else {
			gc.setFill(color);
			gc.setFont(new Font(font, size));
		}
	}

	private void loadOriginalFont(String name) {
		fontImages.clear();
		for (int i = 0; i < 11; i++) {
			StringBuilder sb = new StringBuilder();
			String path = sb.append(System.getProperty("user.dir")).append("/font/").append(name).append("/").append(i)
					.append(".png").toString();
			InputStream is;
			try {
				is = new FileInputStream(path);
				if (i == 10) {
					fontImages.add(new Image(is, size / 2, size, false, true));
				} else {
					fontImages.add(new Image(is, size, size, true, true));
				}
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
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

	private void drawOriginalFontClock(LocalDateTime now) {
		int hour1 = now.getHour() / 10;
		int hour2 = now.getHour() % 10;
		int min1 = now.getMinute() / 10;
		int min2 = now.getMinute() % 10;
		int sec1 = now.getSecond() / 10;
		int sec2 = now.getSecond() % 10;
		gc.setFont(new Font(size));
		gc.setFill(color);
		gc.drawImage(fontImages.get(hour1), x, y);
		gc.drawImage(fontImages.get(hour2), x + size, y);
		// gc.fillText(":", x + 2 * size, y + size);
		gc.drawImage(fontImages.get(10), x + 2 * size, y);
		gc.drawImage(fontImages.get(min1), x + 2.5 * size, y);
		gc.drawImage(fontImages.get(min2), x + 3.5 * size, y);
		// gc.fillText(":", x + 4 * size + COLLON_WIDTH, y + size);
		gc.drawImage(fontImages.get(10), x + 4.5 * size, y);
		gc.drawImage(fontImages.get(sec1), x + 5 * size, y);
		gc.drawImage(fontImages.get(sec2), x + 6 * size, y);
	}
}
