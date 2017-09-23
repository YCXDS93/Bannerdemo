package com.hc.ysg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hc.ysg.R;
import com.hc.ysg.bean.ThemBean;

import java.util.List;

/**
 * 时间：2017/9/22、
 * 姓名：于帅光
 * 用途：详情的适配器
 */
public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ONE=0;
    public static final int TYPE_TWO=1;


    private List<ThemBean.OthersBean> list;
    private Context context;

    public DetailsAdapter(List<ThemBean.OthersBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;

        if (viewType==TYPE_ONE){
            view=View.inflate(context,R.layout.details_item1,null);

            return new MyViewHolder1(view);
        }else {
            view=View.inflate(context,R.layout.details_item2,null);
            return new MyViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type=getItemViewType(position);
        if (type==TYPE_ONE){
            MyViewHolder1 myViewHolder1= (MyViewHolder1) holder;
            myViewHolder1.detal_tile.setText(list.get(position).name);
            Glide.with(context).load(list.get(position).thumbnail).into(((MyViewHolder1) holder).details_image);
        }else {
            MyViewHolder2 myViewHolder2= (MyViewHolder2) holder;
            myViewHolder2.detal_tile.setText(list.get(position).name);
//            ImageLoaderUtils.setImageView(list.get(position).thumbnail,context,myViewHolder2.details_image);
        }

    }

    @Override
    public int getItemViewType(int position) {
       // ThemBean.OthersBean data=list.get(position);
        if (position%2 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView details_image;
        TextView detal_tile;

        public MyViewHolder1(View itemView) {
            super(itemView);
            details_image = itemView.findViewById(R.id.details_image);
            detal_tile = itemView.findViewById(R.id.detal_tile);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView details_image;
        TextView detal_tile;

        public MyViewHolder2(View itemView) {
            super(itemView);
//            details_image = itemView.findViewById(R.id.details_image);
            detal_tile = itemView.findViewById(R.id.detal_tile);
        }
    }

}

