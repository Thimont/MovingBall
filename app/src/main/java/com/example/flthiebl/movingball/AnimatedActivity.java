package com.example.flthiebl.movingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnimatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int frameRate = Integer.parseInt(getIntent().getStringExtra("frame_rate"));
        int xSpeed = Integer.parseInt(getIntent().getStringExtra("x_speed"));
        int ySpeed = Integer.parseInt(getIntent().getStringExtra("y_speed"));
        int points = Integer.parseInt(getIntent().getStringExtra("points"));

        setContentView(new AnimatedView(this, frameRate, xSpeed, ySpeed, points));

    }
}
