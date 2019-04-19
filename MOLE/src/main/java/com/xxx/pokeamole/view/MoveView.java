package com.xxx.pokeamole.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xxx.pokeamole.R;

/**
 * 自定义View 锤子
 *
 * @author xXx
 * @date 2018/4/17.
 */
public class MoveView extends View {
    private Paint mBitPaint;
    private Bitmap mBitmap;

    /**
     * currentX 初始位置x
     * currentY 初始位置y
     */
    public float currentX = 200;
    public float currentY = 800;

    /**
     * mBitmapWidth 图像宽度
     * mBitmapHeight 图像高度
     */
    private float mBitmapWidth, mBitmapHeight;

    public MoveView(Context context) {
        super(context);
        initPaint();
        initBitmap();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //Paint.ANTI_ALIAS_FLAG 抗锯齿
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //加快显示速度
        mBitPaint.setFilterBitmap(true);
        //防抖动
        mBitPaint.setDither(true);
    }

    /**
     * 初始化Bitmap 锤子图像加载
     */
    private void initBitmap() {
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.hammer, null))
                .getBitmap();

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,
                currentX - mBitmapWidth * 3 / 4,
                currentY - mBitmapHeight * 3 / 4,
                mBitPaint);
    }
}
