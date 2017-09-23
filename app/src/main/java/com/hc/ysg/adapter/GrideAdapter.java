package com.hc.ysg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hc.ysg.R;
import com.hc.ysg.bean.ThemBean;

import java.util.List;

/**
 * Created by YU on 2017/9/22.
 */

public class GrideAdapter extends BaseAdapter {
        private List<ThemBean.OthersBean> list;
            private Context context;

            public GrideAdapter(List<ThemBean.OthersBean> list, Context context) {
                this.list = list;
                this.context = context;
            }

            @Override
            public int getCount() {
                return list.size();
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
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView==null){
                    holder=new ViewHolder();
                    convertView=View.inflate(context, R.layout.them_item,null);
                    holder.themName=convertView.findViewById(R.id.themname);
                    holder.themImage=convertView.findViewById(R.id.themimage);
                     convertView.setTag(holder);
                }else{
                    holder= (ViewHolder) convertView.getTag();
                }
                holder.themName.setText(list.get(position).name);
//                ImageLoaderUtils.setImageView(list.get(position).thumbnail,context,holder.themImage);
                Glide.with(context).load(list.get(position).thumbnail).into(holder.themImage);
                return convertView;
            }
            class  ViewHolder{
                ImageView themImage;
                TextView themName;
            }


}
