/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viatusk.umesh.handzapassignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UMESH-ADMIN
 */
public class FIO {

    public static boolean write(String completePath,String content,boolean append){
        try{
            FileWriter wr=new FileWriter(completePath,append);
            wr.write(content);
            wr.flush();
            wr.close();
        }catch (Exception ex) {  ex.printStackTrace();return false;  }
        return true;
    }
    public boolean streamToFile(InputStream is,String completePath) {
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(completePath);
            byte[] buffer=new byte[1024*1024*2];
            int mark=0;
            while((mark=is.read(buffer))>0){
             fos.write(buffer,0,mark);
             fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                fos.close();
            }catch (Exception e){}
        }
        return true;
    }

    public static String read(File completePath){
        BufferedReader br= null;
        try {
            br = new BufferedReader(new FileReader(completePath));
            StringBuilder sb=new StringBuilder();
            String x="";
            try {
                while((x=br.readLine())!=null){
                    sb.append(x);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
            return sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FetchContents.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(FetchContents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    
    public static String read(String completePath){
        BufferedReader br= null;
        try {
            br = new BufferedReader(new FileReader(completePath));
            StringBuilder sb=new StringBuilder();
            String x="";
            try {
                while((x=br.readLine())!=null){
                    sb.append(x);
                }
            } catch (Exception e) {
                return "";
            }
            return sb.toString();
        } catch (Exception ex) {
//            Logger.getLogger(FetchContents.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
//                Logger.getLogger(FetchContents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    
    public static String read(InputStream is) {
//        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        byte[] buffer=new byte[1024*1024*8];
        int read=0;
        StringBuilder sb=new StringBuilder();
//        String x="";
        try {
            while((read=is.read(buffer))>0){
                sb.append(new String(buffer, 0, read));
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }
    public static String read(BufferedReader br) {
        
        StringBuilder sb=new StringBuilder();
        String x="";
        try {
            while((x=br.readLine())!=null){
                sb.append(x);
            }
        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }
}
