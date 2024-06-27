package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangkyActivity extends AppCompatActivity {
    EditText email, pass, repass, username,mobile;
    AppCompatButton button;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        initView();
        initcControl();
    }

    private void initcControl() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangkky();
            }
        });
    }

    private void dangkky() {
        String str_email=email.getText().toString().trim();
        String str_username=username.getText().toString().trim();
        String str_pass=pass.getText().toString().trim();
        String str_repass=repass.getText().toString().trim();
        String str_mobile=mobile.getText().toString().trim();
        if (TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập Email",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(str_username)){
              Toast.makeText(getApplicationContext(),"Bạn chưa nhập Username",Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập Pass",Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(str_repass)){
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập Repass",Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(str_mobile)) {
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập Mobile",Toast.LENGTH_LONG).show();
        }else {
            if (str_pass.equals(str_repass)){
                compositeDisposable.add(apiBanHang.dangki(str_email,str_pass,str_username,str_mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                Until.user_current.setEmail(str_email);
                                Until.user_current.setPass(str_pass);
                                Intent intent=new Intent(getApplicationContext(), DangnhapActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),userModel.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
            }else {
                Toast.makeText(getApplicationContext(),"pass Không khớp",Toast.LENGTH_LONG).show();

            }
        }

    }

    private void initView() {
        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        repass=findViewById(R.id.repass);
        mobile=findViewById(R.id.mobile);
        username=findViewById(R.id.username);
        button = findViewById(R.id.btndangky);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}