package com.viatusk.umesh.handzapassignment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.webkit.JavascriptInterface;

import com.viatusk.umesh.handzapassignment.activities.Home;
import com.viatusk.umesh.handzapassignment.activities.SecAct;

import java.io.IOException;

/**
 * Created by UMESH-ADMIN on 30-11-2017.
 */

public class MYJS {
    Handler h;
    private Context con;
    public MYJS(Context cont) {
        con=cont;
        h=new Handler();
    }

    @JavascriptInterface
    public void title(String title){
        if(con instanceof Activity)
            ((Activity) con).setTitle(title);
    }

    @JavascriptInterface
    public void evalOnHome(final String script){
        h.post(new Runnable() {
            @Override
            public void run() {
                Home.wv.evaluateJavascript(script, null);
            }
        });
    }

    @JavascriptInterface
    public void postCategory(){
        SecAct.homeCallbackForWV=this;
        Intent ii=new Intent(con,SecAct.class);
        ii.putExtra("act","postCategory");
        con.startActivity(ii);
    }

    @JavascriptInterface
    public void addImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity)con).startActivityForResult(intent, 1);
    }

    @JavascriptInterface
    public void fetchLoc(){
        SecAct.homeCallbackForWV=this;
        Intent ii=new Intent(con,SecAct.class);
        ii.putExtra("act","location");
        con.startActivity(ii);
    }

    public String readAsset(String name) {
        try {
            return new FetchContents().fetch(con.getResources().getAssets().open(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
