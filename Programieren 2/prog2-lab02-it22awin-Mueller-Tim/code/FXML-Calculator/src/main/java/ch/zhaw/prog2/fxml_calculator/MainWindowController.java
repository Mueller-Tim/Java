package ch.zhaw.prog2.fxml_calculator;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Controller for the MainWindow. One controller per mask (or FXML file)
 * Contains everything the controller has to reach in the view (controls)
 * and all methods the view calls based on events.
 * @author
 * @version 2023
 */

public class MainWindowController {



    private Stage stageResult = new Stage();

    private ValueHandler valueHandler = new ValueHandler();

    @FXML
    private TextField annualCoast;

    @FXML
    private CheckMenuItem checkAmount;

    @FXML
    private CheckMenuItem checkCosts;

    @FXML
    private CheckMenuItem checkRate;

    @FXML
    private CheckMenuItem checkYears;

    @FXML
    private MenuItem help;

    @FXML
    private TextField initialAmount;

    @FXML
    private TextField numberOfYears;

    @FXML
    private TextArea result;

    @FXML
    private TextField returnRate;

    @FXML
    private void calculate(ActionEvent event) {
        valueHandler.validateAndSetValues(initialAmount.getText(), returnRate.getText(), annualCoast.getText(), numberOfYears.getText());
    }

    @FXML
    private void clearResults(ActionEvent event) {
        valueHandler.clearResult();
    }

    @FXML
    private void clearValues(ActionEvent event) {
        if(checkAmount.isSelected()){
            initialAmount.clear();
        }
        if(checkCosts.isSelected()){
            annualCoast.clear();
        }
        if(checkRate.isSelected()){
            returnRate.clear();
        }
        if(checkYears.isSelected()){
            numberOfYears.clear();
        }
    }

    @FXML
    private void openResultWindow(ActionEvent event) {
        openResultWindow();
    }



    @FXML
    private void showHelp(ActionEvent event) {
        valueHandler.showHelp();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) result.getScene().getWindow();
        stage.close();
        stageResult.close();

    }

    private void openResultWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultWindow.fxml"));
            Pane rootPane = loader.load();
            ResultWindowController resultWindowController = loader.getController();
            initializeResultWindow(resultWindowController);
            Scene scene = new Scene(rootPane);
            stageResult.setScene(scene);
            stageResult.show();
        } catch (IOException e){
            System.out.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    private void initializeResultWindow(ResultWindowController resultWindowController){
        SimpleObjectProperty<TextArea> resultInfo = new SimpleObjectProperty<>(result);
        resultWindowController.getResults().styleProperty().bind(resultInfo.get().styleProperty());
        resultWindowController.getResults().textProperty().bind(resultInfo.get().textProperty());
    }

    public void initialize(){

        help.setAccelerator(KeyCombination.keyCombination("F1"));
        valueHandler.resultProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                result.setText(newValue);
            }
        });

        valueHandler.getValuesState().addListener(new ChangeListener<ValueHandler.ValuesState>() {
            @Override
            public void changed(ObservableValue<? extends ValueHandler.ValuesState> observable, ValueHandler.ValuesState oldValue, ValueHandler.ValuesState newValue) {
                if(newValue.equals(ValueHandler.ValuesState.ERROR)){
                    result.setStyle("-fx-background-color: linear-gradient(#F44708,#A5243D);");
                }
                if(newValue.equals(ValueHandler.ValuesState.OK)){
                    result.setStyle(("-fx-background-color: linear-gradient(#9CEC5B,#C2E812)"));
                }
                if(newValue.equals(ValueHandler.ValuesState.HELP)){
                    result.setStyle("-fx-background-color: linear-gradient(#26F0F1, #52D1DC)");
                }

                if(newValue.equals((ValueHandler.ValuesState.UNDEFINED))){
                    result.setStyle("");
                }
            }
        });
    }
}



