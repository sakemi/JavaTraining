package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;

public final class Main extends Application {
	private static Main instance;
	private static String arg;
	private Parent root;
	private Stage stage;

	public Main(){
		if(instance != null){
			throw new IllegalStateException();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		stage = primaryStage;
		root = FXMLLoader.load(getClass().getResource(Page.TOP.source));
		stage.setTitle(Page.TOP.title);
		stage.setScene(new Scene(root, Page.TOP.height, Page.TOP.width));
		stage.show();
	}

	public void sendPage(Page page) {
		Main.arg = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page.source));
			stage.setTitle(page.title);
			stage.setScene(new Scene(root, page.height, page.width));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void sendPage(Page page, String arg){
		Main.arg = arg;
		try {
			root = FXMLLoader.load(getClass().getResource(page.source));
			stage.setTitle(page.title);
			stage.setScene(new Scene(root, page.height, page.width));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static Main getInstance() {
		return instance;
	}

	public static String getArg(){
		return arg;
	}

	public void popUp(ContextMenu m, double x, double y){
		m.show(stage, x, y);
	}

	public static void main(String[] args) {
		launch(args);
	}

	// TODO:インナークラスよりトップレベルのほうが良さげ
	public enum Page {
		TOP("top.fxml", 600, 800, "Super Clock Maker NX"),
		SELECT("select.fxml", 600, 430, "Select Clock"),
		CREATE("create.fxml", 800, 480, "Create"),
		ADD_FONT("addFont.fxml", 600, 400, "Font Maker"),
		OMAKE("omake.fxml",600, 800, "Omake");

		private final String source;
		private final int height;
		private final int width;
		private final String title;

		private Page(String source, int height, int width, String title) {
			this.source = source;
			this.height = height;
			this.width = width;
			this.title = title;
		}
	}
}
