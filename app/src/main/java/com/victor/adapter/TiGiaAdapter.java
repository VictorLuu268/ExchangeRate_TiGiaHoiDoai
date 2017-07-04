package com.victor.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.model.TiGia;
import com.victor.tigiahoidoai.R;

import java.util.List;

/**
 * Created by Victor on 03/07/2017.
 */

public class TiGiaAdapter extends ArrayAdapter<TiGia> {

    Activity context;
    int resource;
    List<TiGia> objects;

    public TiGiaAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<TiGia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);

        TiGia tiGia = this.objects.get(position);
        ImageView imgHinh = (ImageView) item.findViewById(R.id.logo_ImgView);
        TextView typeTextV = (TextView) item.findViewById(R.id.type_TextView);
        TextView muaTMTextV = (TextView) item.findViewById(R.id.muaTM_TextView);
        TextView banTMTextV = (TextView) item.findViewById(R.id.banTM_TextView);
        TextView muaCKTextV = (TextView) item.findViewById(R.id.muaCK_TextView);
        TextView banCKTextV = (TextView) item.findViewById(R.id.banCK_TextView);

        imgHinh.setImageBitmap(tiGia.getBitmap());
        typeTextV.setText(tiGia.getType());
        muaTMTextV.setText(tiGia.getMuaTienMat());
        banTMTextV.setText(tiGia.getBanTienMat());
        muaCKTextV.setText(tiGia.getMuaChuyenKhoan());
        banCKTextV.setText(tiGia.getBanChuyenKhoan());

        return item;
    }
}
