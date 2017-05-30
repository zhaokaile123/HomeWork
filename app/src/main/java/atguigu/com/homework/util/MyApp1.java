package atguigu.com.homework.util;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;


/**
 * Created by ASUS on 2017/5/22.
 */

public class MyApp1 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils初始化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.


        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
       // 请勿在“ =”与 appid 之间添加任务空字符或者转义符
       // SpeechUtility.createUtility(this, SpeechConstant.APPID +"=59292af5");
}
  //要在布局文件中注册  在最上面

}
