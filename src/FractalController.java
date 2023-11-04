import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.effect.BlendMode;

/**
 * Controller Module for the Application
 * Processes user Input from View and sends orders to Model. Updates View.
 * @author Faycal Kilali
 * @version 1.1
 * @copyright Copyright (C) 2023 Faycal Kilali
 * @license GNU Public License 3.0
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
        int currentIndex = java.util.Arrays.asList(blendModes).indexOf(model.getCurrentBlendMode());
        int nextIndex = (currentIndex + 1) % blendModes.length;
        model.setCurrentBlendMode(blendModes[nextIndex]);
    }


    /**
     * Wipes the Canvas using a transparent rectangle
     * @implSpec does not seem to be functional for some reason, worth an investigation.
     */
    public void wipeCanvas() {
        System.out.println("Wiping the canvas...");
        view.getGraphicsContext().save();
        view.getGraphicsContext().clearRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
        view.getGraphicsContext().restore();
    }

    /**
     * Controller for changing the pattern
     */
    public void changePattern(){
        int drawPattern = model.getCurrentPattern();
        if (drawPattern == 0){
            model.setCurrentPattern(1);
        }
        else if (drawPattern == 1){
            model.setCurrentPattern(2);
        }
        else if (drawPattern == 2){
            model.setCurrentPattern(0);
        }
    }

    /**
     * Passes coordinates to the drawArt method, as well as the width and height of the initial recursion.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void drawClickEvent(double x, double y) {
        drawArt(x, y, 100, 100);
    }

    /**
     * Processes user input, makes a decision on the fractal pattern to draw, and passes important parameters to the recursive method.
     * This method results in recursively drawn art at the canvas by consequence of the recursive methods it calls.
     * @implNote Rather than using Depth, I've used the fact that if the width or height is less than 1, then we stop drawing, as we can't see that shape due to it being smaller than a pixel in area.
     *
     * @param x the x coordinate to center the art
     * @param y the y coordinate to center the art
     * @param width the width of the art
     * @param height the height of the art
     */
    private void drawArt(double x, double y, double width, double height) {
        GraphicsContext gc = view.getGraphicsContext();

        // Randomize color of Stroke
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        gc.setStroke(new Color(red, green, blue, 0.8));

        if (model.getCurrentPattern() == 0){
            // Default pattern
            drawArtPatternOne(x, y, width, height, gc);

        }

        else if (model.getCurrentPattern() == 1){
            drawArtPatternTwo(x, y, width, 0, gc);
            // Second pattern
        }

        else if (model.getCurrentPattern() == 2){
            drawArtPatternThree(x, y, width, height, gc);
            // Second pattern
        }

    }

    /**
     * Recursively draws ever-smaller equilateral triangles inside a main equilaterial triangle.
     *      Base Case: when the width and height is small enough to our constraint, stop drawing.
     *      General case: assign the vertices of the main equilaterial triangle such that each successive draw ends up with the top-vertex of the smaller triangles tangent to the appropriate bottom vertex. Additionally, draw the main equilateral triangle and the two equilaterla triangles that are connected to the bottom-vertices.
     *      Recursive step: callback at smaller width and height.
     * @implSpec I refrained from re-writing the parameters here, as they'd be redundant.
     */
    private void drawArtPatternOne(double x, double y, double width, double height, GraphicsContext gc){

        // Base Case
        if (width < 1 || height < 1){
            return;
        }

        // General Case
        // Top vertex
        double x1 = x;
        double y1 = y;

        // Bottom-left vertex
        double x2 = x - width ;
        double y2 = y + Math.sqrt(3) * height ;


        // Bottom-right vertex
        double x3 = x + width ;
        double y3 = y + Math.sqrt(3) * height ;

        // Stroke the polygon at those points
        double[] xSet = new double[]{x1, x2, x3};
        double[] ySet = new double[]{y1, y2, y3};
        gc.strokePolygon(xSet, ySet, 3);

        // Recursive Step (a respective call for each corner)
        drawArtPatternOne(x1, y1, width / 2, height/2, gc);
        drawArtPatternOne(x2, y2, width / 2, height/2, gc);
        drawArtPatternOne(x3, y3, width / 2, height/2, gc);
    }

    /**
     * Recursively draws a optical-illusion like-shape. Somewhat resembling a tree.
     *      Algorithm
     *      Base Case: when length is less than 4, stop drawing.
     *      General case: draw the shape from current position to endX and endY, execute further recursive draws at angled positions
     *      Recursive step: callback at smaller length and angle
     * @implSpec I refrained from re-writing the parameters here, as they'd be redundant.
     */
    private void drawArtPatternTwo(double x, double y, double length, double angle, GraphicsContext gc){

        // Base Case
        if (length < 3) {
            return;
        }

        // General Case
        double endX = x + length * Math.cos(angle);
        double endY = y + length * Math.sin(angle);

        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(endX, endY);
        gc.stroke();

        // Recursive Step
        drawArtPatternTwo(endX, endY, length * 0.8, angle + Math.PI / 6, gc);
        drawArtPatternTwo(endX, endY, length * 0.8, angle - Math.PI / 6, gc);
    }


    /**
     * Recursively draws 8 squares inside any given square
     *      Algorithm
     *      Base Case: when the width and height is conditionally small, stop drawing.
     *      General case: draw the shape(s) and other primary code
     *      Recursive step: callback at smaller width and height.
     * @implSpec I refrained from re-writing the parameters here, as they'd be redundant.
     */
    private void drawArtPatternThree(double x, double y, double width, double height, GraphicsContext gc){

        // Randomize color of Stroke
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        gc.setStroke(new Color(red, green, blue, 0.8));

        // Base Case
        if (width < 3 || height < 3) {
            return;
        }

        // General Case
        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + width, y);
        gc.lineTo(x + width, y + height);
        gc.lineTo(x, y + height);
        gc.closePath();
        gc.stroke();

        // Recursive Step
        drawArtPatternThree(x, y, width / 2, height / 2, gc);
        drawArtPatternThree(x + width / 2, y, width / 2, height / 2, gc);
        drawArtPatternThree(x + width / 2, y + height / 2, width / 2, height / 2, gc);
        drawArtPatternThree(x, y + height / 2, width / 2, height / 2, gc);
    }
}
