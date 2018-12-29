/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viatusk.umesh.handzapassignment.utils;

import android.util.Log;

/**
 *
 * @author UMESH-ADMIN
 */
public class UT {
    public static String LOGGER="Logger:";

    public static void e(String values) {
        Log.e(LOGGER,values);
    }

    public static boolean ie(String...values){
        for(String val:values){
            if(val==null||val.length()<1){
            return true;
            }
        }
        return false;
    }
}
