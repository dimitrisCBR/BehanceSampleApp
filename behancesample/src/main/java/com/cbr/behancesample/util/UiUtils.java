package com.cbr.behancesample.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.ColorInt;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/** Created by Dimitrios on 1/31/2017. */
public class UiUtils {
    
    private static Integer sStatusBarHeight;
    
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }
    
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }
    
    public static Point measureSquareView(float ratio, int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        if (width > 0 || width < height) {
            height = (int) (width * ratio);
        } else {
            width = (int) (height / ratio);
        }
        
        int widthMeasureSpecs = makeMeasureSpec(width, EXACTLY);
        int heightMeasureSpecs = makeMeasureSpec(height, EXACTLY);
        return new Point(widthMeasureSpecs, heightMeasureSpecs);
    }
    
    public static void hideSoftKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    public static void focusViewAndShowKeyboard(View view, Context context) {
        view.requestFocusFromTouch();
        InputMethodManager inputMethodManager = (InputMethodManager)
            context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }
    
    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }
    
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }
    
    public static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight == null) {
            int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resId > 0) {
                sStatusBarHeight = context.getResources().getDimensionPixelSize(resId);
            }
        }
        return sStatusBarHeight;
    }
    
    public static int getToolbarHeight(Context context) {
        TypedValue tv = new TypedValue();
        int toolbarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        
        return toolbarHeight;
    }
    
    @ColorInt
    public static int getPaletteColor(Palette palette) {
        int defaultColor = 0x000000;
        int color = palette.getMutedColor(defaultColor);
        if (color == defaultColor) {
            color = palette.getLightMutedColor(defaultColor);
        }
        if (color == defaultColor) {
            color = palette.getDarkMutedColor(defaultColor);
        }
        if (color == defaultColor) {
            color = palette.getLightVibrantColor(defaultColor);
        }
        if (color == defaultColor) {
            color = palette.getDarkVibrantColor(defaultColor);
        }
        if (color == defaultColor) {
            color = palette.getVibrantColor(defaultColor);
        }
        
        return color;
    }
    
    public static Bitmap blurBitmap(Context context, Bitmap originalBitmap) {
        
        Bitmap blurredBitmap = originalBitmap.copy(originalBitmap.getConfig(), true);
        //User the support version if you want to support api<17
        final RenderScript rs = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(25f /* e.g. 3.f */);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(blurredBitmap);
        return blurredBitmap;
    }
}
