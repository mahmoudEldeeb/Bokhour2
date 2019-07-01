package com.eldeeb.bokhour.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class CProgressDialog {

    public static ProgressDialog progressDialog;
    public static void show(){
        progressDialog =new ProgressDialog(Constants.context);
        progressDialog.setTitle("loading....");
        progressDialog.show();
    }
    public static void dismiss(){
        progressDialog.dismiss();
    }
    public static void showToast(String text){
        Toast.makeText(Constants.context,text,Toast.LENGTH_SHORT).show();
    }
}
