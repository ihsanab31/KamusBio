package com.skripsi.dija.kamusbio.view.activities.user;

import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;
import com.libizo.CustomEditText;
import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.utils.NormalActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserNameActivity extends NormalActivity  implements UserNameView{
    Trace myTrace;
    @BindView(R.id.et_nama)
    CustomEditText etNama;
    @BindView(R.id.edit)
    CustomEditText edit;
    String token = "";
    UserNamePresenter userNamePresenter;
    @Override
    protected int getActivityView() {
        return R.layout.activity_user_name;
    }

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = FirebaseInstanceId.getInstance().getToken();
        userNamePresenter = new UserNamePresenter(this, this);
        performaTrace(myTrace);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        String name= etNama.getText().toString();
        String no_hp= edit.getText().toString();
        if (!name.isEmpty() && !no_hp.isEmpty()){
            userNamePresenter.inputUser(name, token, no_hp);
        }else {
            showMessage("Nama dan no telepon tidak boleh kosong");
        }

    }


    @Override
    public void onUserNameError(String message) {
        showMessage(getString(R.string.toasterror));
    }
}
