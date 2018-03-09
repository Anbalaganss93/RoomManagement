package com.roommanagement.CustomeFonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Button;

import com.roommanagement.R;

/**
 * Created by anbu0 on 04/03/2018.
 */

public class MediumButtonFonts extends AppCompatButton {

    public MediumButtonFonts(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public MediumButtonFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public MediumButtonFonts(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MediumFonts);

            String fontName = a.getString(R.styleable.MediumFonts_MediumFont);
            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Regular.ttf");
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}

