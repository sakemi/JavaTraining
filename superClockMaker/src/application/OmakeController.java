package application;

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
import java.util.Random;
import java.util.ResourceBundle;

import com.sun.javafx.tk.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import util.PropUtil;

public class OmakeController implements Initializable {
	@FXML
	Button endButton;

	@FXML
	Canvas canvas;

	private GraphicsContext gc;
	private Properties prop = new Properties();
	private double x;
	private double y;
	private int size;
	private Color color;
	private Color background;
	private String font;
	private boolean isOrig;
	private final List<Image> fontImages = new ArrayList<>();
	private final BulletManager bulletManager = BulletManager.getInstance();
	private double textWidth = 100;
	private int cnt = 0;
	private int dif = 8;
	private Timeline timer;
	private final Random rand = new Random();
	private int score = 0;
	private boolean isDanger = false;
	private final Properties record = new Properties();
	private int highScore;

	private static final double DEFAULT_X = 290;
	private static final double DEFAULT_Y = 700;
	private static final int SIZE = 10;
	private static final double WIDTH = 600;
	private static final double HEIGHT = 750;
	private static final int ORIGINAL_FONT_CLOCK_WIDTH = SIZE * 7;
	private static final int VEROCITY = 10;
	private static final int VEROCITY_RANGE = 15;
	private static final int SHOT_SIZE = 10;
	private static final int SIZE_RANGE = 30;
	private int timeSpecial = 300;

	@FXML
	public void onEndClicked(ActionEvent e) {
		Main.getInstance().sendPage(Main.Page.TOP);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		gc = canvas.getGraphicsContext2D();
		initByExcistingProperty(Main.getArg());
		canvas.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO 自動生成されたメソッド・スタブ
				x = event.getX();
				y = event.getY();
			}
		});
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
		startClock();
	}

	private void stopBullet() {
		timer.stop();
		timer = null;
		score *= 100;
		if (score > highScore) {
			Alert textIn = new Alert(AlertType.INFORMATION);
			textIn.setTitle("New Record");
			textIn.getDialogPane().setHeaderText("New Record");
			textIn.getDialogPane().setContentText("Your Score:" + score + "\nOld Score:" + highScore);
			textIn.show();
			record.setProperty("score", String.valueOf(score));
			try {
				record.store(new FileOutputStream("score.properties"), null);
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Game Over");
			alert.getDialogPane().setHeaderText("Game Over");
			alert.getDialogPane().setContentText("Your Score: " + score);
			alert.show();
		}
		bulletManager.endGame();
	}

	private void startClock() {
		timer = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDateTime now = LocalDateTime.now();
				// draw clock
				gc.setFill(background);
				gc.fillRect(0, 0, WIDTH, HEIGHT);
				if (isOrig) {
					drawOriginalFontClock(now);
				} else {
					gc.setFill(color);
					String n = now.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
					gc.fillText(n, Double.valueOf(x), Double.valueOf(y));
					textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(n, gc.getFont());
				}

				if (cnt == 800) {
					timeSpecial = 100;
				}

				// draw bullet
				gc.setFill(Color.WHITE);
				gc.fillText("Score:" + (score * 100), 10, 10);
				gc.fillText("HighScore:" + highScore, 100, 10);
				if (cnt % dif == 0) {
					if (cnt < 200) {
						normalShot();
					} else if (cnt < 400) {
						trickShot();
						background = Color.NAVY;
					} else if (cnt < 600) {
						bigShot();
						background = Color.BURLYWOOD;
					} else if (cnt < 800) {
						powerShot();
						background = Color.DARKKHAKI;
					} else {
						powerShot2();
						background = Color.MAROON;
					}
					score++;
				}

				if ((cnt + 20) % timeSpecial == 0) {
					isDanger = true;
				}

				if (cnt % timeSpecial == 0 && cnt != 0) {
					int n = rand.nextInt(4);
					switch (n) {
					case 0:
						specialShot1();
						break;
					case 1:
						specialShot2();
						break;
					case 2:
						specialShot3();
						break;
					case 3:
						specialShot4();
						break;
					}
					score += 50;
					isDanger = false;
				}

				try {
					if (isOrig) {
						bulletManager.draw(gc, new Boundary(x, y, ORIGINAL_FONT_CLOCK_WIDTH, SIZE));
					} else {
						bulletManager.draw(gc, new Boundary(x, y, textWidth, SIZE));
					}
				} catch (Hit e) {
					stopBullet();
				}
				cnt++;
				if (cnt % 150 == 0) {
					if (dif > 2) {
						dif--;
					}
				}

				if (isDanger) {
					Font tmp = gc.getFont();
					gc.setFont(new Font(100));
					gc.setFill(Color.RED);
					gc.fillText("DANGER!!!", 50, 250);
					gc.setFont(tmp);
				}
			}
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	private void normalShot() {
		bulletManager.createBullet(Math.random() * WIDTH, VEROCITY, SHOT_SIZE, SHOT_SIZE);
	}

	private void trickShot() {
		int v = rand.nextInt(VEROCITY_RANGE) + 5;
		bulletManager.createBullet(Math.random() * WIDTH, v, SHOT_SIZE, SHOT_SIZE);
	}

	private void bigShot() {
		int v = rand.nextInt(VEROCITY_RANGE) + 5;
		int w = rand.nextInt(SIZE_RANGE) + 10;
		int h = rand.nextInt(SIZE_RANGE) + 10;
		bulletManager.createBullet(Math.random() * WIDTH, v, w, h);
	}

	private void powerShot() {
		int n = rand.nextInt(15);
		if (n == 0) {
			bulletManager.createBullet(Math.random() * WIDTH, 40, 50, 50);
		} else {
			bigShot();
		}
	}

	private void powerShot2() {
		int n = rand.nextInt(15);
		if (n == 0) {
			bulletManager.createBullet(Math.random() * WIDTH, 50, 80, 80);
		} else {
			bigShot();
		}
	}

	private void specialShot1() {
		for (int i = 0; i < 200; i++) {
			bulletManager.createBullet(i, VEROCITY, SIZE, SIZE);
			bulletManager.createBullet(i + 400, VEROCITY, SIZE, SIZE);
		}
	}

	private void specialShot2() {
		for (int i = 0; i < 700; i++) {
			bulletManager.createBullet(100, -i, VEROCITY, SIZE, SIZE);
			bulletManager.createBullet(400, -i, VEROCITY, SIZE, SIZE);
		}
	}

	private void specialShot3() {
		for (int i = 0; i < 400; i++) {
			bulletManager.createBullet(i, -i, VEROCITY, SIZE, SIZE);
			bulletManager.createBullet(i + 400, -i, VEROCITY, SIZE, SIZE);
		}
	}

	private void specialShot4() {
		for (int i = 0; i < 10; i++) {
			bulletManager.createBullet(Math.random() * WIDTH, 1, SIZE, SIZE);
			bulletManager.createBullet(Math.random() * WIDTH, 50, 1, SIZE, SIZE);
			bulletManager.createBullet(Math.random() * WIDTH, 100, 1, SIZE, SIZE);
		}
	}

	private void initByExcistingProperty(String title) {
		loadProperty(title);
		x = DEFAULT_X;
		y = DEFAULT_Y;
		size = SIZE;
		color = PropUtil.generateColor(prop.getProperty(Key.COLOR.name().toLowerCase()));
		isOrig = Boolean.valueOf(prop.getProperty(Key.USE_ORIGINAL.name().toLowerCase()));
		font = prop.getProperty(Key.FONT.name().toLowerCase());
		background = Color.BLACK;
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
