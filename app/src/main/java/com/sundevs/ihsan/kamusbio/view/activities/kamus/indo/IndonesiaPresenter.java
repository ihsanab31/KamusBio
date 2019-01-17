package com.sundevs.ihsan.kamusbio.view.activities.kamus.indo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.api.BaseURL;
import com.sundevs.ihsan.kamusbio.api.EndPoint;
import com.sundevs.ihsan.kamusbio.model.Kamus;
import com.sundevs.ihsan.kamusbio.model.response.KamusResponse;
import com.sundevs.ihsan.kamusbio.view.base.BasePresnter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * Tanggal  : 17/01/2019
 * ------------------------------
 * This class for
 */
public class IndonesiaPresenter extends BasePresnter<IndonesiaView> {
    private List<Kamus> kamusList = new ArrayList<>();
    IndonesiaPresenter(IndonesiaView view){
        super.attach(view);
    }

    void getIndonesia(final Activity activity){
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setMessage(activity.getString(R.string.please_wait));
        dialog.show();
        EndPoint apiService = BaseURL.getAPIService();
        apiService.getKamus().enqueue(new Callback<KamusResponse>() {
            @Override
            public void onResponse(@NonNull Call<KamusResponse> call, @NonNull Response<KamusResponse> response) {
                if (response.body().isStatus()) {
                    dialog.dismiss();
                    kamusList = response.body().getData();
                    mView.onLoad(kamusList);
                } else {
                    dialog.dismiss();
                    Toast.makeText(activity, "Error Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KamusResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
