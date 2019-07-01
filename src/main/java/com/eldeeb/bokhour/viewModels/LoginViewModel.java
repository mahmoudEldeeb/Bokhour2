package com.eldeeb.bokhour.viewModels;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.util.Log;

import com.eldeeb.bokhour.MainActivity;
import com.eldeeb.bokhour.models.Repositry;
import com.eldeeb.bokhour.models.dataModels.Results;
import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.utils.CProgressDialog;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.view.Main;

import java.io.IOException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class LoginViewModel extends ViewModel {
    public void login(String email, final String password){
            CProgressDialog.show();
            Repositry.login(email,password).subscribeWith(new SingleObserver<Results>() {
                @Override
                public void onSubscribe(Disposable d) {

                }
                @Override
                public void onSuccess(Results results) {
                   CProgressDialog.dismiss();
                    CProgressDialog.showToast(results.getMessage());
                   if(results.isResult()){
                       UserInfo userInfo=results.getUser_info();
                       userInfo.user_pwd=password;
                       SaveInPrefrence.setLogin(true);
                       SaveInPrefrence.putUserInfo(userInfo);
                       Intent intent=new Intent(Constants.context, Main.class);
                       Activity activity = (Activity) Constants.context;
                       Constants.context.startActivity(intent);
                       activity.finish();
                   }

                }

                @Override
                public void onError(Throwable e) {
                    CProgressDialog.dismiss();
                    CProgressDialog.showToast(e.getMessage());

                }
            });
        }

    }

