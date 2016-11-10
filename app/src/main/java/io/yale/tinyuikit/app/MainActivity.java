package io.yale.tinyuikit.app;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import io.yale.tinyuikit.lib.DrawableExtension;
import io.yale.tinyuikit.lib.RecyclerViewExtensions;
import io.yale.tinyuikit.lib.RecyclerViewExtensions.SimpleAdapter;
import io.yale.tinyuikit.lib.ViewExtension;

import static io.yale.tinyuikit.lib.DrawableExtension.*;
import static io.yale.tinyuikit.lib.RecyclerViewExtensions.newAdapter;
import static io.yale.tinyuikit.lib.ViewExtension.*;

/**
 * Created by yalez on 2016/11/8.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleAdapter<String> adapter = newAdapter(Arrays.asList("2333", "2323"))
                .onCreateVH((parent, type) -> {
                    return null;
                })
                .onVHCreated((ad, type, vh, binder) -> {
                    View itemView = vh.itemView;
                    binder.bind("nameLabel", v_findView(itemView, android.R.id.text1));
                    binder.bind("nameImage", v_findView(itemView, android.R.id.icon));
                    binder.bind("nameLabel", v_findView(itemView, android.R.id.text1));
                })
                .onBindVH((ad, vh, binder, pos) -> {
                    String item = ad.getItem(pos);
                    v_setImageDrawable(binder.find("nameImage"), new ColorDrawable(0));
                })
                .build();
    }
}
