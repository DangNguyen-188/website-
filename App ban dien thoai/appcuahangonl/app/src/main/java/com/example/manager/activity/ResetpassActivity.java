package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResetpassActivity extends AppCompatActivity {
    EditText email;
    AppCompatButton btnreset;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        initView();
        initControll();
    }

    private void initControll() {

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email=email.getText().toString().trim();
                if (TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(),"Ban chua nhap mail",Toast.LENGTH_LONG).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    compositeDisposable.add(apiBanHang.resetPass(str_email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                       userModel -> {
                            if (userModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), userModel.getMessage(),Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),DangnhapActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),userModel.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                       },
                       throwable -> {
                           Toast.makeText(getApplicationContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.INVISIBLE);
                       }

                    ));
                }
            }
        });
    }

    private void initView() {
        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        email=findViewById(R.id.edtresetpass);
        btnreset=findViewById(R.id.btnresetpass);
        progressBar=findViewById(R.id.progressbar);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}