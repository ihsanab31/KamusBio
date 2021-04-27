package com.skripsi.dija.kamusbio.view.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.skripsi.dija.kamusbio.BaseView;
import com.skripsi.dija.kamusbio.KamusApp;
import com.skripsi.dija.kamusbio.R;

import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public abstract class BaseActivityList<PT extends BasePresnter> extends AppCompatActivity implements BaseView {

    public PT mPresenter;
    private ProgressDialog progressDialog;
    private boolean isPaused;

    @LayoutRes
    protected abstract int getActivityView();

    public abstract PT initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityView());
        ButterKnife.bind(this);
        onBindView();
        getSupportActionBar().hide();
        BaseActivityListPermissionsDispatcher.needPermisonWithPermissionCheck(this);
        mPresenter = initPresenter();
    }

    protected void startService() {
        KamusApp kamusApp = (KamusApp) getApplication();
    }

    protected void onBindView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressDialog(String message) {
        if (isPaused) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void showProgessDialog(@StringRes int message) {
        if (isPaused) return;
        showProgressDialog(getString(message));
    }

    @Override
    public void dissmisProgressDialog() {
        if (isPaused) return;
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showMassage(@StringRes int message) {
        if (isPaused) return;
        showMessage(getString(message));
    }

    @Override
    public void showMessage(String message) {
        if (isPaused) return;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    public Bundle getBundle() {
        if (getIntent().getExtras() != null) return getIntent().getExtras();
        else return new Bundle();
    }

    public void startActivity(Class target) {
        startActivity(new Intent(this, target));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void startActivityNoHistory(Class targer) {
        Intent intent = new Intent(getApplicationContext(), targer);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void performaTrace(Trace trace){
        Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
        myTrace.start();
    }
    public void fullScreen(AppCompatActivity appCompatActivity) {
        appCompatActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BaseActivityListPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needPermison() {
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDeniedNeedPermiison() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage("You must allow permission to use this app");
        dialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(android.content.DialogInterface dialogInterface) {
                BaseActivityList.this.finishAffinity();
            }
        });
        dialog.setButton(android.content.DialogInterface.BUTTON_POSITIVE, "OK", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showNeverAskNeedPermiison() {
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleNeedPermiison(final PermissionRequest request) {
        request.proceed();
    }
}
