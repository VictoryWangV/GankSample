package com.goldencis.ganksample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Tom on 2018-1-22.
 */
public class ScaleImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "ScaleImageView";
    /**
     * 宽高比(宽/高)
     */
    private static final int SCALE = 2;

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            double scale = (double) intrinsicWidth / (double) intrinsicHeight;
            int w = getMeasuredWidth();
            int h = (int) ((double) w / scale);
            Log.d(TAG, "onMeasure: ====================================");
            Log.d(TAG, "onMeasure: intrinsicHeight-->" + intrinsicHeight);
            Log.d(TAG, "onMeasure: intrinsicWidth-->" + intrinsicWidth);
            Log.d(TAG, "onMeasure: scale-->" + scale);
            Log.d(TAG, "onMeasure: w-->" + w);
            Log.d(TAG, "onMeasure: h-->" + h);
            setMeasuredDimension(w + getPaddingLeft() + getPaddingRight(), h + getPaddingTop() + getPaddingBottom());
        }
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
    }
}
