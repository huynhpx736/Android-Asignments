package com.example.baitapth;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CustomAdapterUser extends ArrayAdapter {
    Context context;
    int resource;
    List<User> data;
    public CustomAdapterUser(@NonNull Context context, int resource, @NonNull List<User> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null); // nap giao dien
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvLogin = convertView.findViewById(R.id.tvLogin);
        TextView tvUrl = convertView.findViewById(R.id.tvURL);
        User user = data.get(position);
        tvLogin.setText(user.getLogin());
        tvUrl.setText(user.getUrl());
        Glide.with(convertView).load(user.getAvatar_url()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(50, 5))) .into(ivHinh);
        return convertView;
    }
}
