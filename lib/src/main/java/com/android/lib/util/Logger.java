
package com.android.lib.util;

import android.util.Log;

/**
 * Created by ThinhNH on 08/08/2016.
 * utility class for logging
 */
public class Logger {
    private static final boolean DEBUG = true;
    private static final String TAG = "EMC";
    private static final int STACK_TRACE_LEVELS_UP = 4;

    public static void d(String pMsg) {
        if (DEBUG) {
            Log.d(TAG, String.format("%-30s : %-30s : %-5s : ", getClassName(), getMethodName(), getLineNumber()) + pMsg);
        }
    }

    public static void e(String pMsg) {
        if (DEBUG) {
            Log.e(TAG, String.format("%-30s : %-30s : %-5s : ", getClassName(), getMethodName(), getLineNumber()) + pMsg);
        }
    }

    public static void v(String pMsg) {
        if (DEBUG) {
            Log.v(TAG, String.format("%-30s : %-30s : %-5s : ", getClassName(), getMethodName(), getLineNumber()) + pMsg);
        }
    }


    /**
     * Get the current class name. Note, this will only work as called from this
     * class as it has to go a predetermined number of steps up the stack trace.
     * In this case 5.
     *
     * @return String - Current line number.
     */
    private static String getClassName() {
        String fileName = Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName();

        // Removing ".java" and returning class name
        return fileName.substring(0, fileName.length() - 5);
    }

    /**
     * Get the current method name. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @return String - Current line number.
     */
    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName();
    }

    /**
     * Get the current line number. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @return int - Current line number.
     */
    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getLineNumber();
    }
}
