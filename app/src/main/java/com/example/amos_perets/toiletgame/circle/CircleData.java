package com.example.amos_perets.toiletgame.circle;

import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import static com.example.amos_perets.toiletgame.circle.CircleCustomView.DELTA_BORDER;

public class CircleData extends AppCompatActivity {

    private final int RADIUS      = 200;
    private int WIDTH;
    private int HEIGHT;

    private int x;
    private int y;
    private int radius;
    private int color;
    private boolean correctCircle;
    private Random random = new Random();

    public CircleData(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        generateRandomXYAndRadius();
        correctCircle = false;
    }

    public boolean isCorrectCircle() {
        return correctCircle;
    }

    public void setCorrectCircle(boolean correctCircle) {
        this.correctCircle = correctCircle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x - radius <= 0){
            this.x = radius + DELTA_BORDER;
        } else if(x + radius  >= WIDTH){
            this.x = WIDTH - radius;
        } else {
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y - radius <= 0){
            this.y = radius + DELTA_BORDER;
        } else if(y + radius >= HEIGHT){
            this.y = HEIGHT - radius;
        } else {
            this.y = y ;
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private void generateRandomRadius() {
        setRadius(random.nextInt(RADIUS) + 30);
    }

    private void generateRandomY() {
//        setY(random.nextInt(HEIGHT - (radius*2) ) + radius);
        setY(random.nextInt(HEIGHT - radius) + radius);
    }

    private void generateRandomX() {
//        setX(random.nextInt(WIDTH - (radius*2 ) ) + radius);
        setX(random.nextInt(WIDTH - radius) + radius);
    }

    public void generateRandomXYAndRadius(){
        generateRandomRadius();
        generateRandomX();
        generateRandomY();
    }


}
