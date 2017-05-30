package atguigu.com.homework.pager;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.homework.R;
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

    private ArrayList<YuleBean.ListBean> datas;
    private boolean isLoadMore = false;


    private String NET_AUDIO_URL = "http://s.budejie.com/topic/list" +
            "/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid" +
            "=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting" +
            "=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_yule, null);
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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
            datas = netAudioBean.getList();
            if (datas != null && datas.size() > 0) {
                //有 数据
                tvNomedia.setVisibility(View.GONE);
                //设置适配器
                myAdapter = new NetAudioAdapter(context, datas);
                listview.setAdapter(myAdapter);
            } else {
                //没有数据
                tvNomedia.setVisibility(View.VISIBLE);
            }

            progressbar.setVisibility(View.GONE);

        }else {  //上加载更多
            List<NetAudioBean.ListBean> newMovieList = netAudioBean.getList();//这里new了一个新的集合，用来存新数据
            for (int i = 0; i < newMovieList.size(); i++) {

                NetAudioBean data = new NetAudioBean();

            }

        }

    }

    public int getCount() {
        return datas.size();
    }

}
