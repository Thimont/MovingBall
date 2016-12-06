package com.example.flthiebl.movingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bouton = (Button) findViewById(R.id.button_play);
        bouton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AnimatedActivity.class);

        TextView frameRate = (TextView) findViewById(R.id.frame_rate);
        intent.putExtra("frame_rate", frameRate.getText().toString());

        TextView xSpeed = (TextView) findViewById(R.id.x_speed);
        intent.putExtra("x_speed", xSpeed.getText().toString());

        TextView ySpeed = (TextView) findViewById(R.id.y_speed);
        intent.putExtra("y_speed", ySpeed.getText().toString());

        TextView points= (TextView) findViewById(R.id.points);
        intent.putExtra("points", points.getText().toString());

        startActivity(intent);
    }
}
