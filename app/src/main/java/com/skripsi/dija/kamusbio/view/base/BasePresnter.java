package com.skripsi.dija.kamusbio.view.base;


public class BasePresnter<V> {
    public V mView;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }
}
