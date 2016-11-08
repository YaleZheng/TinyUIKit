package io.yale.tinyuikit.lib;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.functions.Func0;

import static io.yale.tinyuikit.lib.ViewExtension.v_findView;

/**
 * Created by yalez on 2016/11/8.
 */

public class ViewFinders {

    private ViewFinders() {
    }

    public static <R extends View> Func0<R> newFinder(final Activity holder, final int targetID) {
        return new Func0<R>() {
            @Override
            public R call() {
                return v_findView(holder, targetID);
            }
        };
    }

    public static <R extends View> Func0<R> newFinder(final Fragment holder, final int targetID) {
        return new Func0<R>() {
            @Override
            public R call() {
                return v_findView(holder, targetID);
            }
        };
    }

    public static <R extends View> Func0<R> newFinder(final Dialog holder, final int targetID) {
        return new Func0<R>() {
            @Override
            public R call() {
                return v_findView(holder, targetID);
            }
        };
    }

    public static <R extends View> Func0<R> newFinder(final View holder, final int targetID) {
        return new Func0<R>() {
            @Override
            public R call() {
                return v_findView(holder, targetID);
            }
        };
    }

    public static <R extends View> Func0<R> newFinder(final R target) {
        return new Func0<R>() {
            @Override
            public R call() {
                return target;
            }
        };
    }
}
