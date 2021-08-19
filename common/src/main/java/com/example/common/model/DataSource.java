package com.example.common.model;

public interface DataSource {
    interface SuccessCallback<T> {
        void onSuccess(T t);
    }

    interface FailedCallback {
        void onDataNotAvailable(String message);
    }

    interface DataCallback<T> extends SuccessCallback<T>, FailedCallback {

    }

    void dispose();
}
