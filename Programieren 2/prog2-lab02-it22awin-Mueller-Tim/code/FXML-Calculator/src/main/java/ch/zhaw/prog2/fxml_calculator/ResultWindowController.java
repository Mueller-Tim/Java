package ch.zhaw.prog2.fxml_calculator;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Controller for the MainWindow. One controller per mask (or FXML file)
 * Contains everything the controller has to reach in the view (controls)
 * and all methods the view calls based on events.
 * @author
 * @version 2023
 */
public class ResultWindowController {
    private ValueHandler valueHandler;

	@FXML
    private TextArea results;

	@FXML
    private void closeWindow() {
		Stage stage = (Stage) results.getScene().getWindow();
		stage.close();
	}

    public TextArea getResults(){
        return results;
    }

}
