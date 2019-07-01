package com.eldeeb.bokhour.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eldeeb.bokhour.models.Repositry;
import com.eldeeb.bokhour.models.dataModels.CategorysResults;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.utils.CProgressDialog;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LastProductsViewModel extends ViewModel {
    public MutableLiveData<ProductsResults> getLastProducts(){
        //CProgressDialog.show();
        final MutableLiveData<ProductsResults> resultsMutableLiveData=new MutableLiveData<>();
        Repositry.getLastProducts().subscribeWith(new SingleObserver<ProductsResults>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(ProductsResults productsResults) {
                Log.v("eeee","ffffffffffffff");
                if(productsResults.result)
                    resultsMutableLiveData.setValue(productsResults);
                else CProgressDialog.showToast(productsResults.message);
                //CProgressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                Log.v("wwwww",e.getMessage());
                CProgressDialog.showToast(e.getMessage());
               // CProgressDialog.dismiss();

            }
        });
        return resultsMutableLiveData;
    }

}
