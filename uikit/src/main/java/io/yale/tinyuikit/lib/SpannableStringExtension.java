package io.yale.tinyuikit.lib;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import static android.text.SpannableStringBuilder.valueOf;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * Created by yalez on 2016/11/9.
 */

public class SpannableStringExtension {
    private SpannableStringExtension() {
    }

    public static SpannableStringBuilder s_setTextColor(CharSequence text, int color) {
        return s_setTextColor(valueOf(text), color);
    }

    public static SpannableStringBuilder s_setTextColor(SpannableStringBuilder text, int color) {
        if (!TextUtils.isEmpty(text)) {
            text.setSpan(new ForegroundColorSpan(color), 0, text.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return text;
    }

    public static SpannableStringBuilder s_concat(CharSequence first, CharSequence... others) {
        return s_concat(valueOf(first), others);
    }

    public static SpannableStringBuilder s_concat(SpannableStringBuilder first, CharSequence... others) {
        SpannableStringBuilder ret = first;
        for (CharSequence other : others) {
            ret = ret.append(other).append('\n');
        }
        return ret;
    }

    public static SpannableStringBuilder s_concatNoBreak(CharSequence first, CharSequence... others) {
        if (first instanceof SpannableStringBuilder) {
            return s_concatNoBreak(SpannableStringBuilder.class.cast(first));
        }

        return s_concatNoBreak(valueOf(first), others);
    }

    public static SpannableStringBuilder s_concatNoBreak(SpannableStringBuilder first, CharSequence... others) {
        SpannableStringBuilder ret = first;
        for (CharSequence other : others) {
            ret = ret.append(other);
        }
        return first;
    }
}
