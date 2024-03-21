package ch.zhaw.prog2.calculator;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Main Application. Controller and View in the same class
 * Other options:
 * - Separating the start from the window classes
 * - Separating view and Controller
 * @author bles
 *
 */
public class Main extends Application {
	private static final int VERTICAL_GAP = 5;
	private static final int HORIZONTAL_GAP = 10;

    private static final String INFO = """
        Enter valid values to
        - Initial amount (> 0)
        - Return in % (can be +/- or 0)
        - Annual Costs (> 0)
        - Number of years (> 0)
        Calculate displays the annual balance development!";
        """;

	private Stage primaryStage;
	private final CheckMenuItem menuClearInitialAmount = new CheckMenuItem("Initial amount");
	private final CheckMenuItem menuClearReturnInPercent = new CheckMenuItem("Return in %");
	private final CheckMenuItem menuClearAnnualCosts = new CheckMenuItem("Annual Costs");
	private final CheckMenuItem menuClearNumberOfYears = new CheckMenuItem("Number of years");
    private final MenuItem menuClearValues = new MenuItem("Clear values");
    private final MenuItem menuClearResult = new MenuItem("Clear result");
	private final TextField initialAmount = new TextField();
	private final TextField returnInPercent = new TextField();
	private final TextField annualCost = new TextField();
	private final TextField numberOfYears = new TextField();
	private final TextArea results = new TextArea();


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	/*
	 * Create the window, call methods to create the different parts of the
	 * scene graph. Put the parts together in appropriate panes.
	 */
	private void mainWindow() {
		try {
			BorderPane rootPane = new BorderPane();
			// BorderPane top
	        MenuBar menuBar = new MenuBar();
	        createMenu(menuBar);
	        // BorderPane left
	        // two rows for grid (inputPanel) and other VBox (resultRows)
			VBox inputOutputPanel = new VBox(VERTICAL_GAP);
			GridPane inputPanel = new GridPane();
			VBox resultRows = new VBox();
			inputOutputPanel.getChildren().add(inputPanel);
			inputOutputPanel.getChildren().add(resultRows);
			createInputPanel(inputPanel);
			createResultRows(resultRows);
 			// BorderPane bottom
			HBox buttons = new HBox(HORIZONTAL_GAP);
			buttons.setAlignment(Pos.BASELINE_CENTER);
			createButtons(buttons);

			// set up root border pane
			rootPane.setTop(menuBar);
			rootPane.setBottom(buttons);
			rootPane.setCenter(inputOutputPanel);

			// Create scene with root node with size
			Scene scene = new Scene(rootPane, 600, 400);
			// scene.getStylesheets().add(getClass().getResource("MyLabel.css").toExternalForm());
			primaryStage.setMinWidth(280);
			// Set stage properties
			primaryStage.setTitle("Return on Investment Calculator");
			// Add scene to the stage and make it visible
			primaryStage.setScene(scene);
			primaryStage.show();

			// Connect height of the result-area to the height of the scene
			scene.heightProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					results.setPrefHeight((double) newValue);
				}
			});

		} catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
		}
	}

	/*
	 * Add the title and the result TextArea to the VBox
	 */
	private void createResultRows(VBox resultRows) {
		resultRows.getChildren().add(new Label("Results:"));
		resultRows.getChildren().add(results);
		resultRows.setPadding(new Insets(VERTICAL_GAP, HORIZONTAL_GAP, VERTICAL_GAP, HORIZONTAL_GAP));
        results.setFont(Font.font("Monospaced", FontWeight.BLACK, 13));
	}

	/*
	 * 4 rows in a GridPane with row-title and input TextField
	 */
	private void createInputPanel(GridPane inputPanel) {
		inputPanel.setVgap(VERTICAL_GAP);
		inputPanel.setHgap(HORIZONTAL_GAP);
		inputPanel.add(new Label("Initial amount"), 0, 1);
		inputPanel.add(new Label("Return rate in %"), 0, 2);
		inputPanel.add(new Label("Annual cost"), 0, 3);
		inputPanel.add(new Label("Number of years"), 0, 4);
		inputPanel.add(initialAmount, 1, 1);
		inputPanel.add(returnInPercent, 1, 2);
		inputPanel.add(annualCost, 1, 3);
		inputPanel.add(numberOfYears, 1, 4);
		inputPanel.setPadding(new Insets(VERTICAL_GAP, HORIZONTAL_GAP, VERTICAL_GAP, HORIZONTAL_GAP));
	}

	/*
	 * Create menu for the top-area of the BorderPane
	 */
	private void createMenu(MenuBar menu) {
        // set menu clear event handlers using an inner class (see below)
        ClearHandler clearHandler = new ClearHandler();
        menuClearValues.setOnAction(clearHandler);
        menuClearResult.setOnAction(clearHandler);

        // create help MenuItem and add help event handler as an anonymous inner class
        MenuItem menuShowHelp = new MenuItem("Show help");
        menuShowHelp.setAccelerator(KeyCombination.keyCombination("F1"));
        menuShowHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showResult(INFO, true, Color.BLUE);
            }
        });

        // create the menu entries
        Menu clearMenu = new Menu("Clear");
        Menu helpMenu = new Menu("?");

        // add MenuItems to the menu entries
		clearMenu.getItems().addAll(menuClearInitialAmount, menuClearReturnInPercent, menuClearAnnualCosts, menuClearNumberOfYears);
		clearMenu.getItems().addAll(new SeparatorMenuItem(), menuClearValues, new SeparatorMenuItem(), menuClearResult);
		helpMenu.getItems().add(menuShowHelp);

        // add menu entries to the menu bar
		menu.getMenus().addAll(clearMenu, helpMenu);
	}

	/*
	 * Create buttons in the HBox inside the bottom pane of the BorderPane
	 */
	private void createButtons(HBox buttons) {
        // Define the close button and use a lambda expression as event handler
		Button closeButton = new Button("Close");
		closeButton.setOnAction(event -> Platform.exit());
        closeButton.setCancelButton(true);

        // Define the calculate button using an anonymous inner class as event handler
        Button calculateButton = new Button("Calculate");
        calculateButton.setDefaultButton(true);
		calculateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ValueHandler valueHandler = new ValueHandler();
				valueHandler.validateAndSetValues(
				    initialAmount.getText(),
                    returnInPercent.getText(),
                    annualCost.getText(),
                    numberOfYears.getText());
				String result = valueHandler.getResult();
                Color borderColor = switch (valueHandler.getValuesState()) {
                    case UNDEFINED -> Color.TRANSPARENT;
                    case OK -> Color.GREEN;
                    case ERROR -> Color.RED;
                };
                showResult(result, true, borderColor);
			}
		});

        // add the buttons to the HBox
		buttons.getChildren().addAll(calculateButton, closeButton);
		buttons.setPadding(new Insets(VERTICAL_GAP, HORIZONTAL_GAP, VERTICAL_GAP, HORIZONTAL_GAP));
	}

	/*
	 * Show text in the result box and set the border color
	 */
	private void showResult(String text, boolean clearFirst, Color borderColor) {
		if(clearFirst) {
			results.setText(text);
		} else {
			results.appendText("\n");
			results.appendText(text);
		}
		results.setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
	}

	/*
	 * Handler to clear the controls.
	 * Example how to use a single handler for multiple controls.
	 */
	private class ClearHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
            if (event.getSource() == menuClearValues) {
                if (menuClearInitialAmount.isSelected()) {
                    initialAmount.clear();
                }
                if (menuClearAnnualCosts.isSelected()) {
                    annualCost.clear();
                }
                if (menuClearNumberOfYears.isSelected()) {
                    numberOfYears.clear();
                }
                if (menuClearReturnInPercent.isSelected()) {
                    returnInPercent.clear();
                }
            } else if (event.getSource() == menuClearResult) {
                results.clear();
            } else {
                System.out.println("Unknown event source: " + event.getSource());
            }
		}
	}

}

