package com.viatusk.umesh.handzapassignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by UMESH-ADMIN on 31-01-2017.
 */

public class FetchContents {

    public String fetch(InputStream is) {
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String x="";
        try {
            while((x=br.readLine())!=null){
                sb.append(x);
            }
        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }

    public String fromFile(File file){
        StringBuilder sb=new StringBuilder();
        try{
        InputStreamReader br= new InputStreamReader(new FileInputStream(file));
        char[] bfr=new char[1024*1024];
        int mark=0;
        try {
            while((mark=br.read(bfr))>0){
                sb.append(bfr,0,mark);
            }
        } catch (IOException e) {
            return "";
        }

        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }
    public String fetchFromBR(BufferedReader br) {
        
        StringBuilder sb=new StringBuilder();
        String x="";
        try {
            while((x=br.readLine())!=null){
                sb.append(x);
            }
        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }
}
