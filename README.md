# Java Fractal Generator

## Project Overview

Java Fractal Generator is an elegant and interactive application designed for creating captivating fractal art. The application leverages JavaFX and follows the MVC (Model-View-Controller) architecture to provide a seamless user experience.

## Key Features

- **Dynamic Blend Modes:** Cycle through a variety of blend modes for each successive drawing.
- **Canvas Management:** Clear the canvas effortlessly to start a new masterpiece.
- **Fractal Patterns:** Explore more than 2 different fractal drawing patterns.
- **Responsive Design:** Automatically adapted to the user's monitor dimensions, ensuring an optimal viewing experience.

## Getting Started

### Prerequisites

- Java 8 or later installed.
- JavaFX library.

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/faycalki/java-fractal-generator.git
   cd fractal-generator
   ```
   
2. **Compile the entry point**:
   ```bash
   javac FractalView.java
   java FractalView
   ```


### Usage

- **Change Blend Mode:** Click the "Change Blend Mode" button to gracefully transition between blend modes.
- **Clear Canvas:** Click the "Clear Canvas" button to reset the canvas for a fresh start.
- **Change Pattern:** Click the "Change Fractal Pattern" button to explore different drawing patterns.
- **Create Art:** Click on the canvas to generate mesmerizing, fractal art. 
- **Adjustable Fractals:** Easily change the widths, lengths of the initial recursions for diverse results from the fractal patterns.
- **Responsive Fractals**: The fractals are partially translucent and are responsive to other fractals that they may interact with, if the blending mode and colours allow so.

## Screenshots
<p align="center">
  <img src="/images/fractal1.png" alt="Fractal Drawing" width="70%">
  <br>
  <em>Fig. 1: A captivating fractal drawing using one of the drawing patterns</em>
</p>

<p align="center">
  <img src="/images/fractal2.png" alt="Fractal Drawing" width="70%">
  <br>
  <em>Fig. 2: A captivating fractal drawing using a different drawing pattern</em>
</p>

<p align="center">
  <img src="/images/fractal3.png" alt="Fractal Drawing" width="70%">
  <br>
  <em>Fig. 3: Another fractal drawing of another drawing pattern</em>
</p>

<p align="center">
  <img src="/images/buttons.png" alt="Blend Mode Change" width="70%">
  <br>
  <em>Fig. 4: Effortlessly change blend modes, clear canvas, and or change fractal patterns</em>
</p>


## UML Diagram

<p align="center">
  <img src="/images/UML_Diagram.png" alt="UML Diagram" width="80%">
   <br>
   <em> Fig. 5: UML Diagram of the project, showcasing the MVC Architecture.</em>
</p>

# License
This project is licensed under the GNU License - see the LICENSE file for details.

# Disclaimer
This project contains a disclaimer - see the DISCLAIMER file for details.