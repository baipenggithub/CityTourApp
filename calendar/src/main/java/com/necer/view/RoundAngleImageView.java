package com.necer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.necer.calendar.R;

import androidx.appcompat.widget.AppCompatImageView;


public class RoundAngleImageView extends AppCompatImageView
{
    private Paint paint;
    private Paint paint2;
    /**
     * 这四个是四个角画圆的半径
     */
    private int   leftTopRadius     = 20;
    private int   rightTopRadius    = 20;
    private int   leftBottomRadius  = 20;
    private int   rightBottomRadius = 20;

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context)
    {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs)
    {

        if (attrs != null)
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundAngleImageView);
            leftTopRadius = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_leftTopRadius, 0);
            rightTopRadius = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_rightTopRadius, 0);
            leftBottomRadius = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_leftBottomRadius, 0);
            rightBottomRadius = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_rightBottomRadius, 0);
        }
        else
        {
            float density = context.getResources().getDisplayMetrics().density;
            leftTopRadius = (int) (leftTopRadius * density);
            rightTopRadius = (int) (rightTopRadius * density);
            leftBottomRadius = (int) (leftBottomRadius * density);
            rightBottomRadius = (int) (rightBottomRadius * density);
        }

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
    }

    @Override
    public void draw(Canvas canvas)
    {
        Bitmap bitmap  = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        super.draw(canvas2);
        if (leftTopRadius > 0)
        {
            drawLiftUp(canvas2);
        }
        if (leftBottomRadius > 0)
        {
            drawLiftDown(canvas2);
        }
        if (rightTopRadius > 0)
        {
            drawRightUp(canvas2);
        }
        if (rightBottomRadius > 0)
        {
            drawRightDown(canvas2);
        }
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
    }

    private void drawLiftUp(Canvas canvas)
    {
        Path path = new Path();
        path.moveTo(0, rightTopRadius);
        path.lineTo(0, 0);
        path.lineTo(leftTopRadius, 0);
        path.arcTo(new RectF(0, 0, leftTopRadius * 2, leftTopRadius * 2), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas)
    {
        Path path = new Path();
        path.moveTo(0, getHeight() - rightTopRadius);
        path.lineTo(0, getHeight());
        path.lineTo(leftTopRadius, getHeight());
        path.arcTo(new RectF(0, getHeight() - leftBottomRadius * 2, leftBottomRadius * 2, getHeight()), 90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas)
    {
        Path path = new Path();
        path.moveTo(getWidth() - leftTopRadius, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - rightTopRadius);
        path.arcTo(new RectF(getWidth() - rightBottomRadius * 2, getHeight() - rightBottomRadius * 2, getWidth(), getHeight()), -0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas)
    {
        Path path = new Path();
        path.moveTo(getWidth(), rightTopRadius);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - leftTopRadius, 0);
        path.arcTo(new RectF(getWidth() - rightTopRadius * 2, 0, getWidth(), 0 + rightTopRadius * 2), -90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }
}
