package com.goldencis.ganksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.goldencis.ganksample.http.Api;
import com.goldencis.ganksample.http.WealService;
import com.goldencis.ganksample.main.MainAdapter;
import com.goldencis.ganksample.main.WealEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        WealService wealService = Api.getWealService();
        Call<WealEntity> call = wealService.getData(10, 1);
        call.enqueue(new Callback<WealEntity>() {
            @Override
            public void onResponse(Call<WealEntity> call, Response<WealEntity> response) {
                showData(response.body());
            }

            @Override
            public void onFailure(Call<WealEntity> call, Throwable t) {

            }
        });
    }

    private void showData(WealEntity body) {
        List<WealEntity.ResultsBean> results = body.getResults();
        mAdapter.setData(results);
    }
}
