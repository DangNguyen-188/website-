package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.model.GioHang;
import com.example.manager.model.SanPhamMoi;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, giasp ,mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    SanPhamMoi sanPhamMoi;
    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themgiohang();
            }
        });
    }

    private void themgiohang() {
        boolean flag =false;
        if (Until.manggiohang.size()>0){
            int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i=0; i<Until.manggiohang.size();i++){
                if (Until.manggiohang.get(i).getIdsp()==sanPhamMoi.getId()){
                    Until.manggiohang.get(i).setSoluong(soluong + Until.manggiohang.get(i).getSoluong());
                    long gia= Long.parseLong(sanPhamMoi.getGiasp())*Until.manggiohang.get(i).getSoluong();
                    Until.manggiohang.get(i).setGiasp(gia);
                    flag=true;
                }
            }
            if (flag==false){
                long gia=Long.parseLong(sanPhamMoi.getGiasp())*soluong;
                GioHang gioHang=new GioHang();
                gioHang.setGiasp(gia);
                gioHang.setSoluong(soluong);
                gioHang.setIdsp(sanPhamMoi.getId());
                gioHang.setTensp(sanPhamMoi.getTensp());
                gioHang.setHinhsp(sanPhamMoi.getHinhanh());
                Until.manggiohang.add(gioHang);
            }
        }else {
            int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
            long gia=Long.parseLong(sanPhamMoi.getGiasp())*soluong;
            GioHang gioHang=new GioHang();
            gioHang.setGiasp(gia);
            gioHang.setSoluong(soluong);
            gioHang.setIdsp(sanPhamMoi.getId());
            gioHang.setTensp(sanPhamMoi.getTensp());
            gioHang.setHinhsp(sanPhamMoi.getHinhanh());
            Until.manggiohang.add(gioHang);

        }
        int totalItem=0;
        for (int i=0;i<Until.manggiohang.size();i++){
            totalItem= totalItem+Until.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
         sanPhamMoi= (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanPhamMoi.getTensp());
        mota.setText(sanPhamMoi.getMota());
        Glide.with(getApplicationContext()).load(sanPhamMoi.getHinhanh()).into(imghinhanh);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá: "+decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasp()))+"đ");
        Integer[] so =new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterspin);
    }

    private void initView() {
        tensp  =findViewById(R.id.txttensp);
        giasp=findViewById(R.id.txtgiasp);
        mota=findViewById(R.id.txtmotachitiet);
        btnthem=findViewById(R.id.btnthemvaogiohang);
        spinner=findViewById(R.id.spinner);
        imghinhanh =findViewById(R.id.imgchitiet);
        toolbar=findViewById(R.id.toobar);
        badge=findViewById(R.id.menu_sl);
        FrameLayout framlayoutgiohang=findViewById(R.id.framegiohang);
        framlayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang=new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(giohang);
            }
        });
        if (Until.manggiohang!=null){
            int totalItem=0;
            for (int i=0;i<Until.manggiohang.size();i++){
                totalItem= totalItem+Until.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Until.manggiohang!=null){
            int totalItem=0;
            for (int i=0;i<Until.manggiohang.size();i++){
                totalItem= totalItem+Until.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}