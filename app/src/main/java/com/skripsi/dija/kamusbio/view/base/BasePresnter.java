package com.skripsi.dija.kamusbio.view.base;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * Tanggal  : 16/01/2019
 * ------------------------------
 * This class for
 */
public class BasePresnter<V> {
    public V mView;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }
}
