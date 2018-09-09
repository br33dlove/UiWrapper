package com.davidcryer.uiwrapperlibraryexample.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import java.util.Objects;

public class DrawableHelper {

    public static Drawable drawableForColor(final Context context, @DrawableRes final int drawableRes, @ColorRes final int colorRes) {
        final Drawable drawable = Objects.requireNonNull(ContextCompat.getDrawable(context, drawableRes));
        return drawableForColor(drawable, ContextCompat.getColor(context, colorRes));
    }

    private static Drawable drawableForColor(final Drawable drawable, @ColorInt final int color) {
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return drawable;
    }
}
