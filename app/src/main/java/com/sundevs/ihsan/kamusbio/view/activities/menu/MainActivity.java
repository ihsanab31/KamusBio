package com.sundevs.ihsan.kamusbio.view.activities.menu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;
import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.view.activities.kamus.IndonesiaActivity;
import com.sundevs.ihsan.kamusbio.view.activities.kamus.LatinActivity;
import com.sundevs.ihsan.kamusbio.view.base.NormalActivity;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("SetTextI18n")
public class MainActivity extends NormalActivity {
    Trace trace;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performaTrace(trace);
        initView();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick({R.id.btn_kmsindo, R.id.btn_kmslatin, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_kmsindo:
                startActivity(IndonesiaActivity.class);
                break;
            case R.id.btn_kmslatin:
                startActivity(LatinActivity.class);
                break;
            case R.id.btn_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Apakah anda yakin keluar dari aplikasi ini?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                MainActivity.this.onBackPressed();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
    }


    private void initView(){
        txtTitle.setText("Selamat Datang Di Kamus Biologi");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin keluar dari aplikasi ini?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        MainActivity.this.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
