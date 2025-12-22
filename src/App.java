import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {

    // bubble firstOne;
    ArrayList<bubble> bubbles;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        // firstOne = new bubble(150, 200, this);
        bubbles = new ArrayList<>();
    }

    public void settings() {
        size(1200, 800);
    }

    public void draw() {
        background(0);
        // firstOne.display();
        // putting it on the screen
        // firstOne.update();
        for (bubble b : bubbles) {
            b.display();
            b.update();
        }

    }

    public void keyPressed() {
        if (key == ' ') {
            // for (int i = 0; i < 10; i++) {
                int x = (int) random(800) +125;
                int y = (int) random(800);
                bubble bubble = new bubble(x, y, this);
                bubbles.add(bubble);
            // }
        }
    }
    public void mousePressed(){
        for (bubble b: bubbles){
            b.checkTouch(mouseX, mouseY);
        }
    }
}
