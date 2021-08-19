package com.example.common.fraction;

import com.example.common.base.BaseContract;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.window.dialog.ToastDialog;

public abstract class PresenterFraction <Presenter extends BaseContract.Presenter>
        extends BaseFraction
        implements BaseContract.View<Presenter>{

    protected Presenter mPresenter;

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        Component component =  super.onComponentAttached(scatter, container, intent);
        initPresenter();

        return component;
    }

    protected abstract void initPresenter();

    @Override
    public void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        if(mPlaceholderView != null) {
            mPlaceholderView.showLoading();
        }
    }

    @Override
    public void showError(String message) {
        if(mPlaceholderView != null) {
            mPlaceholderView.showError(message);
        } else {
            new ToastDialog(getContext()).setText(message).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
