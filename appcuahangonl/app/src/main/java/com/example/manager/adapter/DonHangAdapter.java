package com.example.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manager.R;
import com.example.manager.model.DonHang;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHodel> {
    private RecyclerView.RecycledViewPool viewPool=new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listdonhang;

    public DonHangAdapter(Context context, List<DonHang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);

        return new  MyViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodel holder, int position) {
        DonHang donHang=listdonhang.get(position);
        holder.txtdonhang.setText( "Đơn Hàng:"+donHang.getId());
        LinearLayoutManager layoutManager=new LinearLayoutManager(
                holder.reChitiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        layoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
        ChitietAdapter chitietAdapter=new ChitietAdapter(context,donHang.getItem());
        holder.reChitiet.setLayoutManager(layoutManager);
        holder.reChitiet.setAdapter(chitietAdapter);
        holder.reChitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }

    public class MyViewHodel extends RecyclerView.ViewHolder {
        TextView txtdonhang;
        RecyclerView reChitiet;
        public MyViewHodel(@NonNull View itemView) {
            super(itemView);
            txtdonhang=itemView.findViewById(R.id.iddonhang);
            reChitiet=itemView.findViewById(R.id.recyclerview_chitiet);
        }
    }
}
