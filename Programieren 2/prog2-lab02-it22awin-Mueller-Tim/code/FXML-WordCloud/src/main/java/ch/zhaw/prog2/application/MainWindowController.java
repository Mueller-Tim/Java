package ch.zhaw.prog2.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainWindowController {


    @FXML
    private Label lableTitel;

    @FXML
    private TextField textEingabe;

    @FXML
    private TextArea textHistory;

    private WordModelDecorator wordModelDecorator;

    @FXML
    private void hinzufuegenText(ActionEvent event) {
        String text = textEingabe.getText().toLowerCase();
        String[] words = text.split(" ");
        for (String word : words){
            wordModelDecorator.addWord(word);
        }
    }

    @FXML
    private void leerenTextEingabe(ActionEvent event) {
        textEingabe.clear();
    }

    public void initialize(){
        WordModel wordModel = new WordModel();
        wordModelDecorator= new WordModelDecorator(wordModel);
        wordModelDecorator.addListener(new IsObserver() {
            @Override
            public void update() {
                textHistory.clear();
                textHistory.appendText(wordModel.toString());
            }
        });
        lableTitel.textProperty().bind(textEingabe.textProperty());
    }
}
