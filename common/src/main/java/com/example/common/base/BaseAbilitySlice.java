package com.example.common.base;

import com.example.common.component.PlaceholderView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;


public abstract class BaseAbilitySlice extends AbilitySlice {
    protected PlaceholderView mPlaceholderView;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(layoutId());
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
