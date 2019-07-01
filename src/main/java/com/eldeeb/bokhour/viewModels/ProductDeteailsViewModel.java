package com.eldeeb.bokhour.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eldeeb.bokhour.models.CartRepositry;
import com.eldeeb.bokhour.models.Repositry;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.models.database.CartItem;
import com.eldeeb.bokhour.utils.CProgressDialog;
import com.eldeeb.bokhour.utils.Constants;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ProductDeteailsViewModel extends ViewModel {
    CartRepositry cartRepositry=new CartRepositry(Constants.application);
    public void addItem(CartItem cartItem){
        cartRepositry.insert(cartItem);
    }
    public void deleteItem(CartItem cartItem){
        cartRepositry.delete(cartItem);
    }

    public LiveData<CartItem> isExist(String id){
       return cartRepositry.isExist(id);
    }

    public LiveData<Integer> getCount(){
        return cartRepositry.getCount();
    }

}
