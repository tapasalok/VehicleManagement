package com.dpautomations.vehiclemanagement.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface newType;
    private int newSize;

    public CustomTypefaceSpan(String family, Typeface type) {
        super(family);
        newType = type;
    }

    public CustomTypefaceSpan(String family, int size, Typeface type) {
        super(family);
        newType = type;
        newSize = size;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newType, newSize);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType, newSize);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf, int newSize) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTextSize(newSize);
        paint.setTypeface(tf);
    }

}
