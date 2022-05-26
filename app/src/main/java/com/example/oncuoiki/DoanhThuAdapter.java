package com.example.oncuoiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DoanhThuAdapter extends BaseAdapter {
    Context context;
    int idLayout;
    ArrayList<DoanhThu> arrayList;
    private IClickLisner clickLisner;
    public interface IClickLisner{
        void onClickUpdateIteam(DoanhThu dt);
        void onClickDeleteIteam(DoanhThu dt);

    }

    public DoanhThuAdapter(Context context, int idLayout, ArrayList<DoanhThu> arrayList, IClickLisner clickLisner) {
        this.context = context;
        this.idLayout = idLayout;
        this.arrayList = arrayList;
        this.clickLisner = clickLisner;
    }

    public DoanhThuAdapter(Context context, int idLayout, ArrayList<DoanhThu> arrayList) {
        this.context = context;
        this.idLayout = idLayout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(idLayout,parent,false);
        DoanhThu dt =arrayList.get(i);
        TextView tienkc = convertView.findViewById(R.id.tv_TienKC);
        TextView tienkt = convertView.findViewById(R.id.tv_TienKT);
        Button btnUpdate = convertView.findViewById(R.id.btn_update);
        Button btnDelete = convertView.findViewById(R.id.btn_delete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLisner.onClickUpdateIteam(dt);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLisner.onClickDeleteIteam(dt);
            }
        });

        tienkc.setText(""+arrayList.get(i).getTienkc()+"đ");
        tienkt.setText(""+arrayList.get(i).getTienkt()+"đ");
        return convertView;
    }
}
