package com.example.wanandroid.util;

import android.widget.Toast;

import com.example.wanandroid.MyApplication;


/***
 * toast
 */
public class CommonUtil {
    private static Toast toast;

    public static void showToast(
            String content) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
