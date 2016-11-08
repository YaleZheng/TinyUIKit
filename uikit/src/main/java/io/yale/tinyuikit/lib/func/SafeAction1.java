package io.yale.tinyuikit.lib.func;

/**
 * Created by yalez on 2016/11/8.
 */

public interface SafeAction1<T> extends SafeFunction {
    void call(T arg0) throws Exception;
}
