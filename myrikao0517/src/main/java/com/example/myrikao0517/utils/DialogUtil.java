package com.example.myrikao0517.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载...");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
