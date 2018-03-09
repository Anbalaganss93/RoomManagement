package com.roommanagement.CustomeFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zcodia on 5/18/2015.
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Regular.ttf");
            setTypeface(tf);
            //setBackgroundColor(getResources().getColor(android.R.color.transparent));
            //setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

}