package atguigu.com.homework;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import atguigu.com.homework.baseFragment.BaseFragment;
import atguigu.com.homework.pager.LoaclVideoFragment;
import atguigu.com.homework.pager.LocalAudio;
import atguigu.com.homework.pager.NetVideoFragment;
import atguigu.com.homework.pager.YuleFragment;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments;
    private Fragment tempFragment;
    private RadioGroup rg_main;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        initFragment(); //将所有的fragment添加到集合里

        //Rediogroup 的点击事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.local_video:
                        position = 0;
                        break;
                    case R.id.local_music:
                        position = 1;
                        break;
                    case R.id.net_music:
                        position = 2;
                        break;
                    case R.id.net_video:
                        position = 3;
                        break;
                }

                BaseFragment currentFragment = (BaseFragment) fragments.get(position);

                showFragment(currentFragment); //将当前的fragment 显示数来

            }
        });

        rg_main.check(R.id.local_video);  //让他默认选择一个
    }

    //提交事务  将点击的当前Fragment 添加到  FragmentLayout上
    private void showFragment(BaseFragment currentFragment) {
        if( tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!currentFragment.isAdded()) {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_content,currentFragment).commit();
            }else{
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment).commit();
            }
            tempFragment = currentFragment;
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new LoaclVideoFragment());
        fragments.add(new LocalAudio());
        fragments.add(new YuleFragment());
        fragments.add(new NetVideoFragment());

    }

    private boolean isExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == MotionEvent.BUTTON_BACK) {
            if(position != 0) {
                rg_main.check(R.id.local_video);
            }else if(isExit) {
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                isExit = true;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                },2000);

                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
