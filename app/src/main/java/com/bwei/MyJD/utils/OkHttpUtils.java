package com.bwei.MyJD.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    //私有方法
    private OkHttpUtils(){
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient();
        }
    }
    //单例
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            //同步
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    public void PostData(String path, HashMap<String,String> hashMap, final Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        if(hashMap != null && hashMap.size()>0){
            for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
                builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }
        }
        final Request request = new Request.Builder()
                .url(path)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(callback != null){
                    callback.onResponse(call,response);
                }
            }
        });

    }
    public void getData(String path, Callback callback){
        Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }
}
