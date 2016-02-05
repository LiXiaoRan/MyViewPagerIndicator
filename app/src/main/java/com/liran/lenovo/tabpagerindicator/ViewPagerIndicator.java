package com.liran.lenovo.tabpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class ViewPagerIndicator extends View {

    /**
     * 绘制三角形的画笔
     */
    private Paint mPaint;

    /**
     * 构成三角形的路径
     */
    private Path mPath;

    /**
     * 三角形宽度
     */
    private int mTriangleWidth;
    /**
     * 三角形高度
     */
    private int mTriangleHeight;
    /**
     * 三角形的宽度为单个Tab的1/6
     */
    private static final float RADIO_TRIANGEL = 1.0f / 6;
    /**
     * 三角形的最大宽度
     */
//    private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 3 * RADIO_TRIANGEL);

    /**
     * 初始时三角形指示器的偏移量
     */
    private int mInitTranslationX;

    /**
     * 手指滑动的偏移量
     */
    private int mTranslationX;

    /**
     * 默认的tab数量
     */
    private static final int COUNT_DEFAULT_TAB = 4;

    /**
     * tab数量
     */
    private int mTabVisibleCount = COUNT_DEFAULT_TAB;

    /**
     * tab上的内容
     */
    private List<String> mTabTitles;
    /**
     * 与之绑定的ViewPager
     */
    public ViewPager mViewPager;

    /**
     * 标题正常时的颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    /**
     * 标题选中时的颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;



    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public ViewPagerIndicator(Context context) {
        super(context);
        init(null, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ViewPagerIndicator, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.ViewPagerIndicator_exampleString);
        mExampleColor = a.getColor(
                R.styleable.ViewPagerIndicator_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.ViewPagerIndicator_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.ViewPagerIndicator_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.ViewPagerIndicator_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
    }


}
