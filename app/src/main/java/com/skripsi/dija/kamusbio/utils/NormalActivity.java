package com.skripsi.dija.kamusbio.utils;


import android.support.v7.app.ActionBar;

import com.skripsi.dija.kamusbio.view.base.BaseActivity;


public abstract class NormalActivity extends BaseActivity {
    protected abstract boolean isActionBarEnable();

    @Override
    protected void onBindView() {
        super.onBindView();
        initActionBar();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (!isActionBarEnable()) {
                actionBar.hide();
            }
        }
    }

}
