package com.eldeeb.bokhour.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eldeeb.bokhour.models.Repositry;
import com.eldeeb.bokhour.models.dataModels.Result;
import com.eldeeb.bokhour.models.dataModels.Results;
import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.utils.CProgressDialog;


import java.io.IOException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class ProfileViewModel extends ViewModel {
    public MutableLiveData<UserInfo> getprofile(){
        final MutableLiveData<UserInfo>userinfo=new MutableLiveData<>();
       Repositry.getprofile().subscribeWith(new SingleObserver<Results>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Results results) {
                if(results.isResult()) {
                    userinfo.setValue(results.getUser_info());
                }else CProgressDialog.showToast(results.getMessage());
                }

            @Override
            public void onError(Throwable e) {


                CProgressDialog.dismiss();
                CProgressDialog.showToast(e.getMessage());
            }
        });
        return userinfo;
    }
public void saveProfile(final UserInfo userInfo){
CProgressDialog.show();
        Repositry.saveProfile(userInfo).subscribeWith(new SingleObserver<Result>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Result result) {
                CProgressDialog.showToast(result.message);
                if(result.result){
                    SaveInPrefrence.setCompleteData(true);
                }
                CProgressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                CProgressDialog.showToast(e.getMessage());
                CProgressDialog.dismiss();
            }
        });

    }
}
