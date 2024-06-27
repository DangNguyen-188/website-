package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhtoanActivity extends AppCompatActivity {
            Toolbar toolbar;
            TextView txttongtien, txtsodt,txtemail;
            EditText edtdiachi;
            AppCompatButton btndathang;
            CompositeDisposable compositeDisposable=new CompositeDisposable();
            ApiBanHang apiBanHang;
            long tongtien;
            int totalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan2);
        initView();
        countItem();
        initControl();
    }

    private void countItem() {
         totalItem=0;
        for (int i=0;i<Until.mangmuahnag.size(); i++){
            totalItem= totalItem+Until.mangmuahnag.get(i).getSoluong();
        }
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien = getIntent().getLongExtra("tongtien",0);
        txttongtien.setText( decimalFormat.format(tongtien));
        txtemail.setText(Until.user_current.getEmail());
        txtsodt.setText(Until.user_current.getMobile());

        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi=edtdiachi.getText().toString().trim();
                if (TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập địa chỉ giao hàng",Toast.LENGTH_SHORT).show();
                }else {
                    String str_email=Until.user_current.getEmail();
                    String str_sdt=Until.user_current.getMobile();
                    int id=Until.user_current.getId();
                    Log.d("test", new Gson().toJson(Until.mangmuahnag));
                    compositeDisposable.add(apiBanHang.createOder(str_email,str_sdt,String.valueOf(tongtien),id,str_diachi,totalItem , new Gson().toJson(Until.mangmuahnag))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_SHORT).show();
                                Until.mangmuahnag.clear();
                                Intent intent=new  Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                    ));
                }
            }
        });
    }

    private void initView() {
        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        toolbar=findViewById(R.id.toobar);
        txttongtien=findViewById(R.id.txttongtien);
        txtemail=findViewById(R.id.txtemail);
        txtsodt=findViewById(R.id.txtsodienthoai);
        edtdiachi=findViewById(R.id.edtdiachi);
        btndathang=findViewById(R.id.btndathang);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}