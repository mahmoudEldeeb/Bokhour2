package com.eldeeb.bokhour.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eldeeb.bokhour.models.Repositry;
import com.eldeeb.bokhour.models.dataModels.CategorysResults;
import com.eldeeb.bokhour.utils.CProgressDialog;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class CategoryViewModel extends ViewModel {
public MutableLiveData<CategorysResults>getCategorys(){
    CProgressDialog.show();
    final MutableLiveData<CategorysResults> resultsMutableLiveData=new MutableLiveData<>();
    Repositry.getCategory().subscribeWith(new SingleObserver<CategorysResults>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onSuccess(CategorysResults categorysResults) {
            Log.v("oooooo","vvvvvvvvv");
            if(categorysResults.result)
        resultsMutableLiveData.setValue(categorysResults);
            else CProgressDialog.showToast(categorysResults.message);
        CProgressDialog.dismiss();
        }

        @Override
        public void onError(Throwable e) {
            Log.v("oooooo",e.getMessage());
            CProgressDialog.showToast(e.getMessage());
            CProgressDialog.dismiss();

        }
    });
    return resultsMutableLiveData;
}
}
