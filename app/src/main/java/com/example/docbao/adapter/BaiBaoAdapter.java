package com.example.docbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.docbao.R;
import com.example.docbao.model.BaiBao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaiBaoAdapter extends ArrayAdapter<BaiBao> {
    private Context context;
    private int idLayout;
    private ArrayList<BaiBao> list;
    private ViewHolder holder;

    public BaiBaoAdapter(Context context, int idLayout, ArrayList<BaiBao> list) {
        super(context, idLayout, list);
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(idLayout, parent, false);
            holder = new ViewHolder();

            holder.tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
            holder.tvNgayDang = convertView.findViewById(R.id.tvNgayDang);
            holder.ivAnh = convertView.findViewById(R.id.ivAnh);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        BaiBao thongBao = list.get(position);
        holder.tvTieuDe.setText(thongBao.getTieuDe());
        holder.tvNgayDang.setText(thongBao.getNgayDang());
        // Sử dụng Picasso hoặc Glide để hiển thị ảnh từ URL
        Picasso.get().load(thongBao.getImage()).into(holder.ivAnh);

        return convertView;
    }

    private class ViewHolder {
        TextView tvTieuDe;
        TextView tvNgayDang;
        ImageView ivAnh;
    }
}
