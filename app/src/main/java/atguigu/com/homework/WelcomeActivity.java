package atguigu.com.homework;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.sendEmptyMessageDelayed(0, 2000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));

        return super.onTouchEvent(event);
    }
}