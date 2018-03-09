package com.roommanagement.CustomeFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by zcodia on 5/18/2015.
 */
public class MyTextViewBold extends android.support.v7.widget.AppCompatTextView {

    public MyTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Bold.ttf");
            setTypeface(tf);
            //setBackgroundColor(getResources().getColor(android.R.color.transparent));
            //setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

}