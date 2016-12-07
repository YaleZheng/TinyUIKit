package io.yale.tinyuikit.lib;

import android.text.TextUtils;

import io.yale.rxfunction.lib.func.Action1;
import io.yale.rxfunction.lib.func.SafeAction0;
import io.yale.rxfunction.lib.func.SafeFunc0;


/**
 * Created by yalez on 2016/11/10.
 */

public class ObjectExtension {
    private ObjectExtension() {
    }

    public static <T> void safeOperate(SafeFunc0<T> getter, Action1<T> operator) {
        try {
            T target = getter.call();
            if (target != null) {
                operator.call(target);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void safeCall(SafeAction0 block) {
        if (block != null) {
            try {
                block.call();
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    public static <R> R safeGet(SafeFunc0<R> getter, R defaultValue) {
        if (getter == null) {
            return defaultValue;
        }

        try {
            R ret = getter.call();
            if (ret == null) {
                return defaultValue;
            }

            if (ret instanceof CharSequence && TextUtils.isEmpty(CharSequence.class.cast(ret))) {
                return defaultValue;
            }

            return ret;
        } catch (Exception e) {
            handleException(e);
            return defaultValue;
        }
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
    }
}
