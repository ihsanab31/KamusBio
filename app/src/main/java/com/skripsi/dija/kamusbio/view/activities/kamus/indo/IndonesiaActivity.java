package com.skripsi.dija.kamusbio.view.activities.kamus.indo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.RecognizerIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.libizo.CustomEditText;
import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.adapter.IndonesiaAdapter;
import com.skripsi.dija.kamusbio.model.KamusItem;
import com.skripsi.dija.kamusbio.utils.Constant;
import com.skripsi.dija.kamusbio.view.base.BaseActivityList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IndonesiaActivity extends BaseActivityList<IndonesiaPresenter> implements IndonesiaView {
    List<KamusItem> kamusList = new ArrayList<>();
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
        initView();
        initSearch();
        mPresenter.getIndonesia(this);
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

    private void initView() {
        indonesiaAdapter = new IndonesiaAdapter(this, kamusList);
        rvIndo.setHasFixedSize(true);
        rvIndo.setLayoutManager(new LinearLayoutManager(this));
        rvIndo.setAdapter(indonesiaAdapter);
    }


    private void initSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                // get data yang akan dicari
                query = query.toString().toLowerCase();
                //list data kamus
                final List<KamusItem> filteredList = new ArrayList<>();

                for (int i = 0; i < kamusList.size(); i++) {
                    //proses pencocokan data
                    final String bidang = kamusList.get(i).getNamaKamus().toLowerCase();
                    //proses pencocokan perhuruf
                    if (bidang.contains(query)) {
                        // jika ada yang cocok maka di masukkan kesalam list
                        filteredList.add(kamusList.get(i));
                    }
                }
                // proses menampikan data
                rvIndo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                indonesiaAdapter = new IndonesiaAdapter(getApplicationContext(), filteredList);
                rvIndo.setAdapter(indonesiaAdapter);
                indonesiaAdapter.notifyDataSetChanged();  // data set changed
                rvIndo.setVisibility(View.VISIBLE);
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

    @Override
    public void onLoad(List<KamusItem> data) {
        indonesiaAdapter.refresh(data);
        kamusList = data;
    }

    @Override
    public IndonesiaPresenter initPresenter() {
        return new IndonesiaPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Constant.mBundleRecyclerViewState = new Bundle();
        Parcelable liststate = rvIndo.getLayoutManager().onSaveInstanceState();
        Constant.mBundleRecyclerViewState.putParcelable(Constant.KEY_RECYCLER_STATE, liststate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constant.mBundleRecyclerViewState !=null) {
            Parcelable liststate = Constant.mBundleRecyclerViewState.getParcelable(Constant.KEY_RECYCLER_STATE);
            rvIndo.getLayoutManager().onRestoreInstanceState(liststate);
        }
    }
}
