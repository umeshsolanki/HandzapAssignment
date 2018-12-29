package com.viatusk.umesh.handzapassignment.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.viatusk.umesh.handzapassignment.R;
import com.viatusk.umesh.handzapassignment.utils.MYJS;
import com.viatusk.umesh.handzapassignment.utils.UT;


public class SecAct extends AppCompatActivity {
    Context con;
    public static MYJS homeCallbackForWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String act=getIntent().getStringExtra("act");
        if(UT.ie(act)){

        }else if(act.equals("location")){
            //either take location from gps or manual entry
            setTitle("Enter Location");
            setContentView(R.layout.frag_web_layout);
            findViewById(R.id.saveLoc).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String val= String.valueOf(((EditText)findViewById(R.id.location)).getText());
                    homeCallbackForWV.evalOnHome("$('#pLocation').val('"+val+"');");
                    finish();
                }
            });
        }else if(act.equals("postCategory")){
            //either take location from gps or manual entry
            setTitle("Select category");
            setContentView(R.layout.postcategory);
            findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checked=0;
                    traverseSelectedCheckBox(findViewById(R.id.rootView));
                    homeCallbackForWV.evalOnHome("$('#pCat').val('"+(checked==0?"Nothing":checked==1?"1 Category ":checked+" categories")+"  selected');");
                    finish();
                }
            });


        }
        con=SecAct.this;
    }
    int checked=0;
    public int traverseSelectedCheckBox(View view) {
        if (view instanceof ViewGroup)
        for(int c=0;c<((ViewGroup)view).getChildCount();c++){
            View cv = ((ViewGroup) view).getChildAt(c);
            if (cv instanceof CheckBox){
                checked+=((CheckBox) cv).isChecked()?1:0;
            }else if (cv instanceof ViewGroup){
                traverseSelectedCheckBox(cv);
            }
        }
        return checked;
    }
}