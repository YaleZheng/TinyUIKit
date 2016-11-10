package io.yale.tinyuikit.lib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.yale.tinyuikit.lib.func.SafeAction1;
import io.yale.tinyuikit.lib.func.SafeFunc0;
import rx.functions.Action1;
import rx.functions.Action4;

import static io.yale.tinyuikit.lib.ViewFinders.newFinder;

/**
 * Created by yalez on 2016/11/8.
 */

@SuppressWarnings("deprecation")
public class ViewExtension {
    private ViewExtension() {
    }

    private static <V extends View> void safeOperateView(SafeFunc0<V> finder, SafeAction1<V> handler) {
        if (finder != null && handler != null) {
            try {
                V target = finder.call();
                handler.call(target);
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    protected static void handleException(Exception e) {
        e.printStackTrace();
    }

    public static <T extends View> T v_findView(Activity holder, int targetID) {
        try {
            return ViewFinders.<T>newFinder(holder, targetID).call();
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    public static <T extends View> T v_findView(Dialog holder, int targetID) {
        try {
            return ViewFinders.<T>newFinder(holder, targetID).call();
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    public static <T extends View> T v_findView(Fragment holder, int targetID) {
        try {
            return ViewFinders.<T>newFinder(holder, targetID).call();
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    public static <T extends View> T v_findView(View holder, int targetID) {
        try {
            return ViewFinders.<T>newFinder(holder, targetID).call();
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    public static void v_setVisibility(Activity holder, int targetID, int visibility) {
        v_setVisibility(newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(Fragment holder, int targetID, int visibility) {
        v_setVisibility(newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(Dialog holder, int targetID, int visibility) {
        v_setVisibility(newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(View holder, int targetID, int visibility) {
        v_setVisibility(newFinder(holder, targetID), visibility);
    }

    public static void v_setVisibility(View target, int visibility) {
        v_setVisibility(newFinder(target), visibility);
    }

    private static void v_setVisibility(SafeFunc0<View> finder, final int visibility) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setVisibility(visibility);
            }
        });
    }

    public static void v_setVisible(Activity holder, int targetID) {
        v_setVisible(newFinder(holder, targetID));
    }

    public static void v_setVisible(Fragment holder, int targetID) {
        v_setVisible(newFinder(holder, targetID));
    }

    public static void v_setVisible(Dialog holder, int targetID) {
        v_setVisible(newFinder(holder, targetID));
    }

    public static void v_setVisible(View holder, int targetID) {
        v_setVisible(newFinder(holder, targetID));
    }

    public static void v_setVisible(View target) {
        v_setVisible(newFinder(target));
    }

    private static void v_setVisible(SafeFunc0<View> finder) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public static void v_setInvisible(Activity holder, int targetID) {
        v_setInvisible(newFinder(holder, targetID));
    }

    public static void v_setInvisible(Fragment holder, int targetID) {
        v_setInvisible(newFinder(holder, targetID));
    }

    public static void v_setInvisible(Dialog holder, int targetID) {
        v_setInvisible(newFinder(holder, targetID));
    }

    public static void v_setInvisible(View holder, int targetID) {
        v_setInvisible(newFinder(holder, targetID));
    }

    public static void v_setInvisible(View target) {
        v_setInvisible(newFinder(target));
    }

    private static void v_setInvisible(SafeFunc0<View> finder) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static void v_setGone(Activity holder, int targetID) {
        v_setGone(newFinder(holder, targetID));
    }

    public static void v_setGone(Fragment holder, int targetID) {
        v_setGone(newFinder(holder, targetID));
    }

    public static void v_setGone(Dialog holder, int targetID) {
        v_setGone(newFinder(holder, targetID));
    }

    public static void v_setGone(View holder, int targetID) {
        v_setGone(newFinder(holder, targetID));
    }

    public static void v_setGone(View target) {
        v_setGone(newFinder(target));
    }

    private static void v_setGone(SafeFunc0<View> finder) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setVisibility(View.GONE);
            }
        });
    }

    public static void v_setBackground(Activity holder, int targetID, final Drawable drawable) {
        v_setBackground(newFinder(holder, targetID), drawable);
    }

    public static void v_setBackground(Fragment holder, int targetID, final Drawable drawable) {
        v_setBackground(newFinder(holder, targetID), drawable);
    }

    public static void v_setBackground(Dialog holder, int targetID, final Drawable drawable) {
        v_setBackground(newFinder(holder, targetID), drawable);
    }

    public static void v_setBackground(View holder, int targetID, final Drawable drawable) {
        v_setBackground(newFinder(holder, targetID), drawable);
    }

    public static void v_setBackground(View target, final Drawable drawable) {
        v_setBackground(newFinder(target), drawable);
    }

    private static void v_setBackground(SafeFunc0<View> finder, final Drawable drawable) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setBackgroundDrawable(drawable);
            }
        });
    }

    public static void v_setClickListener(Activity holder, int targetID, View.OnClickListener listener) {
        v_setClickListener(newFinder(holder, targetID), listener);
    }

    public static void v_setClickListener(Fragment holder, int targetID, View.OnClickListener listener) {
        v_setClickListener(newFinder(holder, targetID), listener);
    }

    public static void v_setClickListener(Dialog holder, int targetID, View.OnClickListener listener) {
        v_setClickListener(newFinder(holder, targetID), listener);
    }

    public static void v_setClickListener(View holder, int targetID, View.OnClickListener listener) {
        v_setClickListener(newFinder(holder, targetID), listener);
    }

    public static void v_setClickListener(View target, View.OnClickListener listener) {
        v_setClickListener(newFinder(target), listener);
    }

    private static void v_setClickListener(SafeFunc0<View> finder, final View.OnClickListener listener) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setOnClickListener(listener);
            }
        });
    }

    public static void v_setFocusChangeListener(Activity holder, int targetID, View.OnFocusChangeListener listener) {
        v_setFocusChangeListener(newFinder(holder, targetID), listener);
    }

    public static void v_setFocusChangeListener(Fragment holder, int targetID, View.OnFocusChangeListener listener) {
        v_setFocusChangeListener(newFinder(holder, targetID), listener);
    }

    public static void v_setFocusChangeListener(Dialog holder, int targetID, View.OnFocusChangeListener listener) {
        v_setFocusChangeListener(newFinder(holder, targetID), listener);
    }

    public static void v_setFocusChangeListener(View holder, int targetID, View.OnFocusChangeListener listener) {
        v_setFocusChangeListener(newFinder(holder, targetID), listener);
    }

    public static void v_setFocusChangeListener(View target, View.OnFocusChangeListener listener) {
        v_setFocusChangeListener(newFinder(target), listener);
    }

    private static void v_setFocusChangeListener(SafeFunc0<View> finder, final View.OnFocusChangeListener listener) {
        safeOperateView(finder, new SafeAction1<View>() {
            @Override
            public void call(View view) throws Exception {
                view.setOnFocusChangeListener(listener);
            }
        });
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Activity holder, int targetID, final SafeAction1<T> handler, Class<T> clazz) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Fragment holder, int targetID, final SafeAction1<T> handler, Class<T> clazz) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Dialog holder, int targetID, final SafeAction1<T> handler, Class<T> clazz) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(View holder, int targetID, final SafeAction1<T> handler, Class<T> clazz) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(View target, final SafeAction1<T> handler, Class<T> clazz) {
        v_updateLayoutParams(newFinder(target), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Activity holder, int targetID, final SafeAction1<T> handler) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Fragment holder, int targetID, final SafeAction1<T> handler) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(Dialog holder, int targetID, final SafeAction1<T> handler) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(View holder, int targetID, final SafeAction1<T> handler) {
        v_updateLayoutParams(newFinder(holder, targetID), handler);
    }

    public static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(View target, final SafeAction1<T> handler) {
        v_updateLayoutParams(newFinder(target), handler);
    }

    private static <T extends ViewGroup.LayoutParams> void v_updateLayoutParams(SafeFunc0<View> finder, final SafeAction1<T> handler) {
        safeOperateView(finder, new SafeAction1<View>() {
            @SuppressWarnings("unchecked")
            @Override
            public void call(View view) throws Exception {
                T params = (T) view.getLayoutParams();
                if (params != null) {
                    handler.call(params);
                }
            }
        });
    }

    public static void v_forEach(Activity holder, int targetID, final SafeAction1<View> handler) {
        SafeFunc0<ViewGroup> finder = newFinder(holder, targetID);
        v_forEach(finder, handler);
    }

    public static void v_forEach(Fragment holder, int targetID, final SafeAction1<View> handler) {
        SafeFunc0<ViewGroup> finder = newFinder(holder, targetID);
        v_forEach(finder, handler);
    }

    public static void v_forEach(Dialog holder, int targetID, final SafeAction1<View> handler) {
        SafeFunc0<ViewGroup> finder = newFinder(holder, targetID);
        v_forEach(finder, handler);
    }

    public static void v_forEach(View holder, int targetID, final SafeAction1<View> handler) {
        SafeFunc0<ViewGroup> finder = newFinder(holder, targetID);
        v_forEach(finder, handler);
    }

    public static void v_forEach(ViewGroup target, final SafeAction1<View> handler) {
        SafeFunc0<ViewGroup> finder = newFinder(target);
        v_forEach(finder, handler);
    }

    private static void v_forEach(SafeFunc0<ViewGroup> finder, final SafeAction1<View> handler) {
        safeOperateView(finder, new SafeAction1<ViewGroup>() {
            @Override
            public void call(ViewGroup group) throws Exception {
                int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    handler.call(group.getChildAt(i));
                }
            }
        });
    }

    public static class TextWatcherBuilder {
        private Action4<CharSequence, Integer, Integer, Integer> beforeTextChanged;
        private Action4<CharSequence, Integer, Integer, Integer> onTextChanged;
        private Action1<Editable> afterTextChanged;

        private TextWatcherBuilder() {
        }

        public TextWatcherBuilder beforeTextChanged(Action4<CharSequence, Integer, Integer, Integer> handler) {
            this.beforeTextChanged = handler;
            return this;
        }

        public TextWatcherBuilder onTextChanged(Action4<CharSequence, Integer, Integer, Integer> handler) {
            this.onTextChanged = handler;
            return this;
        }

        public TextWatcherBuilder afterTextChanged(Action1<Editable> handler) {
            this.afterTextChanged = handler;
            return this;
        }

        public TextWatcher build() {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (beforeTextChanged != null) {
                        beforeTextChanged.call(s, start, count, after);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (onTextChanged != null) {
                        onTextChanged.call(s, start, before, count);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (afterTextChanged != null) {
                        afterTextChanged.call(s);
                    }
                }
            };
        }
    }

    public static void v_setText(Activity holder, int targetID, CharSequence text) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(Fragment holder, int targetID, CharSequence text) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(Dialog holder, int targetID, CharSequence text) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(View holder, int targetID, CharSequence text) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_setText(finder, text);
    }

    public static void v_setText(TextView target, CharSequence text) {
        SafeFunc0<TextView> finder = newFinder(target);
        v_setText(finder, text);
    }

    private static void v_setText(SafeFunc0<TextView> finder, final CharSequence text) {
        safeOperateView(finder, new SafeAction1<TextView>() {
            @Override
            public void call(TextView view) throws Exception {
                view.setText(text);
            }
        });
    }

    public static TextWatcherBuilder newTextWatcher() {
        return new TextWatcherBuilder();
    }

    public static void v_addTextChanged(Activity holder, int targetID, TextWatcher watcher) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_addTextChanged(finder, watcher);
    }

    public static void v_addTextChanged(Fragment holder, int targetID, TextWatcher watcher) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_addTextChanged(finder, watcher);
    }

    public static void v_addTextChanged(Dialog holder, int targetID, TextWatcher watcher) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_addTextChanged(finder, watcher);
    }

    public static void v_addTextChanged(View holder, int targetID, TextWatcher watcher) {
        SafeFunc0<TextView> finder = newFinder(holder, targetID);
        v_addTextChanged(finder, watcher);
    }

    public static void v_addTextChanged(TextView target, TextWatcher watcher) {
        SafeFunc0<TextView> finder = newFinder(target);
        v_addTextChanged(finder, watcher);
    }

    private static void v_addTextChanged(SafeFunc0<TextView> finder, final TextWatcher watcher) {
        safeOperateView(finder, new SafeAction1<TextView>() {
            @Override
            public void call(TextView view) throws Exception {
                if (watcher != null) {
                    view.addTextChangedListener(watcher);
                }
            }
        });
    }

    public static void v_setImageDrawable(Activity holder, int targetID, final Drawable drawable) {
        SafeFunc0<ImageView> finder = newFinder(holder, targetID);
        v_setImageDrawable(finder, drawable);
    }

    public static void v_setImageDrawable(Fragment holder, int targetID, final Drawable drawable) {
        SafeFunc0<ImageView> finder = newFinder(holder, targetID);
        v_setImageDrawable(finder, drawable);
    }

    public static void v_setImageDrawable(Dialog holder, int targetID, final Drawable drawable) {
        SafeFunc0<ImageView> finder = newFinder(holder, targetID);
        v_setImageDrawable(finder, drawable);
    }

    public static void v_setImageDrawable(View holder, int targetID, final Drawable drawable) {
        SafeFunc0<ImageView> finder = newFinder(holder, targetID);
        v_setImageDrawable(finder, drawable);
    }

    public static void v_setImageDrawable(ImageView target, final Drawable drawable) {
        v_setImageDrawable(newFinder(target), drawable);
    }

    private static void v_setImageDrawable(SafeFunc0<ImageView> finder, final Drawable drawable) {
        safeOperateView(finder, new SafeAction1<ImageView>() {
            @Override
            public void call(ImageView imageView) throws Exception {
                imageView.setImageDrawable(drawable);
            }
        });
    }
}
