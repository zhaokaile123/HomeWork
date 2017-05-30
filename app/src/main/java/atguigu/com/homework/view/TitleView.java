package atguigu.com.homework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.homework.R;

import static atguigu.com.homework.R.id.game;

/**
 * Created by ASUS on 2017/5/30.
 */

public class TitleView extends LinearLayout implements View.OnClickListener {

    private Context context;
   private TextView tv_sousuokuang;
    private RelativeLayout rl_game;
    private ImageView jilu;
    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override  // 加载完了布局调用
    protected void onFinishInflate() {
        super.onFinishInflate();

        tv_sousuokuang = (TextView) getChildAt(1);
        rl_game = (RelativeLayout) getChildAt(2);
        jilu = (ImageView) getChildAt(3);

        tv_sousuokuang.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        jilu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.tv_sousuokuang:
                Toast.makeText(context, "搜索框", Toast.LENGTH_SHORT).show();
                break;
            case  game:
                Toast.makeText(context, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.jilu:
                Toast.makeText(context, "记录", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
