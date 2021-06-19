package com.shawpoo.app.https;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Shawpoo: 2017/6/2
 * @Description:
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        readHttpsCer();
    }

    // 获取证书流
    private void readHttpsCer() {
        try {
            InputStream is = getAssets().open("cers/srca.cer");
            NetConfig.addCertificate(is); // 这里将证书读取出来，，放在配置中byte[]里
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
