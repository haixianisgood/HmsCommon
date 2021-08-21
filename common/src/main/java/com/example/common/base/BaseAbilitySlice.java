package com.example.common.base;

import com.example.common.component.PlaceholderView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;


public abstract class BaseAbilitySlice extends AbilitySlice {
    protected PlaceholderView mPlaceholderView;
    protected ComponentContainer mComponentContainer;
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        Component component = LayoutScatter.getInstance(this).parse(layoutId(), null, false);
        mComponentContainer = (ComponentContainer) component;
        super.setUIContent(mComponentContainer);

        beforeShow();
        show();
        afterShow();
    }

    protected abstract int layoutId();

    protected void beforeShow() {

    }

    protected void show() {

    }

    protected void afterShow() {

    }

    public void setPlaceholderView(PlaceholderView placeholderView) {
        this.mPlaceholderView = placeholderView;
    }
}
