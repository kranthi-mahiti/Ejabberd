package com.sanjay.ejabberd.utils;

import android.util.Log;


public class Logger {
    /**
     * Default constructors
     */
    private Logger() {
        // This Constructor is not Used
    }

    /**
     * function to use in catch block....
     *
     * @param tag
     * @param desc
     * @param e
     */
    public static void logE(String tag, String desc, Exception e) {
        Log.e(tag, desc, e);
    }

    /**
     * function to use for debug and showing in console..
     *
     * @param tag
     * @param desc
     */
    public static void logD(String tag, String desc) {
        Log.d(tag, "" + desc);
    }

    /**
     * function to use for debug and showing in console....
     *
     * @param tag
     * @param desc
     */
    public static void logV(String tag, String desc) {
        Log.v(tag, "" + desc);
    }
}
