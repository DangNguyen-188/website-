package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;


import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.adapter.DonHangAdapter;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemDonActivity extends AppCompatActivity {
        CompositeDisposable compositeDisposable=new CompositeDisposable();
        ApiBanHang apiBanHang;
        RecyclerView redonhang;
        Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don);
        initView();
        initToolbar();
        getOder();

        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);

    }

    private void getOder() {
        compositeDisposable.add(apiBanHang.xemDonHang(Until.user_current.getId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    donHangModel -> {
                        DonHangAdapter adapter=new DonHangAdapter(getApplicationContext(),donHangModel.getResult());
                        redonhang.setAdapter(adapter);
                    },
                    throwable -> {

                    }
            ));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        apiBanHang=RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        toolbar=findViewById(R.id.toobar);
        redonhang=findViewById(R.id.recyclerview_donhang);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        redonhang.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}