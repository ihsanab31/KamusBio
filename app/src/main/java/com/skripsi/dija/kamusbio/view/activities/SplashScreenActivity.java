package com.skripsi.dija.kamusbio.view.activities;

import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;
import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.utils.preference.SessionManager;
import com.skripsi.dija.kamusbio.view.activities.menu.MainActivity;
import com.skripsi.dija.kamusbio.view.activities.user.UserNameActivity;
import com.skripsi.dija.kamusbio.utils.NormalActivity;

public class SplashScreenActivity extends NormalActivity {
    SessionManager sessionManager;
    Trace myTrace;
    @Override
    protected int getActivityView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        performaTrace(myTrace);
        sessionManager = new SessionManager(getApplicationContext());
        int interval = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLoggedIn()) {
                    startActivityNoHistory(MainActivity.class);
                } else {
                    startActivityNoHistory(UserNameActivity.class);
                }

            }
        }, interval);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }
}
