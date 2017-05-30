package atguigu.com.homework.pager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.homework.R;
import atguigu.com.homework.adapter.MyAdapter;
import atguigu.com.homework.baseFragment.BaseFragment;
import atguigu.com.homework.dimain.YuleBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/5/30.
 */

public class YuleFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;

    private String NET_AUDIO_URL = "http://s.budejie.com/topic/list" +
            "/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid" +
            "=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting" +
            "=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

    private ArrayList<YuleBean.ListBean> datas;
    private MyAdapter myAdapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isLoadMore = false;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_yule, null);

        ButterKnife.inject(this, view);

        refrashData(view);






        return view;
    }

    //上拉或者下拉
    private void refrashData(View view) {

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        //设置上拉下拉监听
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override  //下拉刷新
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = false;
                getDataFromNet();
            }

            @Override //上拉加载更多
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);

                isLoadMore = true;
                getMoreData();

            }
        });


    }

    //上拉加载更多
    private void getMoreData() {
        RequestParams request = new RequestParams(NET_AUDIO_URL);
        x.http().get(request, new Callback.CommonCallback<String>() {

            @Override //联网成功的时候
            public void onSuccess(String result) {

                processData(result); //处理数据
                materialRefreshLayout.finishRefreshLoadMore(); // 关闭下拉加载更多
            }

            @Override//出错的时候
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override //  初始化数据
    public void initData() {
        super.initData();
        getDataFromNet(); // 联网请求数据
    }

    // 联网请求数据 的 方法
    private void getDataFromNet() {
        //根据url 去请求数据参数
        RequestParams reques = new RequestParams(NET_AUDIO_URL);

        Log.e("TAG","连网成功");
        //xutil
        x.http().post(reques, new Callback.CommonCallback<String>() {
            @Override //联网成功的时候
            public void onSuccess(String result) {
                materialRefreshLayout.finishRefresh();
                processData(result);//解析数据的
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    // 解析数据  返回的是一个  对象集合
    private void processData(String json) {
        YuleBean netAudioBean = new Gson().fromJson(json, YuleBean.class);

        if (!isLoadMore) {
            datas = (ArrayList<YuleBean.ListBean>) netAudioBean.getList();
            if (datas != null && datas.size() > 0) {
                //有 数据
                tvNomedia.setVisibility(View.GONE);
                //设置适配器
                myAdapter = new MyAdapter(context, datas);
                Log.e("TAG","datas = " + datas);
                recyclerview.setAdapter(myAdapter);

                //布局管理器的设置  一定要添加
                recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            } else {
                //没有数据
                tvNomedia.setVisibility(View.VISIBLE);
            }
            progressbar.setVisibility(View.GONE);

        }else {  //上加载更多
            List<YuleBean.ListBean> newMovieList = netAudioBean.getList();//这里new了一个新的集合，用来存新数据
            for (int i = 0; i < newMovieList.size(); i++) {

                YuleBean data = new YuleBean();

            }
        }
    }

    public int getCount() {
        return datas.size();
    }

}
