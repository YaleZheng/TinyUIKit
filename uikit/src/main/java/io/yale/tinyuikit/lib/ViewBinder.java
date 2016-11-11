package io.yale.tinyuikit.lib;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import static io.yale.tinyuikit.lib.ViewExtension.v_findView;

/**
 * Created by yalez on 2016/11/8.
 */

public class ViewBinder {
    private SparseArray<View> bindViews = new SparseArray<>();
    private View attchedRoot;


    public ViewBinder() {
    }

    public ViewBinder attachRoot(View root) {
        this.attchedRoot = root;
        return this;
    }

    public ViewBinder detachRoot() {
        return attachRoot(null);
    }

    public ViewBinder bind(String tag, View view) {
        if (!TextUtils.isEmpty(tag)) {
            this.bindViews.append(tag.hashCode(), view);
        }
        return this;
    }

    public ViewBinder bind(String tag, int targetID) {
        View target = v_findView(attchedRoot, targetID);
        return bind(tag, target);
    }

    public <T extends View> T find(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            int key = tag.hashCode();
            if (bindViews.indexOfKey(key) >= 0) {
                return (T) bindViews.get(key);
            }
        }

        return null;
    }
}
