package com.example.common.fraction;

import com.example.common.component.PlaceholderView;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;

public abstract class BaseFraction extends Fraction {
    protected Component mComponent;
    protected PlaceholderView mPlaceholderView;
    protected boolean isInitialized = false;
    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        mComponent = scatter.parse(layoutId(), container, false);
        return mComponent;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);

        if(!isInitialized) {
            initComponent();
            isInitialized = true;
        }
        before();
        show();
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }

    @Override
    protected void onComponentDetach() {
        super.onComponentDetach();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected abstract int layoutId();

    protected abstract void before();

    protected abstract void initComponent();

    protected abstract void show();

    public void setPlaceholderView(PlaceholderView placeholderView) {
        this.mPlaceholderView = placeholderView;
    }
}
