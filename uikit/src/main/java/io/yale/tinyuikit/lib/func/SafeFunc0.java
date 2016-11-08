package io.yale.tinyuikit.lib.func;

/**
 * Created by yalez on 2016/11/8.
 */

public interface SafeFunc0<R> extends SafeFunction {
    R call() throws Exception;
}
