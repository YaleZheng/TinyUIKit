package io.yale.tinyuikit.lib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by yalez on 2016/11/22.
 */

public class ContextExtension {
    private static Resources sRes;
    private static DisplayMetrics sMetrics;

    static {
    }

    private ContextExtension() {
    }

    public static void init(Context context) {
        sRes = context.getResources();
        WindowManager wm = WindowManager.class.cast(context.getSystemService(Context.WINDOW_SERVICE));
        sMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(sMetrics);
    }

    public static void destroy() {
    }

    public static int getScreenWidth() {
        return sMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        return sMetrics.heightPixels;
    }

    public static Rect getScreenSize() {
        return new Rect(0, 0, sMetrics.widthPixels, sMetrics.heightPixels);
    }

    public static void getScreenSize(Rect outRect) {
        outRect.set(0, 0, sMetrics.widthPixels, sMetrics.heightPixels);
    }

    public static Drawable getDrawable(int drawableID) {
        return ResourcesCompat.getDrawable(sRes, drawableID, null);
    }

    public static Drawable getDrawable(int drawableID, Resources.Theme theme) {
        return ResourcesCompat.getDrawable(sRes, drawableID, theme);
    }

    public static int getDimensionPixelSize(int dimenID) {
        return sRes.getDimensionPixelSize(dimenID);
    }

    public static int getColor(int colorID) {
        return ResourcesCompat.getColor(sRes, colorID, null);
    }

    public static int getColor(int colorID, Resources.Theme theme) {
        return ResourcesCompat.getColor(sRes, colorID, theme);
    }

    public static int dp2px(int dp) {
        return (int) dp2pxFloat(dp);
    }

    public static int sp2px(int sp) {
        return (int) sp2pxFloat(sp);
    }

    public static float px2dp(int px) {
        return (float) px / sMetrics.density;
    }

    public static float px2sp(int sp) {
        return (float) sp / sMetrics.scaledDensity;
    }

    public static float dp2pxFloat(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sMetrics);
    }

    public static float sp2pxFloat(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, sMetrics);
    }
}
