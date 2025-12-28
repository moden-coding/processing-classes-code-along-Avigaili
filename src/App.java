import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {

    // bubble firstOne;
    ArrayList<bubble> bubbles;
    double timer;
    int scene;
    double highScore;
    double gameStart;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        // readHighScore();
        // firstOne = new bubble(150, 200, this);
        bubbles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bubbleMaker();
        }
        scene = 0;
        gameStart = millis();
    }

    public void settings() {
        size(1200, 800);
    }

    public void draw() {
        background(0);
        if (scene == 0) {
            // firstOne.display();
            // putting it on the screen
            // firstOne.update();
            for (bubble b : bubbles) {
                b.display();
                b.update();
            }
            fill(255);
            textSize(50);
            timer = millis() - gameStart;
            timer = ((int) timer / 100) / 10.0;
            text("" + timer, width - 100, 50);
            if (bubbles.size() == 0) {
                scene = 1;
                readHighScore();

                if (highScore == 0 || highScore > timer) {
                    highScore = timer;
                    saveHighScore();
                }
            }
        } else {
            text("Score: " + timer, 400, 400);
            text("High Score: " + highScore, 400, 500);
        }

    }

    public void saveHighScore() {

        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highScore); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highScore = Double.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void bubbleMaker() {
        int x = (int) random(800) + 125;
        int y = (int) random(800);
        bubble bubble = new bubble(x, y, this);
        bubbles.add(bubble);
    }

    public void keyPressed() {
        if (key == ' ') {
            // for (int i = 0; i < 10; i++) {
            // int x = (int) random(800) +125;
            // int y = (int) random(800);
            // bubble bubble = new bubble(x, y, this);
            // bubbles.add(bubble);
            // this was moved into bubble maker
            // }
            if (scene == 0) {
                bubbles.clear();
            } else {
                setup();
            }

        }
        if (key == 's') {
            saveFile();
        }
        if (key == 'l') {
            loadFile();
        }
    }

    public void saveFile() {

        String filePath = "saveFile.txt"; // Path to the text file

        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (bubble b : bubbles) {
                writer.println(b.saveInfo());
            }

            writer.close(); // Closes the writer and saves the file
            System.out.println("Integer saved to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

    public void loadFile() {
        bubbles.clear();
        try (Scanner scanner = new Scanner(Paths.get("saveFile.txt"))) {
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                bubble temp = new bubble(row, this);
                bubbles.add(temp);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mousePressed() {
        for (bubble b : bubbles) {
            if (b.checkTouch(mouseX, mouseY) == false)
                ;
        }
    }
}
