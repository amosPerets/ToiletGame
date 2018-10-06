package com.example.amos_perets.toiletgame;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements CircleCustomView.CircleCustomListener{

    private Random random;
    private CircleCustomView circleCustomView;
    private RelativeLayout llMain;

    private TextView tvCorrectCircle, tvTouchCorrectCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCorrectCircle = (TextView)findViewById(R.id.TextCorrectCircle);
        tvTouchCorrectCircle = (TextView)findViewById(R.id.TextTouchCorrectCircle);

        llMain = (RelativeLayout)findViewById(R.id.MainActivity);

        random = new Random();
        circleCustomView = (CircleCustomView)findViewById(R.id.circle);
        circleCustomView.setCircleCustomListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleCustomView.addOrChangeCircle(circleCustomView.isFullyList());
                circleCustomView.invalidate();
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                circleCustomView.clearAll();
                return true;
            }
        });



        circleCustomView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    if(!circleCustomView.isCircleTouch((int)event.getX(), (int)event.getY())){
                        tvTouchCorrectCircle.setText("NOT GOODDDDDD");
                    }else {
                        tvTouchCorrectCircle.setText("GOODDDDDD");
                    }

                }

                if(event.getAction() == MotionEvent.ACTION_UP){
                    tvTouchCorrectCircle.setText("");
                }

                return true;
            }
        });

    }


    @Override
    public void onChangecorrectCircle(String strCorrectCircle) {
        tvCorrectCircle.setText(strCorrectCircle);
    }
}
