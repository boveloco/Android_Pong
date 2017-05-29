package bovendorp.andre.androidpong.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

import bovendorp.andre.androidpong.Ball;
import bovendorp.andre.androidpong.Bot;
import bovendorp.andre.androidpong.GameResources;
import bovendorp.andre.androidpong.Life;
import bovendorp.andre.androidpong.Player;
import bovendorp.andre.androidpong.R;
import bovendorp.andre.androidpong.gameOver.GameOver;

public class RenderView extends View {
    Paint paint = new Paint();
    Player player1 = null;
    Bot player2 = null;
    Ball bolinha = null;
    Life l = null;
    Life l2 = null;
    Life l3 = null;
    public static MediaPlayer mp;
    private Intent intent;
    public static boolean gameOver = false;

    float startTime;

    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();
        intent = new Intent(getContext(), GameOver.class);
        mp = MediaPlayer.create(getContext(), R.raw.toc);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (player1 == null) {
            player1 = new Player(100, getHeight() / 2);
            GameResources.getInstance().addObject(player1);
        }
        if (bolinha == null) {
            bolinha = new Ball(getWidth() / 2, getHeight() / 2, getWidth(), getHeight());
            GameResources.getInstance().addObject(bolinha);
        }
        if (player2 == null) {
            player2 = new Bot(getWidth() - 100, getHeight() / 2, bolinha);
            GameResources.getInstance().addObject(player2);
        }
        if (l == null) {
            l = new Life(1, 50 + (40), 40);
            GameResources.getInstance().addObject(l);
        }
        if (l2 == null) {
            l2 = new Life(2, 50 + (40 * 2), 40);
            GameResources.getInstance().addObject(l2);
        }
        if (l3 == null) {
            l3 = new Life(3, 50 + (40 * 3), 40);
            GameResources.getInstance().addObject(l3);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gameOver) {
            mp.stop();
            getContext().startActivity(intent);
            return;
        }
        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();

        canvas.drawRGB(200, 200, 200);
        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        // score
        paint.setTextSize(30);
        canvas.drawText("Pontuação: " + player1.getPoints(), (getWidth() / 3) * 2, 60, paint);
        invalidate();
        GameResources.getInstance().swap();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            player1.setPosition((int) event.getY(), getHeight());
        }
        return true;
    }
}
