package com.example.amos_perets.toiletgame.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.amos_perets.toiletgame.circle.CircleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleCustomView extends View {

    private final int SIZE_CIRCLES        = 9;
    private static int INDEX_ARRAY_COLOR  = 0;
    public static final int DELTA_BORDER      = 10;

    private Paint drawPaint;
    private Random random;
    private List<CircleData> circleDataList;
    private int[] colorArray = new int[SIZE_CIRCLES];

    private CircleCustomListener circleCustomListenerCallBack;

    public CircleCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        random = new Random();
        circleDataList = new ArrayList<>();
        colorArray[0] = Color.BLACK;
        colorArray[1] = Color.BLUE;
        colorArray[2] = Color.YELLOW;
        colorArray[3] = Color.GRAY;
        colorArray[4] = Color.GREEN;
        colorArray[5] = Color.argb(255, 255, 123, 0);
        colorArray[6] = Color.RED;
        colorArray[7] = Color.CYAN;
        colorArray[8] = Color.WHITE;
    }

    private void generateCorrectCircleData(){
        for(CircleData circleData : circleDataList) {
            circleData.setCorrectCircle(false);
        }
        if(circleDataList.size() > 1){
            int indexCorrectCircle = random.nextInt( circleDataList.size()-1 );
            circleDataList.get(indexCorrectCircle).setCorrectCircle(true);
            String strCorrectColorCircle = getCorrectCircle(circleDataList.get(indexCorrectCircle).getColor());
            if(circleCustomListenerCallBack != null){
                circleCustomListenerCallBack.onChangecorrectCircle(strCorrectColorCircle);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clear(canvas);
        checkIntersectionsCircles();
        drawCircles(canvas);
    }

    public void addOrChangeCircle(boolean isFullyList){
        CircleData circleData = generateCircle();

        if(isFullyList){
            changeCircleInList(circleData);
        } else {
            addCircle(circleData);
        }

        generateCorrectCircleData();
    }

    private void changeCircleInList(CircleData circleData){
        for (CircleData currCircleData : circleDataList){
            if(circleData.getColor() == currCircleData.getColor()){
                circleDataList.set(circleDataList.indexOf(currCircleData), circleData);
                break;
            }
        }
    }

    private void addCircle(CircleData circleData){
        circleDataList.add(circleData);
    }

    public boolean isFullyList(){
         return circleDataList.size() == SIZE_CIRCLES;
    }


    public void invalidate(){
        super.invalidate();
    }

    public void drawCircles(Canvas canvas){
        for(CircleData circleData : circleDataList){
            canvas.drawCircle(circleData.getX(), circleData.getY(), circleData.getRadius() , setupPaint(circleData.getColor()));
        }
    }

    private void clear(Canvas canvas) {
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), drawPaint);
    }

    private Paint setupPaint(int color) {
        drawPaint.setColor(color);
        return drawPaint;
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

    public CircleData generateCircle(){
        CircleData circleData = new CircleData(getMeasuredWidth() - DELTA_BORDER , getMeasuredHeight() - DELTA_BORDER);
        if(INDEX_ARRAY_COLOR  == circleDataList.size() ){
            initIndexArrayColor();
        }
        circleData.setColor(colorArray[INDEX_ARRAY_COLOR++]);

        return circleData;
    }

    private void initIndexArrayColor(){
        INDEX_ARRAY_COLOR = 0;
    }

    public void initAll(){
        circleDataList.removeAll(circleDataList);
        if(circleCustomListenerCallBack != null){
            circleCustomListenerCallBack.onChangecorrectCircle("");
        }
    }

    public void clearAll(){
        initAll();
        invalidate();
    }

    public CircleData isTapCircle(int xTouch, int yTouch){
        int radius, centerX, centerY, distanceX, distanceY;
        CircleData currCircleData = null;

        for(CircleData circleData : circleDataList){
                 radius = circleData.getRadius();
                 centerX = circleData.getX() ;
                 centerY = circleData.getY() ;
                 distanceX = (xTouch - centerX);
                 distanceY = (yTouch - centerY);
                if(isInsideCircle(distanceX, distanceY, radius)){
                    Log.d("DRAG", "CIRCLE");
                    currCircleData = circleData;
                    break;
                } else {
                    Log.d("DRAG", "NOT CIRCLE");
                }

        }

        return currCircleData;
    }



    public boolean isCircleTouch(int xTouch, int yTouch){
        int radius, centerX, centerY, distanceX, distanceY;

        for(CircleData circleData : circleDataList){
            if(circleData.isCorrectCircle()){
                 radius = circleData.getRadius();
                 centerX = circleData.getX() ;
                 centerY = circleData.getY() ;
                 distanceX = (xTouch - centerX);
                 distanceY = (yTouch - centerY);
                return isInsideCircle(distanceX, distanceY, radius);
            }
        }

        return false;
    }

    private boolean isInsideCircle(int distanceX, int distanceY, int radius){
        return (distanceX * distanceX) + (distanceY * distanceY) <= radius * radius;
    }


    public String getCorrectCircle(int colorOfCorrectCircle){

        String strColor= "";

        switch (colorOfCorrectCircle){
            case Color.BLACK:
                strColor = "BLACK";
                break;

            case Color.CYAN:
                strColor = "CYAN";
                break;

            case Color.RED:
                strColor = "RED";
                break;

            case Color.DKGRAY:
                strColor = "ORANGE";
                break;

            case Color.GREEN:
                strColor = "GREEN";
                break;

            case Color.GRAY:
                strColor = "GRAY";
                break;

            case Color.YELLOW:
                strColor = "YELLOW";
                break;

            case Color.BLUE:
                strColor = "BLUE";
                break;

            case Color.WHITE:
                strColor = "WHITE";
                break;

        }
        return strColor;
    }

    private void checkIntersectionsCircles(){
        for(CircleData circleData : circleDataList){
            for (int i = 0; i < circleDataList.size(); i++){
                CircleData currCircleData = circleDataList.get(i);
                if( circleData.getColor() != currCircleData.getColor()){
                    if( isIntersectionsCircle(circleData, currCircleData)){
                        circleData.generateRandomXYAndRadius();
                        i = 0;
                    }
                }
            }
        }
    }

    /**
     * Check overlap between two circles
     * @param circleData - the curr/new circleData
     * @param prevCircleData the prev circleData
     * @return
     */
    private boolean isIntersectionsCircle(CircleData circleData,  CircleData prevCircleData){

        return  (Math.hypot(circleData.getX()-prevCircleData.getX(), circleData.getY()-prevCircleData.getY()) <= (circleData.getRadius() + prevCircleData.getRadius()));

    }


    public void setCircleCustomListener(CircleCustomListener circleCustomListenerCallBack){
        this.circleCustomListenerCallBack = circleCustomListenerCallBack;
    }

    public interface CircleCustomListener{
        public void onChangecorrectCircle(String strCorrectCircle);
    }
}

