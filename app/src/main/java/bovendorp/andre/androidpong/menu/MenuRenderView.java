package bovendorp.andre.androidpong.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import bovendorp.andre.androidpong.game.MainActivity;

public class MenuRenderView extends View {
    Paint paint;
    private Intent intent;
    private boolean enter = false;

    public MenuRenderView(Context context) {
        super(context);
        paint = new Paint();
        intent = new Intent(this.getContext(), MainActivity.class);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(0, 0, 0);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Menu", 100, 100, paint);
        paint.setTextSize(150);
        int height = getHeight() / 2;
        int width = getWidth() / 3;
        canvas.drawText("START", width, height, paint);
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
