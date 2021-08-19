package com.example.common.base;

public interface BaseView <T extends BasePresenter> {
    void setPresenter(T presenter);
}
