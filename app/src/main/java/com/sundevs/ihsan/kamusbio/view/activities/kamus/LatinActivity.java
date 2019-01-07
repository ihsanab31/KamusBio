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
import com.sundevs.ihsan.kamusbio.adapter.LatinAdapter;
import com.sundevs.ihsan.kamusbio.api.BaseURL;
import com.sundevs.ihsan.kamusbio.api.EndPoint;
import com.sundevs.ihsan.kamusbio.model.Latin;
import com.sundevs.ihsan.kamusbio.model.response.LatinResponse;
import com.sundevs.ihsan.kamusbio.view.base.NormalActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatinActivity extends NormalActivity {
    EndPoint apiSevice;
    List<Latin> kamusList = new ArrayList<>();
    private final int REQ_CODE_SPEECH_INPUT = 100;
    LatinAdapter latinAdapter;
    @BindView(R.id.et_search)
    CustomEditText etSearch;
    @BindView(R.id.rv_latin)
    RecyclerView rvLatin;

    @Override
    protected int getActivityView() {
        return R.layout.activity_latin;
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
        latinAdapter = new LatinAdapter(this, kamusList);
        rvLatin.setHasFixedSize(true);
        rvLatin.setLayoutManager(new LinearLayoutManager(this));
        rvLatin.setAdapter(latinAdapter);
    }

    private void getData(){
        showProgressDialog("Please Wait");
        apiSevice.getLatin().enqueue(new Callback<LatinResponse>() {
            @Override
            public void onResponse(@NonNull Call<LatinResponse> call, @NonNull Response<LatinResponse> response) {
                if (response.body().isStatus()){
                    dissmisProgressDialog();
                    kamusList= response.body().getData();
                    rvLatin.setAdapter(new LatinAdapter(getApplicationContext(), kamusList));
                    latinAdapter.notifyDataSetChanged();
                }else {
                    dissmisProgressDialog();
                    showMessage("Error Response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LatinResponse> call, @NonNull Throwable t) {
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
                final List<Latin> filteredList = new ArrayList<>();
                for (int i = 0; i < kamusList.size(); i++) {
                    final String bidang = kamusList.get(i).getNamaLatin().toLowerCase();
                    if (bidang.contains(query)) {
                        filteredList.add(kamusList.get(i));
                    }
                }

                rvLatin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                latinAdapter= new LatinAdapter(getApplicationContext(), filteredList);
                rvLatin.setAdapter(latinAdapter);
                latinAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
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
