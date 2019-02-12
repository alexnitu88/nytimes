package me.alexnitu.common;

public interface BasePresenter<T extends BaseView> {

    void attach(T view);

    void detach();
}
