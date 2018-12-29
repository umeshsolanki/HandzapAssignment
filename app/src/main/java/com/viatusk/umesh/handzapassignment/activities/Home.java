package com.viatusk.umesh.handzapassignment.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.viatusk.umesh.handzapassignment.utils.MYJS;
import com.viatusk.umesh.handzapassignment.utils.Web;

public class Home extends AppCompatActivity {
    public static WebView wv;
    public static Dialog d;
    Context con;
    MYJS js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        con=Home.this;
        LinearLayout root=new LinearLayout(con);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        wv=new WebView(con);
        root.addView(wv,-1,-1);
        js=new MYJS(con);
        wv.loadDataWithBaseURL(Web.bURL,js.readAsset("newPost.html"),"text/html","utf8",null);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.addJavascriptInterface(js,"js");
        setContentView(root);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1: {
                if (grantResults != null&&grantResults.length>0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { }
                    break;
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (intent == null) {
                return;
            }
            try {
                String imagePath = "";
                String[] imgData = { MediaStore.Images.Media.DATA };
                Cursor imgCursor = managedQuery(intent.getData(), imgData, null, null, null);
                if(imgCursor!=null) {
                    int index = imgCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    imgCursor.moveToFirst();
                    imagePath = imgCursor.getString(index);
                }
                else
                    imagePath = intent.getData().getPath();
//                    int delLink=imagePath.hashCode();
                js.evalOnHome("$('.imgContainer').append(\"<img class='imgPicked'  src='file:///"+imagePath+"' alt='"+intent.getData().getPath()+"' />&nbsp;" +
                        "<i onclick='$(this.previousSibling.previousSibling).remove();this.remove();' class='fa-2x fa fa-trash delLink'></i>\");");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
}
