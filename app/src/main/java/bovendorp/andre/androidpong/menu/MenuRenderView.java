package bovendorp.andre.androidpong.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import bovendorp.andre.androidpong.game.MainActivity;

public class MenuRenderView extends View {
    Paint paint;
    private Intent intent;
    GestureDetector gestureDetector;

    public MenuRenderView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new MenuRenderView.GestureListener());
        paint = new Paint();
        intent = new Intent(context, MainActivity.class);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(0,0,0);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Menu", 100,100, paint);
        paint.setTextSize(150);
        int height = getHeight()/2;
        int width = getWidth() /3;
        canvas.drawText("START", width, height, paint);
        invalidate();
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            getContext().startActivity(intent);
            return true;
        }
    }
}