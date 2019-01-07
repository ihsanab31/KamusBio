package com.sundevs.ihsan.kamusbio.view.activities.kamus;

import android.os.Bundle;

import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.view.base.NormalActivity;

public class LatinActivity extends NormalActivity {

    @Override
    protected int getActivityView() {
        return R.layout.activity_latin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }
}
