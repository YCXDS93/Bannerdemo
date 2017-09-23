package com.hc.ysg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hc.ysg.R;
import com.hc.ysg.adapter.DetailsAdapter;
import com.hc.ysg.bean.ThemBean;
import com.hc.ysg.utils.LoggingInterceptor;
import com.hc.ysg.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {
    private List<ThemBean.OthersBean>list;
    private String url="http://news-at.zhihu.com/api/4/themes";
    private DetailsAdapter detailsAdapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        loadData();
    }

    private void initView() {
        list=new ArrayList<>();
        recycler=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

    }
   private void loadData() {

           //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
           OkHttpUtils.getInstance().getBeanOfOk(this, url, ThemBean.class,
                   new OkHttpUtils.CallBack<ThemBean>() {
                       @Override
                       public void getData(ThemBean testBean) {

                           Log.i("===", "getData: " + testBean.toString());
                           if (testBean != null) {

                               //如果不为空用本地list接收
                             list=testBean.others;

                               //数据一旦回调成功初始化数据源和适配器
                               DetailsAdapter adapter=new DetailsAdapter(list,DetailsActivity.this);
                               recycler.setAdapter(adapter);
                           }
                       }
                   });
       }
    public void interceptor(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //在建立OkHttp对象时，传入拦截器对象
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
//                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor((Interceptor) new CacheInterecepor()).build();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    Log.d("ysg", str);
//                    textView.setText(str);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
