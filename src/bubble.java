import processing.core.PApplet;

public class bubble {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;
    private int health;

    public bubble(int xPos, int yPos, PApplet c) {
        x = xPos;
        y = yPos;
        size = 100;
        canvas = c;
        // anything above here is just a basic code! and the PApplet makes it so the
        // bubble knows abt the screem
        speed = 5;
        //color =randomColor();
        color=canvas.color(0,225,0);
        health = 3;
    }

    public void display() {
        canvas.fill(color);
        canvas.circle(x, y, size);
        // like making the bubble on the screen
        if (health ==2){
            color = canvas.color(255,255,0);
        }
        else if (health ==1){
            color = canvas.color(255,0,0);
        }
    }

    public void update() {
        x += speed;
        if (x+ size/2>canvas.width || x-size/2 <0){
            speed =-speed;
        //    color =randomColor();
            health--;
        }
    }
    public int randomColor(){
        return color =canvas.color(canvas.random(255),canvas.random(255),canvas.random(255));
    }
    public int getSize(){
        return size;
    }
    public void checkTouch(int mouseX, int mouseY){
        float distanceFromCenter = canvas.dist(x,y,mouseX,mouseY);
        if(distanceFromCenter<size/2){
        health--;
        }
    }
}
