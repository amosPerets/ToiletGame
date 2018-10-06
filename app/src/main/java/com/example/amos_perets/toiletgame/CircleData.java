package com.example.amos_perets.toiletgame;

import android.support.v7.app.AppCompatActivity;

import java.util.Random;

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
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    private void setRandomRadius() {
            this.radius = random.nextInt(RADIUS) + 30;
    }

    private void setRandomY() {
        this.y = random.nextInt(HEIGHT - (radius*2) ) + radius ;

    }

    private void setRandomX() {
        this.x = random.nextInt(WIDTH - (radius*2 ) ) + radius;
    }

    public void generateRandomXYAndRadius(){
        setRandomRadius();
        setRandomX();
        setRandomY();
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
