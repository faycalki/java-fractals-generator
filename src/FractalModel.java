/**
 * Model Module for the Application
 * Stores the Model's data and performs logical operations on the data if instructed to by the Controller.
 * @author Faycal Kilali
 * @version 1.1
 * @copyright Copyright (C) 2023 Faycal Kilali
 * @license GNU Public License 3.0
 */

import javafx.scene.effect.BlendMode;

public class FractalModel {

    private BlendMode currentBlendMode;
    private int currentPattern;


    public FractalModel() {
        // Set default values
        currentBlendMode = BlendMode.DIFFERENCE;
        currentPattern = 0;
    }

    /**
     * Accessor Method for BlendMode
     * @return the current blend mode
     */
    public BlendMode getCurrentBlendMode() {
        return currentBlendMode;
    }

    /**
     * Mutator method for BlendMode
     * @param blendMode the Blend Mode to apply
     */
    public void setCurrentBlendMode(BlendMode blendMode) {
        this.currentBlendMode = blendMode;
    }

    /**
     * Accessor Method for the current fractal drawing pattern
     * @return the current pattern to draw
     */
    public int getCurrentPattern() {
        return currentPattern;
    }

    /**
     * Mutator method for the current fractal pattern to draw
     * @param pattern the new fractal pattern to draw
     */
    public void setCurrentPattern(int pattern) {
        this.currentPattern = pattern;
    }

}
