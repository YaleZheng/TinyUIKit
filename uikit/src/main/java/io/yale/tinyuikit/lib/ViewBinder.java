package io.yale.tinyuikit.lib;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by yalez on 2016/11/8.
 */

public class ViewBinder {
    SparseArray<View> bindViews = new SparseArray<>();

    public ViewBinder() {
    }

    public ViewBinder bind(String tag, View view) {
        if (!TextUtils.isEmpty(tag)) {
            this.bindViews.append(tag.hashCode(), view);
        }
        return this;
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
