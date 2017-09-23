package com.hc.ysg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hc.ysg.R;
import com.hc.ysg.activity.DetailsActivity;
import com.hc.ysg.adapter.GrideAdapter;
import com.hc.ysg.bean.ThemBean;
import com.hc.ysg.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2017/9/22.
 */

public class ThemeFragment extends Fragment {
    private String url="http://news-at.zhihu.com/api/4/themes";
    private List<ThemBean.OthersBean>list;
    private View view;
    private GridView gv;
    private GrideAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.themefragment,null);
        initView();
        loadData();
        return view;
    }

    private void initView() {
        gv = view.findViewById(R.id.gv);
        list=new ArrayList<>();

    }
    private void loadData() {

            //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
            OkHttpUtils.getInstance().getBeanOfOk(getActivity(), url, ThemBean.class,
                    new OkHttpUtils.CallBack<ThemBean>() {
                        @Override
                        public void getData(ThemBean testBean) {

                            Log.i("===", "getData: " + testBean.toString());
                            if (testBean != null) {

                                //如果不为空用本地list接收
                               list=testBean.others;
                                //数据一旦回调成功初始化数据源和适配器
                                GrideAdapter adapter = new GrideAdapter(list, getActivity());
                                gv.setAdapter(adapter);
                                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        startActivity(new Intent(getActivity(), DetailsActivity.class));
                                    }
                                });
                            }
                        }
                    });
        }
}
