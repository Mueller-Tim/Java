package ch.zhaw.prog2.fxml_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main-Application. Opens the first window (MainWindow) and the common ValueHandler
 * @author
 * @version 2023
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage){
		mainWindow(primaryStage);
	}

	private void mainWindow(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Pane rootNode = loader.load();
            Scene scene = new Scene(rootNode);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(300);
            primaryStage.setMinHeight(200);
            primaryStage.setTitle("Return on Investment Calculator");
            primaryStage.show();
        } catch (IOException e){
            System.out.println("Error while loading FXML file: " + e.getMessage());
        }

	}

}

