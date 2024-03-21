package ch.zhaw.prog2.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage){
        openMainWindow(primaryStage);
    }

    private void openMainWindow(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Pane rootNode = loader.load();
            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.setMinHeight(200);
            stage.setMinWidth(300);
            stage.show();
        } catch (IOException e){
            System.out.println("Error while loading FXML file: " + e.getMessage());
        }

    }
}
