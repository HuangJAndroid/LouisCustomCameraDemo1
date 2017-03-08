package com.louisgeek.louiscustomcamerademo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/2/27.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder sfh;
    private Paint paint;
    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }



    private void init(Context context) {
        sfh=this.getHolder();
        sfh.addCallback(this);
        paint=new Paint();
        paint.setColor(Color.WHITE);

    }



    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        init(context);
    }



    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);

    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myDraw();
    }

    private void myDraw() {
        Canvas canvas=sfh.lockCanvas();
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), new Paint());
        Bitmap bmp=readBitmap(getResources(), R.drawable.ic_sticker_reversal_horizontal);
        Matrix matrix=new Matrix();

        matrix.postTranslate(0, 550);
        bmp=readBitmap(getResources(), R.drawable.ic_sticker_reversal_horizontal);
        canvas.drawBitmap(bmp, matrix, paint);

        sfh.unlockCanvasAndPost(canvas);
        if(bmp!=null)
            bmp.recycle();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    public static Bitmap readBitmap(Resources r, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = r.openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}
