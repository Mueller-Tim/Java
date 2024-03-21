package ch.zhaw.prog2.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private final WordModel wordModel = new WordModel();

	@Override
	public void start(Stage primaryStage) throws Exception {
		openMainWindow(primaryStage);
	}

	private void openMainWindow(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Pane rootNode = loader.load();

 			MainWindowController mainWindowController = loader.getController();
			mainWindowController.setWordModel(wordModel);

			Scene scene = new Scene(rootNode);

            stage.setScene(scene);
            stage.show();
		} catch(Exception e) {
            System.out.println("Failed to load FXML resource: " + e.getMessage());
		}

	}
}
