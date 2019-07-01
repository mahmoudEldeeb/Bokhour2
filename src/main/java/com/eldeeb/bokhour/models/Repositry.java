package com.eldeeb.bokhour.models;

import android.util.Log;

import com.eldeeb.bokhour.models.dataModels.CategorysResults;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.models.dataModels.Result;
import com.eldeeb.bokhour.models.dataModels.Results;
import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.models.network.RetrofitConnection;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Repositry {
    public static Single<Results> register(UserInfo userInfo){
        return RetrofitConnection.getNetworkConnection().register("en",userInfo.cfirstname,userInfo.clastname,userInfo.user_pwd,
                userInfo.user_pwd2,userInfo.cemail,userInfo.cmobile,userInfo.cgender)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Single<Results> login(String email,String password){
        return RetrofitConnection.getNetworkConnection().login("en",email,password)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<CategorysResults> getCategory(){
        return RetrofitConnection.getNetworkConnection().getCategory("en","getcats","1905310719204")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<ProductsResults> getLastProducts(){
        return RetrofitConnection.getNetworkConnection().getLastProducts("en","getallproducts","1905310719204")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<ProductsResults> getProductsOfCategory(){
        return RetrofitConnection.getNetworkConnection().getProductsOfCategory("en","getallproducts","1905310719204","0","0")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



    public static Single<Results> getprofile(){
        Log.v("eeee",                SaveInPrefrence.getCCode()+"    "+SaveInPrefrence.getCCode()+"   "+SaveInPrefrence.getCid());
        return RetrofitConnection.getNetworkConnection().getprofile("en","getprofile",
                SaveInPrefrence.getCCode(),SaveInPrefrence.getCCode(),SaveInPrefrence.getCid())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Single<Result> saveProfile(UserInfo userInfo){
       return RetrofitConnection.getNetworkConnection().saveProfile("en","saveprofile",
                SaveInPrefrence.getCid(),
                SaveInPrefrence.getCCode(),userInfo.cfirstname,userInfo.clastname,userInfo.user_pwd,
               userInfo.user_pwd2,userInfo.cemail,userInfo.cmobile,userInfo.cgender,userInfo.ccountry,
               userInfo.ccity,
               userInfo.caddress,userInfo.cccno,userInfo.cseccode,userInfo.expireMM,userInfo.expireYY)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
