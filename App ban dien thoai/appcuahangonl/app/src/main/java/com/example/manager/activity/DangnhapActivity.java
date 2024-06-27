package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangnhapActivity extends AppCompatActivity {
    TextView txtdangky,txtresetpass;
    EditText email,pass;
    AppCompatButton btndangnhap;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        initView();
        initControl();
    }

    private void initControl() {
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DangkyActivity.class);
                startActivity(intent);
            }
        });
        txtresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResetpassActivity.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email=email.getText().toString().trim();
                String str_pass=pass.getText().toString().trim();
                if (TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập Email",Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập Pass",Toast.LENGTH_LONG).show();
                }else {
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);
                    dangnhap(str_email,str_pass);

                }
            }
        });
    }

    private void initView() {
        Paper.init(this);
        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        txtdangky=findViewById(R.id.txtdangky);
        txtresetpass=findViewById(R.id.txtresetpass);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        btndangnhap=findViewById(R.id.btndangnhap);
        if (Paper.book().read("email")!=null&& Paper.book().read("pass")!=null){
                email.setText(Paper.book().read("email"));
                pass.setText(Paper.book().read("pass"));
                if (Paper.book().read("isLogin")!=null){
                    boolean flag =Paper.book().read("isLogin");
                    if (flag){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //dangnhap(Paper.book().read("email"),Paper.book().read("pass"));
                            }
                        },1000);
                    }
                }
        }

    }

    private void dangnhap(String email,String pass) {
        compositeDisposable.add(apiBanHang.dangnhap(email,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                isLogin=true;
                                Paper.book().write("isLogin", isLogin);

                                Until.user_current= userModel.getResult().get(0);
                                Paper.book().write("user", userModel.getResult().get(0));
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Until.user_current.getEmail() != null && Until.user_current.getPass() != null){
            email.setText(Until.user_current.getEmail());
            pass.setText(Until.user_current.getPass());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}