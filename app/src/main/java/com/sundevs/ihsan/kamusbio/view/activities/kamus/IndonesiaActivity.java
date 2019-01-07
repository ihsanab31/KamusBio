package com.sundevs.ihsan.kamusbio.view.activities.kamus;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.libizo.CustomEditText;
import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.adapter.IndonesiaAdapter;
import com.sundevs.ihsan.kamusbio.api.BaseURL;
import com.sundevs.ihsan.kamusbio.api.EndPoint;
import com.sundevs.ihsan.kamusbio.model.Kamus;
import com.sundevs.ihsan.kamusbio.model.response.KamusResponse;
import com.sundevs.ihsan.kamusbio.view.base.NormalActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndonesiaActivity extends NormalActivity {
    EndPoint apiSevice;
    List<Kamus> kamusList = new ArrayList<>();
    @BindView(R.id.et_search)
    CustomEditText etSearch;
    @BindView(R.id.rv_indo)
    RecyclerView rvIndo;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    IndonesiaAdapter indonesiaAdapter;
    @Override
    protected int getActivityView() {
        return R.layout.activity_indonesia;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiSevice =BaseURL.getAPIService();
        initView();
        getData();
        initSearch();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick({R.id.iv_back, R.id.btn_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_voice:
                promptSpeechInput();
                break;
        }
    }

    private void initView(){
        indonesiaAdapter = new IndonesiaAdapter(this, kamusList);
        rvIndo.setHasFixedSize(true);
        rvIndo.setLayoutManager(new LinearLayoutManager(this));
        rvIndo.setAdapter(indonesiaAdapter);
    }

    private void getData(){
        showProgressDialog("Please Wait");
        apiSevice.getKamus().enqueue(new Callback<KamusResponse>() {
            @Override
            public void onResponse(@NonNull Call<KamusResponse> call, @NonNull Response<KamusResponse> response) {
                if (response.body().isStatus()){
                    dissmisProgressDialog();
                    kamusList= response.body().getData();
                    rvIndo.setAdapter(new IndonesiaAdapter(getApplicationContext(), kamusList));
                    indonesiaAdapter.notifyDataSetChanged();
                }else {
                    dissmisProgressDialog();
                    showMessage("Error Response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<KamusResponse> call, @NonNull Throwable t) {
                dissmisProgressDialog();
                showMessage(t.getMessage());
            }
        });
    }

    private void initSearch(){
        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<Kamus> filteredList = new ArrayList<>();

                for (int i = 0; i < kamusList.size(); i++) {

                    final String bidang = kamusList.get(i).getNamaKamus().toLowerCase();
                    if (bidang.contains(query)) {
                        filteredList.add(kamusList.get(i));
                    }
                }

                rvIndo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                indonesiaAdapter= new IndonesiaAdapter(getApplicationContext(), filteredList);
                rvIndo.setAdapter(indonesiaAdapter);
                indonesiaAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "id-ID");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etSearch.setText(result.get(0));
                }
                break;
            }

        }
    }
}
