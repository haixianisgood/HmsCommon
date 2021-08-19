package com.example.common.base;

public class BasePresenter<View extends BaseContract.View> implements BaseContract.Presenter {
    private View mView;

    public BasePresenter(View view) {
        this.setView(view);
    }

    @SuppressWarnings("unchecked")
    public void setView(View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if(mView != null) {
            mView.showLoading();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        if(mView != null) {
            mView.setPresenter(null);
        }
        mView = null;
    }


}
