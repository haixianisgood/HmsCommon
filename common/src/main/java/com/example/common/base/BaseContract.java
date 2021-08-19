package com.example.common.base;

import com.example.common.provider.BaseProvider;

public interface BaseContract {
    interface Presenter {
        void start();
        void destroy();
    }

    interface View <P> {
        void setPresenter(P presenter);
        void showLoading();
        void showError(String message);
    }

    interface ListView<P extends Presenter, ViewModel> extends View<P> {
        BaseProvider<ViewModel> listProvider();
        void onProviderDataChanged();
    }
}
