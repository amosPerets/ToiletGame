package com.example.amos_perets.toiletgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random;
    private CircleCustomView circleCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        circleCustomView = (CircleCustomView)findViewById(R.id.circle);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                circleCustomView.drawCircle();

                new CountDownTimer(60000, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        circleCustomView.drawCircle();
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();

            }
        });
    }


}
