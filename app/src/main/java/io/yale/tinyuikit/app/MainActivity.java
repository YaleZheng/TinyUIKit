package io.yale.tinyuikit.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import io.yale.tinyuikit.lib.RecyclerViewExtensions;
import io.yale.tinyuikit.lib.RecyclerViewExtensions.SimpleAdapter;
import io.yale.tinyuikit.lib.ViewExtension;

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
                })
                .onBindVH((ad, vh, binder, pos) -> {
                    String item = ad.getItem(pos);
                    TextView nameLabel = binder.find("NameLabel");
                    v_setText(nameLabel, "233");
                })
                .build();
    }
}
