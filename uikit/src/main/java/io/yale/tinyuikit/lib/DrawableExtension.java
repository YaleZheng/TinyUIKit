package io.yale.tinyuikit.lib;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.print.PrintAttributes;

import java.util.List;

/**
 * Created by yalez on 2016/11/8.
 */

public class DrawableExtension {
    private static Application ctx;

    private DrawableExtension() {
    }

    public static void init(Application application) {
        ctx = application;
    }

    public static void destroy() {
    }

    public static RoundCornerDrawableBuilder newRoundCornerDrawable(int fillColor, int radiusInPx) {
        return new RoundCornerDrawableBuilder(fillColor, radiusInPx);
    }

    public static StateListDrawableBuilder newStateListDrawable() {
        return new StateListDrawableBuilder();
    }

    public static class StateListDrawableBuilder {
        private StateListDrawable listDrawable = new StateListDrawable();

        public StateListDrawableBuilder addStateDefault(Drawable drawable) {
            return addState(new int[0], drawable);
        }

        public StateListDrawableBuilder addStatePressed(Drawable drawable) {
            return addState(new int[]{android.R.attr.state_pressed}, drawable);
        }

        public StateListDrawableBuilder addStateSelected(Drawable drawable) {
            return addState(new int[]{android.R.attr.state_selected}, drawable);
        }

        public StateListDrawableBuilder addStateChecked(Drawable drawable) {
            return addState(new int[]{android.R.attr.state_checked}, drawable);
        }

        public StateListDrawableBuilder addState(int[] stateSet, Drawable drawable) {
            this.listDrawable.addState(stateSet, drawable);
            return this;
        }

        public StateListDrawable build() {
            return this.listDrawable;
        }
    }

    public static class RoundCornerDrawableBuilder {
        private int fillColor = 0;
        private int borderColor = 0;
        private int borderWidthInPx = 0;
        private int radiusInPx;
        private Rect bounds = new Rect();
        private RectF boundsF = new RectF();

        public RoundCornerDrawableBuilder(int fillColor, int radiusInPx) {
            this.fillColor = fillColor;
            this.radiusInPx = radiusInPx;
        }

        public RoundCornerDrawableBuilder setBorder(int borderColor, int borderWidthInPx) {
            this.borderColor = borderColor;
            this.borderWidthInPx = borderWidthInPx;
            return this;
        }

        public Drawable build() {
            return new ShapeDrawable(new Shape() {
                @Override
                public void draw(Canvas canvas, Paint paint) {
                    if (radiusInPx == 0) {
                        canvas.drawColor(fillColor);
                    } else {
                        canvas.getClipBounds(bounds);
                        boundsF.set(bounds);

                        paint.setStyle(Paint.Style.FILL);
                        if (fillColor != 0) {
                            paint.setColor(fillColor);
                            canvas.drawRoundRect(boundsF, radiusInPx, radiusInPx, paint);
                        }

                        if (borderWidthInPx > 0 && borderColor != 0) {
                            boundsF.inset(borderWidthInPx, borderColor);
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setColor(borderColor);
                            canvas.drawRoundRect(boundsF, radiusInPx, radiusInPx, paint);
                        }
                    }
                }
            });
        }
    }
}
