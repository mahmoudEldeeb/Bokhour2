package com.eldeeb.bokhour.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.eldeeb.bokhour.models.CartRepositry;
import com.eldeeb.bokhour.models.database.CartItem;
import com.eldeeb.bokhour.utils.Constants;

import java.util.List;

public class ShoppingViewModel extends ViewModel {
    CartRepositry cartRepositry=new CartRepositry(Constants.application);
    public LiveData<List<CartItem>> getCart(){
        return cartRepositry.getCart();
    }
public void update(CartItem cartItem){
        cartRepositry.update(cartItem);
}
    public void delete(CartItem cartItem){
        cartRepositry.delete(cartItem);
    }
    public void deleteAll(){
        cartRepositry.deleteAll();
    }
}