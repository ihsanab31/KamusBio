package com.skripsi.dija.kamusbio.view.activities.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.api.BaseURL;
import com.skripsi.dija.kamusbio.api.EndPoint;
import com.skripsi.dija.kamusbio.model.response.UserResponse;
import com.skripsi.dija.kamusbio.utils.preference.SessionManager;
import com.skripsi.dija.kamusbio.view.activities.menu.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 07, January, 2019
 * ------------------------------
 * This class for
 */
class UserNamePresenter  {
    private  UserNameView userNameView;
    private Activity activity;

    UserNamePresenter(UserNameView userNameView, Activity activity) {
        this.userNameView = userNameView;
        this.activity = activity;
    }

    void inputUser(final String name, final String token, final String no_hp){
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setMessage(activity.getString(R.string.please_wait));
        dialog.show();
        final SessionManager sessionManager = new SessionManager(activity);
        EndPoint apiService = BaseURL.getAPIService();
        apiService.inputUser(name,token, no_hp).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    Log.d("onFailure_Register", name+"\n"+token);
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    sessionManager.createLoginSession(name,no_hp);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }else {
                    dialog.dismiss();
                    userNameView.onUserNameError(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                t.getMessage();
            }
        });
    }
}
