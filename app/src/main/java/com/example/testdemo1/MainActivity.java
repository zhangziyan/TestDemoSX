package com.example.testdemo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testdemo1.presenter.TomactPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt1;
    private TomactPresenter tomactPresenter;
    private String url = "http://192.168.40.1:8080/ims/123.png";
    private ImageView iv1;
    private Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 0:
                        byte[] result = (byte[]) msg.obj;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(result,0,result.length);
                        iv1.setImageBitmap(bitmap);
                    break;
                }
            }
        };
        initView();
        initData();
    }

    private void initData() {
        tomactPresenter = new TomactPresenter();
    }

    private void initView() {
        bt1 = (Button) findViewById(R.id.bt1);
        iv1 = (ImageView)findViewById(R.id.iv1);
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                tomactPresenter.sendHttpData(url,myHandler);
                break;
        }
    }
}