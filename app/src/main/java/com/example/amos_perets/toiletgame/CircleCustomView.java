package com.example.amos_perets.toiletgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleCustomView extends View {

    private final int RADIUS                = 200;
    private final int WIDTH                 = getResources().getDisplayMetrics().widthPixels;
    private final int HEIGHT                =  getResources().getDisplayMetrics().heightPixels;

    private Paint drawPaint;
    private Random random;
    private List<CircleData> circleDataList;
    private CircleData correctCircleData;

    public CircleCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        random = new Random();
        circleDataList = new ArrayList<>();
        circleDataList.add(new CircleData(Color.BLACK, true));
        circleDataList.add(new CircleData(Color.RED, false));
        circleDataList.add(new CircleData(Color.YELLOW, false));
  //      circleDataList.add(new CircleData(Color.GREEN, false));
//        circleDataList.add(new CircleData(Color.BLUE, false));
        correctCircleData = getCorrectCircleData();
    }

    private CircleData getCorrectCircleData(){
        for(CircleData circleData : circleDataList){
            if(circleData.isCorrectCircle()){
                return circleData;
            }
        }
        return new CircleData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clear(canvas);
        for(CircleData circleData : circleDataList){
            circle(canvas, circleData);
        }
    }

    private void circle(Canvas canvas, CircleData circleData){
        circleData.generateRandomXYAndRadius();
//        if(!circleData.isCorrectCircle()){
            for (int i = 0; i < circleDataList.size(); i++){
                CircleData prevCircleData = circleDataList.get(i);
                    if( (circleData.getColor() != prevCircleData.getColor()) && (intersectionsCircle(circleData, prevCircleData)) ){
                        circleData.generateRandomXYAndRadius();
                        i = 0;
                    }
            }
  //      }
        canvas.drawCircle(circleData.getX(), circleData.getY(), circleData.getRadius() , setupPaint(circleData.getColor()));
    }

    /**
     * Check overlap between two circles
     * @param circleData - the curr/new circleData
     * @param prevCircleData the prev circleData
     * @return
     */
    private boolean intersectionsCircle(CircleData circleData,  CircleData prevCircleData){

        return  (Math.hypot(circleData.getX()-prevCircleData.getX(), circleData.getY()-prevCircleData.getY()) <= (circleData.getRadius() + prevCircleData.getRadius()));


    }


    public void drawCircle(){
        invalidate();
    }

    private void clear(Canvas canvas) {
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), drawPaint);
    }

    private Paint setupPaint(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);
        paint.setColor(color);
        return paint;
    }

    private void initPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(Color.parseColor("#CD5C5C"));
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private class CircleData {

        private int x;
        private int y;
        private int radius;
        private int color;
        private boolean correctCircle;

        public CircleData(int color, boolean correctCircle) {
            this.color = color;
            this.correctCircle = correctCircle;
        }

        public CircleData() {

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
//            this.radius = random.nextInt(RADIUS) + 10;
            this.radius = RADIUS;
        }

        private void setRandomY() {
            this.y = random.nextInt(HEIGHT - (radius*2) ) + radius ;
//            this.y = (HEIGHT - (radius*2));

        }

        private void setRandomX() {
            this.x = random.nextInt(WIDTH - (radius*2) ) + radius;
//            this.x = (WIDTH - (radius*2));
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

}

