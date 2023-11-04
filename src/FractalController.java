import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.effect.BlendMode;

/**
 * Controller for the Fractal Art Implementation
 * @author Faycal Kilali
 * @version 1.0
 */
public class FractalController {
    private FractalModel model;
    private FractalView view;

    /**
     * Constructor for the Controller
     * @param model the model to be assigned to the controller
     * @param view the view to be assigned to the controller
     */
    public FractalController(FractalModel model, FractalView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Mutator method to change the Blend Mode of the GraphicsContext object.
     */
    public void changeBlendMode() {
        BlendMode[] blendModes = {
                BlendMode.ADD,
                BlendMode.BLUE,
                BlendMode.COLOR_BURN,
                BlendMode.COLOR_DODGE,
                BlendMode.DARKEN,
                BlendMode.DIFFERENCE,
                BlendMode.EXCLUSION,
                BlendMode.GREEN,
                BlendMode.HARD_LIGHT,
                BlendMode.LIGHTEN,
                BlendMode.MULTIPLY,
                BlendMode.OVERLAY,
                BlendMode.RED,
                BlendMode.SCREEN,
                BlendMode.SOFT_LIGHT,
                BlendMode.SRC_ATOP,
                BlendMode.SRC_OVER
        };
        int currentIndex = java.util.Arrays.asList(blendModes).indexOf(view.getGraphicsContext().getGlobalBlendMode());
        int nextIndex = (currentIndex + 1) % blendModes.length;
        view.getGraphicsContext().setGlobalBlendMode(blendModes[nextIndex]);
    }


    /**
     * Wipes the Canvas using a transparent rectangle
     * @implSpec does not seem to be functional for some reason, worth an investigation.
     */
    public void wipeCanvas() {
        System.out.println("Wiping the canvas...");
        view.getGraphicsContext().clearRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
    }



    /**
     * Passes coordinates to the drawArt method which processes the user input further.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void drawClickEvent(double x, double y) {
        // Draw a rectangle at the clicked point
        drawArt(x, y, 250, 250);
    }

    /**
     * Recursively draws art at the canvas
     *
     *         Algorithm
     *         Base Case: when the width and height is arbitrarily small, stop drawing.
     *         General case: draw the shape(s) and other primary code
     *         Recursive step: callback at smaller width and height.
     *
     * @param x the x coordinate to center the art
     * @param y the y coordinate to center the art
     * @param width the width of the art
     * @param height the height of the art
     */
    private void drawArt(double x, double y, double width, double height) {
        GraphicsContext gc = view.getGraphicsContext();
        drawArtHelper(x, y, width, height, gc); // For optimizaiton purposes, so we don't have to access gc every time.
    }

    /**
     * Helper method for the drawArt method.
     * @implSpec I refrained from re-writing the parameters here, as they'd be redundant.
     */
    private void drawArtHelper(double x, double y, double width, double height, GraphicsContext gc){

        // Base Case
        if (width < 5 | height < 5){
            return;
        }

        // General Case 1 (no overlap)
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, width, height);

        // General Case 2 (overlap/interaction with other fractals)


        // Recursive Step
        drawArtHelper(x, y, width/2, height/2, gc);
    }

}
