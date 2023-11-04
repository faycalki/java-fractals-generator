import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
//import javafx.scene.control.Slider;

// For acquiring user monitor dimensions
import java.awt.Toolkit;
import java.awt.Dimension;

// Buttons
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Viewer Module for the application
 * Takes in user input, sends it to Controller for processing
 * Receives instruction from Controller
 * @implSpec Uses MVC (Model, View, Controller) implementation
 * @implNote The Change Blend Mode button and approach appears to require a full re-draw of the canvas in order to change the previous drawings., hence I've opted not to implement it for now. I did, however, leave functional code for it in case one wishes to complete it in the future.
 * @author Faycal Kilali
 * @version 1.1
 * @copyright Copyright (C) 2023 Faycal Kilali
 * @license GNU Public License 3.0
 */

public class FractalView extends Application {

    /*
    GraphicsContext issues draw calls to the Canvas object using a buffer.
     */

    private FractalModel model;
    private FractalController controller;

    private Canvas canvas;
    private GraphicsContext gc;


    // Button
    private VBox root;
    private Button changeBlendModeButton;
    private Button clearCanvasButton;
    private Button changePatternButton;

    /**
     * Serves as a receiver for the User Input and also expresses to the user a GUI relevant to our uses.
     * Additionally, serves as an entry to the JavaFX portion of the program.
     *
     * @param primaryStage the stage
     */
    @Override
    public void start(Stage primaryStage) {
        model = new FractalModel();
        controller = new FractalController(model, this);

        // Appropriately sizing
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double monitorWidth = screenSize.getWidth();
        double monitorHeight = screenSize.getHeight();
        System.out.println("Detected primary monitor dimensions: " + monitorWidth + " x " + monitorHeight);

        Group root = new Group();
        canvas = new Canvas(monitorWidth, monitorHeight);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        // Some customization of the GraphicsContext, default values wise.
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.setGlobalAlpha(0.5); // Translucency


        // Button for BlendMode
        changeBlendModeButton = new Button("Change Blend Mode (applies to successive draws)");
        // Button for clearing canvas
        clearCanvasButton = new Button("Clear Canvas"); // Currently unfunctional
        // Button to change pattern
        changePatternButton = new Button("Change fractal drawing pattern");



        VBox buttonContainer = new VBox(10); // 10 is the spacing between buttons
        buttonContainer.getChildren().addAll(changeBlendModeButton, clearCanvasButton, changePatternButton);
        root.getChildren().add(buttonContainer);

        // Scene
        Scene scene = new Scene(root, monitorWidth, monitorHeight, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java Fractal Generator by Faycal Kilali");
        primaryStage.show();

        // We'll use the method of the current instance, hence "this", as the callback for the event.
        canvas.setOnMouseClicked(this::drawClickEvent);
        changeBlendModeButton.setOnMouseClicked(this::ChangeBlendModeEvent);
        clearCanvasButton.setOnMouseClicked(this::wipeCanvas);
        changePatternButton.setOnMouseClicked(this::changePattern);

    }


    /**
     * Entry-point for the Java Program
     *
     * @param args arguments to pass through the terminal
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Accessor Method for the Canvas
     *
     * @return the active Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Accessor Method for the GraphicsContext
     *
     * @return the active GraphicsContext object
     */
    public GraphicsContext getGraphicsContext() {
        return gc;
    }


    /**
     * Prints current coordinates that are clicked and also passes the user input to the Controller.
     *
     * @param event the mouse click user input.
     */
    private void drawClickEvent(MouseEvent event) {
        System.out.println("Coordinates: x = " + (int) event.getX() + ", y = " + (int) event.getY());

        // Notify the controller about the mouse click
        controller.drawClickEvent(event.getX(), event.getY());
    }

    /**
     * This simply chooses the action to be made when the change blend mode button is pressed.
     */
    public void ChangeBlendModeEvent(MouseEvent event) {
        System.out.println("Current Blend Mode: " + gc.getGlobalBlendMode());
        controller.changeBlendMode();
    }

    /**
     * This simply chooses the action to be made when the change blend mode button is pressed.
     */
    public void wipeCanvas(MouseEvent event) {
        controller.wipeCanvas();
    }

    /**
     * This changes the fractal pattern
     */
    public void changePattern(MouseEvent event) {
        controller.changePattern();
    }

}