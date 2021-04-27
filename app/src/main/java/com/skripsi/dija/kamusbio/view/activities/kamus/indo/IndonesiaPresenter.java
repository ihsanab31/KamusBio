package com.skripsi.dija.kamusbio.view.activities.kamus.indo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.api.BaseURL;
import com.skripsi.dija.kamusbio.api.EndPoint;
import com.skripsi.dija.kamusbio.model.KamusItem;
import com.skripsi.dija.kamusbio.model.response.KamuBioResponse;
import com.skripsi.dija.kamusbio.utils.Constant;
import com.skripsi.dija.kamusbio.view.base.BasePresnter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IndonesiaPresenter extends BasePresnter<IndonesiaView> {
    private List<KamusItem> kamusList = new ArrayList<>();
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
        apiService.getKamus(Constant.token).enqueue(new Callback<KamuBioResponse>() {
            @Override
            public void onResponse(@NonNull Call<KamuBioResponse> call, @NonNull Response<KamuBioResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    dialog.dismiss();
                    kamusList = response.body().getCity();
                    mView.onLoad(kamusList);
                } else {
                    dialog.dismiss();
                    Toast.makeText(activity, "Error Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KamuBioResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
