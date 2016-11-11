package io.yale.tinyuikit.lib;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;

import io.yale.rxfunction.lib.func.SafeFunc0;

/**
 * Created by yalez on 2016/11/8.
 */

public class ViewFinders {

    private ViewFinders() {
    }

    public static <R extends View> SafeFunc0<R> newFinder(final Activity holder, final int targetID) {
        return new SafeFunc0<R>() {
            @Override
            public R call() {
                return findViewImpl(holder, targetID);
            }
        };
    }

    public static <R extends View> SafeFunc0<R> newFinder(final Fragment holder, final int targetID) {
        return new SafeFunc0<R>() {
            @Override
            public R call() {
                return findViewImpl(holder, targetID);
            }
        };
    }

    public static <R extends View> SafeFunc0<R> newFinder(final Dialog holder, final int targetID) {
        return new SafeFunc0<R>() {
            @Override
            public R call() {
                return findViewImpl(holder, targetID);
            }
        };
    }

    public static <R extends View> SafeFunc0<R> newFinder(final View holder, final int targetID) {
        return new SafeFunc0<R>() {
            @Override
            public R call() {
                return findViewImpl(holder, targetID);
            }
        };
    }

    public static <R extends View> SafeFunc0<R> newFinder(final R target) {
        return new SafeFunc0<R>() {
            @Override
            public R call() {
                return target;
            }
        };
    }

    private static <T extends View> T findViewImpl(Activity holder, int targetID) {
        if (holder != null && holder.getWindow() != null && holder.getWindow().getDecorView() != null) {
            return findViewImpl(holder.getWindow().getDecorView(), targetID);
        }

        return null;
    }

    private static <T extends View> T findViewImpl(Dialog holder, int targetID) {
        if (holder != null && holder.getWindow() != null && holder.getWindow().getDecorView() != null) {
            return findViewImpl(holder.getWindow().getDecorView(), targetID);
        }

        return null;
    }

    private static <T extends View> T findViewImpl(Fragment holder, int targetID) {
        if (holder != null) {
            return findViewImpl(holder.getView(), targetID);
        }

        return null;
    }

    private static <T extends View> T findViewImpl(View holder, int targetID) {
        if (holder != null) {
            View target = holder.findViewById(targetID);
            if (target != null) {
                return (T) target;
            }
        }
        return null;
    }
}
