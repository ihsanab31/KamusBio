package com.skripsi.dija.kamusbio;

import android.support.annotation.StringRes;


public interface BaseView {

    void showProgressDialog(String message);

    void showProgessDialog(@StringRes int message);

    void dissmisProgressDialog();

    void showMessage(String message);

    void showMassage(@StringRes int message);
}
