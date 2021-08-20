package com.example.common.component;

import com.example.common.ResourceTable;
import ohos.aafwk.ability.Ability;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.AbilityContext;
import ohos.app.Context;

public class Toast {
    public static void show(Context context, String message) {
        DirectionalLayout layout = (DirectionalLayout) LayoutScatter
                .getInstance(context)
                .parse(ResourceTable.Layout_toast, null, false);

        Text text = (Text) layout.getComponentAt(0);
        text.setText(message);

        new ToastDialog(context)
                .setContentCustomComponent(layout)
                .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT,
                        DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                .setDuration(1200)
                .show();
    }
}
