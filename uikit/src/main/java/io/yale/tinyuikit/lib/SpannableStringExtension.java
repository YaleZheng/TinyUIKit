package io.yale.tinyuikit.lib;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

import java.nio.charset.Charset;

import static android.text.SpannableStringBuilder.valueOf;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * Created by yalez on 2016/11/9.
 */

public class SpannableStringExtension {
    private SpannableStringExtension() {
    }

    public static SpannableStringBuilder s_setImage(CharSequence text, Drawable drawable, int verticalAlign) {
        return s_setSpan(valueOf(text), new ImageSpan(drawable, verticalAlign));
    }

    public static SpannableStringBuilder s_setImage(CharSequence text, Drawable drawable) {
        return s_setSpan(valueOf(text), new ImageSpan(drawable));
    }

    public static SpannableStringBuilder s_setScaleX(CharSequence text, float factor) {
        return s_setSpan(valueOf(text), new ScaleXSpan(factor));
    }

    public static SpannableStringBuilder s_setURL(CharSequence text, String url) {
        return s_setSpan(text, new URLSpan(url));
    }

    public static SpannableStringBuilder s_setStrikethrough(CharSequence text) {
        return s_setSpan(text, new StrikethroughSpan());
    }

    public static SpannableStringBuilder s_setUnderline(CharSequence text) {
        return s_setSpan(text, new UnderlineSpan());
    }

    public static SpannableStringBuilder s_setBackground(CharSequence text, int color) {
        return s_setSpan(text, new BackgroundColorSpan(color));
    }

    public static SpannableStringBuilder s_setTypeface(CharSequence text, String family) {
        return s_setSpan(text, new TypefaceSpan(family));
    }

    public static SpannableStringBuilder s_setRelativeFontSize(CharSequence text, float factor) {
        return s_setSpan(text, new RelativeSizeSpan(factor));
    }

    public static SpannableStringBuilder s_setFontSize(CharSequence text, int sizeInPx) {
        return s_setSpan(text, new AbsoluteSizeSpan(sizeInPx, false));
    }

    public static SpannableStringBuilder s_setTextColor(CharSequence text, int color) {
        return s_setSpan(valueOf(text), new ForegroundColorSpan(color));
    }

    public static SpannableStringBuilder s_concat(CharSequence first, CharSequence... others) {
        SpannableStringBuilder sb = valueOf(first);
        for (CharSequence other : others) {
            sb.append(other).append('\n');
        }
        return sb;
    }

    public static SpannableStringBuilder s_concatNoBreak(CharSequence first, CharSequence... others) {
        SpannableStringBuilder sb = valueOf(first);
        for (CharSequence other : others) {
            sb.append(other);
        }
        return sb;
    }

    private static SpannableStringBuilder s_setSpan(CharSequence text, Object span) {
        SpannableStringBuilder sb = valueOf(text);
        if (!TextUtils.isEmpty(text)) {
            sb.setSpan(span, 0, text.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sb;
    }
}
