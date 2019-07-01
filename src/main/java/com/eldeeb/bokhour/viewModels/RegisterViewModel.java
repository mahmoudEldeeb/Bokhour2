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

public class RegisterViewModel extends ViewModel {
    public void register(final UserInfo userInfo){
        CProgressDialog.show();
        Repositry.register(userInfo).subscribeWith(new SingleObserver<Results>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Results results) {
                CProgressDialog.dismiss();
                if(results.isResult()){
                    CProgressDialog.showToast(results.getMessage());
                    SaveInPrefrence.putUserInfo(userInfo);
//                    Log.v("oooo",userInfo.ccode);
                    SaveInPrefrence.setLogin(true);
                    Intent intent=new Intent(Constants.context, Main.class);
                    Activity activity = (Activity) Constants.context;
                     Constants.context.startActivity(intent);
                    activity.finish();

                }
                else {
                    CProgressDialog.showToast(results.getMessage());
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
