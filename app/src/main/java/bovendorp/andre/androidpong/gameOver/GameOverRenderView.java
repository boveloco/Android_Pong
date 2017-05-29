package bovendorp.andre.androidpong.gameOver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import bovendorp.andre.androidpong.Player;
import bovendorp.andre.androidpong.game.RenderView;
import bovendorp.andre.androidpong.menu.Menu;

public class GameOverRenderView extends View {
    Paint paint;
    Intent intent;
    boolean enter = false;

    public GameOverRenderView(Context context) {
        super(context);
        paint = new Paint();
        intent = new Intent(getContext(), Menu.class);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //RenderView.gameOver = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(0, 0, 0);
        paint.setTextSize(150);
        paint.setColor(Color.WHITE);
        canvas.drawText("GAME OVER!", (getWidth() / 6), getHeight() / 2, paint);
        paint.setTextSize(40);
        canvas.drawText("Points: " + Player.spoints, getWidth() / 2 - 120, getHeight() / 3 * 2, paint);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_CANCEL) {
            if (enter)
                return true;
            getContext().startActivity(intent);
            enter = true;
        }
        return true;
    }
}
