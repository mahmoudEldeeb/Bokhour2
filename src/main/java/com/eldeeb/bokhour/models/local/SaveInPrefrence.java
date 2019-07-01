package com.eldeeb.bokhour.models.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.utils.Constants;

public class SaveInPrefrence {
public static SharedPreferences getPrefrence(){
    SharedPreferences preferences;
    preferences= Constants.context.getSharedPreferences("com.eldeeb.bokhour", Context.MODE_PRIVATE);
    return preferences;
}
public static SharedPreferences.Editor getEditor(){
    return   getPrefrence().edit();
}
public static void putUserInfo(UserInfo userInfo){
    SharedPreferences.Editor editor=getEditor();
    editor.putString(Constants.CID,userInfo.cid);
    editor.putString(Constants.FIRST_NAME,userInfo.cfirstname);
    editor.putString(Constants.LAST_NAME,userInfo.clastname);
    editor.putString(Constants.PASSWORD,userInfo.user_pwd);
    editor.putString(Constants.PHONE,userInfo.cmobile);
    editor.putString(Constants.EMIAL,userInfo.cemail);
    editor.putString(Constants.GENDER,userInfo.cgender);
    editor.putString(Constants.CCODE,userInfo.ccode);
    try {
        editor.putString(Constants.CURRENCY_ID, userInfo.currency.currency_id);
        editor.putString(Constants.CURRENCY_CODE, userInfo.currency.currency_code);
    }catch (NullPointerException e){}
    editor.commit();

}
public static String getCurrency(){
    return getPrefrence().getString(Constants.CURRENCY_CODE,"USA");
}
public static void setLogin(boolean login){
    getEditor().putBoolean(Constants.IS_LOGIN,login).commit();

}
public static boolean isLogin(){
    return getPrefrence().getBoolean(Constants.IS_LOGIN,false);
}

public static  String getCCode(){
    return getPrefrence().getString(Constants.CCODE,"0");
}


    public static  String getCid(){
        return getPrefrence().getString(Constants.CID,"0");
    }

    public static String getPassword() {
    return getPrefrence().getString(Constants.PASSWORD,"0");
    }

    public static void setCompleteData(boolean b) {
        getEditor().putBoolean(Constants.IS_COMPLETE_DATA,b).commit();
    }
}
