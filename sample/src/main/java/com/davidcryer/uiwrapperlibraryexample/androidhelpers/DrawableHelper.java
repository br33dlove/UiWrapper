package com.davidcryer.uiwrapperlibraryexample.androidhelpers;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

public class DrawableHelper {

    public static Drawable drawableForColor(final Context context, @DrawableRes final int drawableRes, @ColorRes final int colorRes) {
        return drawableForColor(ContextCompat.getDrawable(context, drawableRes), ContextCompat.getColor(context, colorRes));
    }

    private static Drawable drawableForColor(final Drawable drawable, @ColorInt final int color) {
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return drawable;
    }
}
