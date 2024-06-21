package com.example.gaodenavi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements Inputtips.InputtipsListener, TextWatcher,RvAdapter.OnItemClickListener {
    private RvAdapter rvAdapter;
    private Inputtips inputtips;
    //    private AMapNavi aMapNavi;
    private EditText editText;
    private RecyclerView recyclerView;
    private ArrayList<Tip> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.search_edit);
        editText.addTextChangedListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        rvAdapter = new RvAdapter(this, recyclerView, new ArrayList<>());
        rvAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(rvAdapter);

        inputtips = new Inputtips(this, (InputtipsQuery) null);
        inputtips.setInputtipsListener(this);
    }



    //通过适配器把数据展示到recyclerView中去
    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        rvAdapter.setData(list);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        InputtipsQuery inputtipsQuery = new InputtipsQuery(String.valueOf(charSequence), null);
        inputtipsQuery.setCityLimit(true);
        inputtips.setQuery(inputtipsQuery);
        inputtips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int postion, Tip data) {

    }
    @Override
    public void onBackPressed() {
        Log.d("SearchActivity", "onBackPressed Called");
        super.onBackPressed();
    }


}