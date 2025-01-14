package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.adapter.GioHangAdapter;
import com.example.manager.model.EventBus.TinhTongEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    TextView giohangtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;
    GioHangAdapter adapter;
    long tongtiensp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();
        initControl();
        Tinhtongtien();

    }

    private void Tinhtongtien() {
         tongtiensp=0;
        for (int i=0;i<Until.mangmuahnag.size();i++){
            tongtiensp= tongtiensp+(Until.mangmuahnag.get(i).getGiasp()*Until.mangmuahnag.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtiensp));
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
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (Until.manggiohang.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else {
            adapter=new GioHangAdapter(getApplicationContext(),Until.manggiohang);
            recyclerView.setAdapter(adapter);
        }
        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ThanhtoanActivity.class);
                intent.putExtra("tongtien",tongtiensp);
                Until.manggiohang.clear();
                startActivity(intent);
            }
        });
    }

    private void initView() {
        giohangtrong=findViewById(R.id.txtgiohangtrong);
        tongtien=findViewById(R.id.txttongtien);
        toolbar =findViewById(R.id.toobar);
        recyclerView=findViewById(R.id.recyclerviewgiohang);
        btnmuahang=findViewById(R.id.btnmuahang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void evntTinhTien(TinhTongEvent event){
        if (event!=null){
            Tinhtongtien();
        }
}
}