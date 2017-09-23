package com.hc.ysg.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hc.ysg.R;
import com.hc.ysg.fragment.DailyFragment;
import com.hc.ysg.fragment.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2017/9/22、
 * 姓名：于帅光
 * 用途：页面
 */
public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList=new ArrayList<>();
    private ViewPager vp;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化
    }

    private void initView() {
        vp = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        //将fragment装进列表中

        fragmentList.add(new DailyFragment());
        fragmentList.add(new DailyFragment());
        fragmentList.add(new DailyFragment());
        fragmentList.add(new ThemeFragment());

        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText("最新日报"));
        tabLayout.addTab(tabLayout.newTab().setText("专门"));
        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("主题日报"));
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);


    }
    class MyAdapter extends FragmentPagerAdapter{
        private String[] titles = new String[]{"最新日报", "专门", "热门", "主题日报"};
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  titles[position];
        }
    }
}
