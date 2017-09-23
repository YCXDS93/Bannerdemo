package com.hc.ysg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hc.ysg.R;
import com.hc.ysg.adapter.ReCylerAdapter;
import com.hc.ysg.bean.Bean;
import com.hc.ysg.utils.BannerImage;
import com.hc.ysg.utils.OkHttpUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2017/9/22.
 */

public class DailyFragment extends Fragment {
    private List<Bean.TopStoriesBean>list;
    private List<Bean.TopStoriesBean>blist;
    private String url="http://news-at.zhihu.com/api/4/news/latest";
    private View view;
    private RecyclerView mRecyler;
    private Banner banner;
    private SwipeRefreshLayout swipe;
    private ReCylerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dailyfragment,null);
        initView();
        loadData();
        loadData1();
        return view;
    }
    //初始化数据
    private void initView() {
        list=new ArrayList<>();
        blist=new ArrayList<>();
        mRecyler = (RecyclerView)view.findViewById(R.id.recycler);
        banner = (Banner) view.findViewById(R.id.banner);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        mRecyler.setLayoutManager(linearLayoutManager);
        banner.setImageLoader(new BannerImage());
//        banner.start();
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        });
        mRecyler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition==list.size()-1){
                    loadData();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void loadData1() {

            //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
            OkHttpUtils.getInstance().getBeanOfOk(getActivity(), url, Bean.class,
                    new OkHttpUtils.CallBack<Bean>() {
                        @Override
                        public void getData(Bean testBean) {

                            Log.i("===", "getData: " + testBean.toString());
                            if (testBean != null) {
                                blist=testBean.top_stories;
                               List<String>imag=new ArrayList<String>();

                                for (Bean.TopStoriesBean bean:blist){
                                    imag.add(bean.image);
                                }
                                banner.setImages(imag);

                                banner.start();
                                initAdapter();
                            }
                        }
                    });
        }
    private void loadData() {

            //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
            OkHttpUtils.getInstance().getBeanOfOk(getActivity(), url, Bean.class,
                    new OkHttpUtils.CallBack<Bean>() {
                        @Override
                        public void getData(Bean testBean) {

                            Log.i("===", "getData: " + testBean.toString());
                            if (testBean != null) {
                                //如果不为空用本地list接收
                                list=testBean.top_stories;
                                List<String>image=new ArrayList<String>();
                                for (Bean.TopStoriesBean bean:blist){
                                    image.add(bean.image);
                                }

                                //数据一旦回调成功初始化数据源和适配器
                                initAdapter();
                            }
                        }
                    });
        }
        public void  initAdapter(){
            adapter = new ReCylerAdapter(list,getActivity());
            mRecyler.setAdapter(adapter);
        }
}
