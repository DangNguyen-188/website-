package com.example.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manager.R;
import com.example.manager.model.Item;

import java.util.List;

public class ChitietAdapter extends RecyclerView.Adapter<ChitietAdapter.MyViewHodel> {
    Context context;
    List<Item> itemList;

    public ChitietAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public ChitietAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet,parent,false);
        return new MyViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodel holder, int position) {
        Item item=itemList.get(position);
        holder.txtten.setText(item.getTensp()+"");
        holder.txtsoluong.setText("Số lượng:"+item.getSoluong()+" ");
        Glide.with(context).load(item.getHinhanh()).into(holder.imagechitet);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHodel extends RecyclerView.ViewHolder{
        ImageView imagechitet;
        TextView txtten,txtsoluong;
        public MyViewHodel(@NonNull View itemView) {
            super(itemView);
            imagechitet=itemView.findViewById(R.id.item_imgchitiet);
            txtten=itemView.findViewById(R.id.item_tenspchitiet);
            txtsoluong=itemView.findViewById(R.id.item_soluongchitiet);
        }
    }
}
