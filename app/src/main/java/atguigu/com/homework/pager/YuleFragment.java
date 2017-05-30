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


