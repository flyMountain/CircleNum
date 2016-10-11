package com.yuanfei.circlrnum.circletextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * Created by admin on 2016/10/8.
 */

public class CustomCircleTextView extends View {
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;
    /**
     * 上下左右间隙
     */
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    /**
     *
     */
    private int mPadding;
    /**
     * 圆圈的颜色
     */
    private int mBackgroundColor;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomCircleTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomCircleTextView(Context context)
    {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomCircleTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomTitleView_titleText) {
                mTitleText = a.getString(attr);

            } else if (attr == R.styleable.CustomTitleView_titleTextColor) {// 默认颜色设置为黑色
                mTitleTextColor = a.getColor(attr, Color.BLACK);

            } else if (attr == R.styleable.CustomTitleView_titleTextSize) {// 默认设置为16sp，TypeValue也可以把sp转化为px
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomTitleView_paddingLeft) {
                mPaddingLeft = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomTitleView_paddingRight) {
                mPaddingRight = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomTitleView_paddingTop) {
                mPaddingTop = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomTitleView_paddingBottom) {
                mPaddingBottom = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomTitleView_backgroundColor) {
                mBackgroundColor = a.getColor(attr, Color.RED);

            } else if (attr == R.styleable.CustomTitleView_padding) {
                mPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));

            }

        }
        a.recycle();


        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
//
        int mWidth = 0;
        int mHeight = 0;

        int cWidth = 0;
        int cHeight = 0;

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseX = 0;

        float baseY = mBound.height();

        float topY = baseY + fontMetrics.top;

        float bottomY = baseY + fontMetrics.bottom;



        float textWidth = mPaint.measureText(mTitleText);

        cWidth = (int)textWidth ;
        cHeight = (int)(bottomY - topY);

        mHeight = Math.max(cHeight,cWidth) + mPaddingBottom + mPaddingTop + mPadding * 2;
        mWidth = Math.max(cHeight,cWidth) + mPaddingLeft + mPaddingRight + mPadding * 2;

//
        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = sizeWidth;
        }
        if (heigthMode == MeasureSpec.EXACTLY){
            mHeight = sizeHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int radius = getWidth()/2;
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2,getWidth()/2,radius, mPaint);

        mPaint.setColor(mTitleTextColor);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText( mTitleText, getWidth()/2, (getWidth()+mBound.height())/2, mPaint);

    }
}
