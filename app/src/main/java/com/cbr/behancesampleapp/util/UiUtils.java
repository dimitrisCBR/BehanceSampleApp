package com.stud.dle.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorRes;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.stud.dle.StuddleApplication;

import java.lang.ref.WeakReference;

import static android.support.v4.content.ContextCompat.getDrawable;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * Created by Dimitrios on 1/31/2017.
 */

public class UiUtils {

    private static Integer sStatusBarHeight;

    public static void resetTransitionName(View animatedView) {
        ViewCompat.setTransitionName(animatedView, null);
    }

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

    @Deprecated
    public static Integer getColor(@ColorRes int resId) {
        return StuddleApplication.app().getResources().getColor(resId);
    }


    /**
     * Defer the execution of the  runnable only when a view if inflated,attached AND really measured with non-empty measures.
     * If you don't care about the non-empty measures, you can simply use View.post()
     *
     * @param view     the view waiteing to be displayed
     * @param runnable
     */
    public static void runWhenVisible(View view, final Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException(String.valueOf("Provide a runnable if you want something to happen!"));
        }
        final WeakReference<View> target = new WeakReference<View>(view);
        final long firstRun = System.currentTimeMillis();
        Runnable imageViewRunnable = new Runnable() {
            @Override
            public void run() {
                View view;
                if ((view = target.get()) != null) {
                    if (view.getWidth() + view.getHeight() > 0) {
                        view.post(runnable);
                    } else if ((System.currentTimeMillis() - firstRun) < 1000) {
                        view.postDelayed(this, 10);
                    }
                }
            }
        };
        view.post(imageViewRunnable);
    }

    /**
     * Checks ifs a view is visible within another view.
     *
     * @param subject   The subject view of this test.
     * @param container The parent (container) of the subject view.
     * @return True is subject is visible within container.
     */
    public static boolean isVisibleInParent(View subject, View container) {
        Rect rect = new Rect();
        container.getHitRect(rect);
        return subject.getLocalVisibleRect(rect);
    }

    /**
     * Checks if the view has visibility set as {@link View#VISIBLE}.
     *
     * @return True if visibility is {@link View#VISIBLE}, false otherwise.
     */
    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public static void shakeView(View view) {
        if (view != null) {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 10f);
            translationY.setDuration(400);
            translationY.setInterpolator(new CycleInterpolator(3));
            translationY.start();
        }
    }

    public static <T extends View> T findById(View view, int id) {
        return (T) view.findViewById(id);
    }

    public static class CompoundUtils {

        public static Drawable getCompoundDrawable(TextView view, Position position) {
            return view.getCompoundDrawables()[position.ordinal()];
        }

        public enum Position {
            LEFT, TOP, RIGHT, BOTTOM
        }
    }
}
