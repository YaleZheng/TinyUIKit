package io.yale.tinyuikit.lib;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import rx.functions.Func0;

/**
 * Created by yalez on 2016/11/8.
 */

public class ViewExtension {
    private ViewExtension() {
    }

    public static <T extends View> T v_findView(Activity holder, int targetID) {
        if (holder != null && holder.getWindow() != null && holder.getWindow().getDecorView() != null) {
            return v_findView(holder.getWindow().getDecorView(), targetID);
        }

        return null;
    }

    public static <T extends View> T v_findView(Dialog holder, int targetID) {
        if (holder != null && holder.getWindow() != null && holder.getWindow().getDecorView() != null) {
            return v_findView(holder.getWindow().getDecorView(), targetID);
        }

        return null;
    }

    public static <T extends View> T v_findView(Fragment holder, int targetID) {
        if (holder != null) {
            return v_findView(holder.getView(), targetID);
        }

        return null;
    }

    public static <T extends View> T v_findView(View holder, int targetID) {
        if (holder != null) {
            View target = holder.findViewById(targetID);
            if (target != null) {
                return (T) target;
            }
        }
        return null;
    }

    public static void v_setVisibility(Activity holder, int targetID, int visibility) {
        v_setVisibility(ViewFinders.newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(Fragment holder, int targetID, int visibility) {
        v_setVisibility(ViewFinders.newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(Dialog holder, int targetID, int visibility) {
        v_setVisibility(ViewFinders.newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(View holder, int targetID, int visibility) {
        v_setVisibility(ViewFinders.newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(Func0<View> finder, int visibility) {
        if (finder != null) {
            try {
                View target = finder.call();
                if (target != null) {
                    target.setVisibility(visibility);
                }
            } catch (Exception e) {
            }
        }
    }


    public static void v_setText(Activity holder, int targetID, CharSequence text) {
        Func0<TextView> finder = ViewFinders.newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(Fragment holder, int targetID, CharSequence text) {
        Func0<TextView> finder = ViewFinders.newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(Dialog holder, int targetID, CharSequence text) {
        Func0<TextView> finder = ViewFinders.newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(View holder, int targetID, CharSequence text) {
        Func0<TextView> finder = ViewFinders.newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(Func0<TextView> finder, CharSequence text) {
        if (finder != null) {
            try {
                TextView target = finder.call();
                if (target != null) {
                    target.setText(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
