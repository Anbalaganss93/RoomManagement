package com.roommanagement.CustomeFonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.roommanagement.R;

/**
 * Created by anbu0 on 04/03/2018.
 */

public class BoldFonts extends AppCompatTextView {

    public BoldFonts(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public BoldFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public BoldFonts(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MediumFonts);

            String fontName = a.getString(R.styleable.MediumFonts_BoldFont);
            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Bold.ttf");
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}

