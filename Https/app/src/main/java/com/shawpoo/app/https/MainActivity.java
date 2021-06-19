package com.shawpoo.app.https;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Button mButton;
    private TextView mTextView;
    private String mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_get);
        mTextView = (TextView) findViewById(R.id.text);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAsynHttp();
            }
        });
    }

    private void getAsynHttp() {
        OkHttpClient mOkHttpClient = HttpsUtil.createOkHttp(MainActivity.this);
        Request.Builder requestBuilder = new Request.Builder().url("https://www.cloudservicesystem.com");

        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    mData = "Cache: " + response.cacheResponse().toString();
                } else {
                    response.body().string();
                    mData = "Net: " + response.networkResponse().toString();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(mData);
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
