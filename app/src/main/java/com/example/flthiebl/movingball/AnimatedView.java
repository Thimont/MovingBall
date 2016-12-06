package com.example.flthiebl.movingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by flthiebl on 04/11/2016.
 */

public class AnimatedView extends ImageView {
    private Context mContext;

    private int x = -1;
    private int y = -1;

    private int frameRate;
    private int points;
    private int computerPoints = 0;
    private int playerPoints = 0;


    private int xVelocity;
    private int yVelocity;
    private Handler h;

    int black_barY;
    int black_barX = 500;

    private Paint paint = null;

    private GestureDetector mgd;

    public AnimatedView(Context context, int frameRate, int xSpeed, int ySpeed, int points) {
        super(context);
        this.mContext = context;
        this.frameRate = frameRate;
        this.xVelocity = xSpeed;
        this.yVelocity = ySpeed;
        this.points = points;

        h = new Handler();
        mgd = new GestureDetector(mContext, new mTouch());
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mgd.onTouchEvent(event);
    }

    protected void onDraw(Canvas c){
        BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.ball, null);
        BitmapDrawable black_bar = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.black_bar, null);

        if(x < 0 && y < 0){
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
        }

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);

        black_barY = this.getHeight()/100*98;

        if((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)){
                xVelocity = xVelocity*-1;
            }

        if(y >= (black_barY - ball.getBitmap().getHeight()) && x > black_barX && x < (black_barX + black_bar.getBitmap().getWidth())){
            yVelocity = yVelocity*-1;
        }

        if(y < 0){
            yVelocity = yVelocity*-1;
            playerPoints ++;

        }

        if(y > (this.getHeight() - ball.getBitmap().getHeight())){
            yVelocity = yVelocity*-1;
            computerPoints ++;
        }

        if(black_barX <= 0)
            black_barX = 0;
        if(black_barX >= this.getWidth()- black_bar.getBitmap().getWidth())
            black_barX = this.getWidth() - black_bar.getBitmap().getWidth();

        c.drawBitmap(ball.getBitmap(), x, y, null);
        c.drawBitmap(black_bar.getBitmap(), black_barX, black_barY, null);
        c.drawText("Player : " + String.valueOf(playerPoints), this.getWidth()/10, this.getHeight()/10, paint);
        c.drawText("Computer : " + String.valueOf(computerPoints), this.getWidth()/10*7, this.getHeight()/10, paint);

        h.postDelayed(r, frameRate);
    }

    private class mTouch implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            black_barX += (motionEvent1.getX() - motionEvent.getX())/15;

            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            /*int tangente = (int) Math.abs((e2.getY() - e1.getY()) / (e2.getX() - e1.getX()));

            if(velocityX < 0){
                xVelocity = 10;
            } else {
                xVelocity = 10;
            }

            if(velocityY < 0){
                yVelocity = -tangente * 10;
            } else {
                yVelocity = tangente * 10;
            }*/

            return true;
        }
    }
}