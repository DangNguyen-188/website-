package com.example.manager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.adapter.LoaiSPAdapter;
import com.example.manager.adapter.SanPhamMoiAdapter;
import com.example.manager.model.SanPhamMoi;
import com.example.manager.model.User;
import com.example.manager.model.loaiSP;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewtrangchu;
    NavigationView navigationView;
    ListView listViewtranhchuchu;
    DrawerLayout drawerLayout;
    LoaiSPAdapter loaiSPAdapter;
    List<loaiSP> mangloaisp;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang= RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        Paper.init(this);
        if (Paper.book().read("user")!=null){
            User user=Paper.book().read("user");
            Until.user_current=user;
        }
        anhxa();
        ActionBar();
        if(isConnected(this)){
            ActionviewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else{
            Toast.makeText(getApplicationContext(),"không có internet, vui lòng kết nối lại",Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
        listViewtranhchuchu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                       Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(), DienThoaiActivity.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(), DienThoaiActivity.class);
                        laptop.putExtra("loai",2);
                        startActivity(laptop);
                        break;
                    case 5:
                        Intent donhang = new Intent(getApplicationContext(),XemDonActivity.class);
                        startActivity(donhang);
                        break;
                    case 6:
                        Intent quanli = new Intent(getApplicationContext(),QuanLiActivity.class);
                        startActivity(quanli);
                        finish();
                    break;
                    case 7:
                        Paper.book().delete("user");
                        Intent dangnhap = new Intent(getApplicationContext(),DangnhapActivity.class);
                        startActivity(dangnhap);
                        finish();
                        break;
                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                sanPhamMoiModel -> {
                    if (sanPhamMoiModel.isSuccess()){
                        mangSpMoi =sanPhamMoiModel.getResult();
                        spAdapter = new SanPhamMoiAdapter(getApplicationContext(),mangSpMoi);
                        recyclerViewtrangchu.setAdapter(spAdapter);
                    }
                },
                throwable -> {
                    Toast.makeText(getApplicationContext(),"không ket nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                }
        ));
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getloaiSP()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                loaiSPModel -> {
                    if (loaiSPModel.isSuccess()){
                        mangloaisp = loaiSPModel.getResult();
                        mangloaisp.add(new loaiSP("Quản lí","https://banner2.cleanpng.com/20191116/rqt/transparent-login-icon-logout-icon-5dcfef94516950.3632820915739083723335.jpg"));
                        mangloaisp.add(new loaiSP("Đăng xuất","https://banner2.cleanpng.com/20191116/rqt/transparent-login-icon-logout-icon-5dcfef94516950.3632820915739083723335.jpg"));
                        loaiSPAdapter = new LoaiSPAdapter(getApplicationContext(),mangloaisp);
                        listViewtranhchuchu.setAdapter(loaiSPAdapter);
                    }
                }
        ));
    }

    private void ActionviewFlipper() {
        List<String> mangquangcao =new ArrayList<>();
        mangquangcao.add("https://cdn.pico.vn/Files/2020/08/04/15959_man-hinh-laptop-pico.jpg");
        mangquangcao.add("https://images.fpt.shop/unsafe/filters:quality(90)/fptshop.com.vn/uploads/images/tin-tuc/136826/Originals/Iphone-13-co-may-mau-tong-hop-mau-sac-ro-ri-hap-dan-cua-iphone-13-k.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2018/07/13/1101218/laptop-dell-cua-nuoc-nao-co-tot-khong-.png");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView=new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        Animation slide_out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setInAnimation(slide_out);
    }

    private void ActionBar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
    toolbar.setNavigationOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick( View view){
            drawerLayout.openDrawer(GravityCompat.START);

        }
    });
    }

    private void anhxa() {
    imgsearch=findViewById(R.id.imgsearch);
    toolbar=findViewById(R.id.toolbartrangchu);
    viewFlipper=findViewById(R.id.viewlipper);
    recyclerViewtrangchu=findViewById(R.id.recyclerview);
    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
    recyclerViewtrangchu.setLayoutManager(layoutManager);
    recyclerViewtrangchu.setHasFixedSize(true);
    listViewtranhchuchu=findViewById(R.id.listviewtrangchu);
    navigationView=findViewById(R.id.navigation);
    drawerLayout=findViewById(R.id.drawerlayout);
    badge=findViewById(R.id.menu_sl);
    frameLayout=findViewById(R.id.framegiohang);
    //khoi tao list
    mangloaisp=new ArrayList<>();
    mangSpMoi =new ArrayList<>();
    if (Until.manggiohang==null){
        Until.manggiohang=new ArrayList<>();

    }else {
        int totalItem=0;
        for (int i=0;i<Until.manggiohang.size();i++){
            totalItem= totalItem+Until.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }
    frameLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent giohang=new  Intent(getApplicationContext(),GioHangActivity.class);
            startActivity(giohang);
        }
    });
    imgsearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(),SearchActivit.class);
            startActivity(intent);
        }
    });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem=0;
        for (int i=0;i<Until.manggiohang.size();i++){
            totalItem= totalItem+Until.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected())||(mobile != null && mobile.isConnected())){
        return true;
        }else{
            return false;

        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}