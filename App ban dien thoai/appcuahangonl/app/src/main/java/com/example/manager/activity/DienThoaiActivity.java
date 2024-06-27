package com.example.manager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.manager.R;
import com.example.manager.Until.Until;
import com.example.manager.adapter.DienThoaiAdapter;
import com.example.manager.model.SanPhamMoi;
import com.example.manager.retrofit.ApiBanHang;
import com.example.manager.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DienThoaiActivity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    int page=1;
    int loai;
    DienThoaiAdapter adapterDT;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler=new Handler();
    boolean isLoading =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        apiBanHang = RetrofitClient.getInstance(Until.BASE_URL).create(ApiBanHang.class);
        loai =getIntent().getIntExtra("loai",1);
        AnhXa();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading==false){
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition()==sanPhamMoiList.size()-1){
                        isLoading=true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapterDT.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapterDT.notifyItemRemoved(sanPhamMoiList.size());
                page=page+1;
                getData(page);
                adapterDT.notifyDataSetChanged();
                isLoading=false;
            }
        },2000);
    }

    private void getData(int page ) {
        compositeDisposable.add(apiBanHang.getSanPham(page, loai)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
           sanPhamMoiModel -> {
               if (sanPhamMoiModel.isSuccess()){
                   if (adapterDT==null) {
                       sanPhamMoiList = sanPhamMoiModel.getResult();
                       adapterDT = new DienThoaiAdapter(getApplicationContext(), sanPhamMoiList);
                       recyclerView.setAdapter(adapterDT);
                   }else {
                       int vitri=sanPhamMoiList.size()-1;
                       int soluongadd=sanPhamMoiModel.getResult().size();
                       for (int i=0;i<soluongadd;i++){
                           sanPhamMoiList.add(sanPhamMoiModel.getResult().get(i));
                       }
                       adapterDT.notifyItemRangeInserted(vitri,soluongadd);
                   }
               }else{
                   Toast.makeText(getApplicationContext(),"Hết",Toast.LENGTH_LONG).show();
                   isLoading=true;

               }
           },
                throwable -> {
                    Toast.makeText(getApplicationContext(),"khon ket noi sever",Toast.LENGTH_LONG).show();
                }
        ));
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

    private void AnhXa() {
        toolbar =findViewById(R.id.toobar);
        recyclerView=findViewById(R.id.recyclerview_dt);
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList=new ArrayList<>();
    }
    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
}