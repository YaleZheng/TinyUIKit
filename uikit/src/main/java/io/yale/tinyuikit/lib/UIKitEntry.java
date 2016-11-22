package io.yale.tinyuikit.lib;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;

/**
 * Created by yalez on 2016/11/8.
 */

public class UIKitEntry {
    private UIKitEntry() {
    }

    public static void init(Context ctx) {
        Application application = null;

        if (ctx instanceof Application) {
            application = Application.class.cast(ctx);
        } else if (ctx instanceof Activity) {
            application = Activity.class.cast(ctx).getApplication();
        } else if (ctx instanceof Service) {
            application = Service.class.cast(ctx).getApplication();
        }

        if (application != null) {
            DrawableExtension.init(application);
            ContextExtension.init(application);
        }
    }

    public static void destory() {
        DrawableExtension.destroy();
        ContextExtension.destroy();
    }
}
