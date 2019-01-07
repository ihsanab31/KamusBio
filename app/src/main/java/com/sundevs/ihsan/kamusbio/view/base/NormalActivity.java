package com.sundevs.ihsan.kamusbio.view.base;


import android.support.v7.app.ActionBar;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for hidden actionbar
 */
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
