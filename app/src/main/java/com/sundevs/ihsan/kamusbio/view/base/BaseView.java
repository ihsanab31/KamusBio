package com.sundevs.ihsan.kamusbio.view.base;

import android.support.annotation.StringRes;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for view dialog
 */
public interface BaseView {

    void showProgressDialog(String message);

    void showProgessDialog(@StringRes int message);

    void dissmisProgressDialog();

    void showMessage(String message);

    void showMassage(@StringRes int message);
}
