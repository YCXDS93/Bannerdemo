package com.hc.ysg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hc.ysg.R;
import com.hc.ysg.bean.Bean;

import java.util.List;

/**
 * Created by YU on 2017/9/22.
 */

public class ReCylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bean.TopStoriesBean> list;
    private Context context;

    public ReCylerAdapter(List<Bean.TopStoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        view = LayoutInflater.from(context).inflate(R.layout.daily_item, null);
        holder = new MyViewHoder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder myViewHoder = (MyViewHoder) holder;
        myViewHoder.title.setText(list.get(position).title);
        Glide.with(context).load(list.get(position).image).into(myViewHoder.imageView);
//        ImageLoaderUtils.setImageView(list.get(position).image,context,((MyViewHoder) holder).imageView);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public MyViewHoder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
        }
    }
}
