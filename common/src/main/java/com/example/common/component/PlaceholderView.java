package com.example.common.component;

public interface PlaceholderView {
    void showEmpty();
    void showSuccess();
    void showError(String message);
    void showNetworkError();
    void showLoading();
    void showResult(boolean result);
}
