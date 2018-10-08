package com.example.amos_perets.toiletgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amos_perets.toiletgame.circle.CircleCustomView;
import com.example.amos_perets.toiletgame.circle.CircleData;
import com.example.amos_perets.toiletgame.color.ColorData;
import com.example.amos_perets.toiletgame.color.ColorList;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private CircleCustomView circleCustomView;
    private RelativeLayout llMain;

    private TextView tvCorrectCircle, tvTouchCorrectCircle;

    private CircleData currCircleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorList colorList = new ColorList();
        Toast.makeText(this, "SIZE: " + colorList.getColorList().length, Toast.LENGTH_SHORT ).show();
//        for(int i = 0; i < colorList.getColorList().length; i++){
//            Log.d("COLOR_LIST", "COLOR NAME: " + colorList.getColorList()[i].getName() + ", " + colorList.getColorList()[i].getNumOfColor());
//        }
//

        for(int i = 0; i < colorList.getColorList().length; i++){
            Log.d("COLOR_LIST", "COLOR NAME: " + colorList.getNextNameColorInList());
        }
    }

}
