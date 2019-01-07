package com.sundevs.ihsan.kamusbio.view.activities.user;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;
import com.libizo.CustomEditText;
import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.api.BaseURL;
import com.sundevs.ihsan.kamusbio.api.EndPoint;
import com.sundevs.ihsan.kamusbio.model.response.UserResponse;
import com.sundevs.ihsan.kamusbio.utils.preference.SessionManager;
import com.sundevs.ihsan.kamusbio.view.activities.menu.MainActivity;
import com.sundevs.ihsan.kamusbio.view.base.NormalActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserNameActivity extends NormalActivity {
    Trace myTrace;
    SessionManager session;
    @BindView(R.id.et_nama)
    CustomEditText etNama;
    @BindView(R.id.edit)
    CustomEditText edit;
    String token = "";
    EndPoint apiService;

    @Override
    protected int getActivityView() {
        return R.layout.activity_user_name;
    }

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        initView();
        performaTrace(myTrace);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        inputUser();
    }

    public void initView() {
        if (session.isLoggedIn()) {
            startActivityNoHistory(MainActivity.class);
        }
        apiService = BaseURL.getAPIService();
        token = FirebaseInstanceId.getInstance().getToken();
    }

    private void inputUser() {
        showProgressDialog("Harap Tunggu...");
        apiService.inputUser(etNama.getText().toString(), token, edit.getText().toString()).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.body().getSuccess() == 1) {
                    session.createLoginSession(etNama.getText().toString(), edit.getText().toString());
                    dissmisProgressDialog();
                    startActivityNoHistory(MainActivity.class);
                }else {
                    dissmisProgressDialog();
                    showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                showMessage(t.getMessage());
                dissmisProgressDialog();
            }
        });

    }
}
