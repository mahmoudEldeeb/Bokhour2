package com.eldeeb.bokhour.models;

import android.app.Application;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.os.AsyncTask;

import com.eldeeb.bokhour.models.database.CartDao;
import com.eldeeb.bokhour.models.database.CartItem;
import com.eldeeb.bokhour.models.database.database;

import java.util.List;

public class CartRepositry {

    private CartDao cartDao;
    public CartRepositry(Application application){
        database db = database.getDatabase(application);
        cartDao= db.dao();

    }
    public void insert (CartItem cartItem) {
        new insertAsyncTask(cartDao).execute(cartItem);
    }
    public void update (CartItem cartItem) {
        //new insertAsyncTask(cartDao).execute(cartItem);
        new updateAsyncTask(cartDao).execute(cartItem);
    }

    public void delete(CartItem cartItem){

        new deleteAsyncTask(cartDao).execute(cartItem);

    }
    public void deleteAll(){

        new deleteAllAsyncTask(cartDao).execute();

    }
    public LiveData<CartItem>isExist(String id){
        return cartDao.isExist(id);
    }

    public LiveData<List<CartItem>>getCart(){
        return cartDao.getCart();

    }

    public LiveData<Integer>getCount(){
        return cartDao.getRowCount();
    }

    private  class insertAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao mAsyncTaskCartDao;

        insertAsyncTask(CartDao CartDao) {
            mAsyncTaskCartDao = CartDao;
        }

        @Override
        protected Void doInBackground(final CartItem... params) {
            try {

                mAsyncTaskCartDao.insert(params[0]);

            }
            catch (Exception e){

            }
            return null;
        }
    }

    private  class updateAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao mAsyncTaskCartDao;

        updateAsyncTask(CartDao CartDao) {
            mAsyncTaskCartDao = CartDao;
        }

        @Override
        protected Void doInBackground(final CartItem... params) {
            try {

                mAsyncTaskCartDao.update(params[0]);

            }
            catch (Exception e){

            }
            return null;
        }
    }
    private class deleteAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao mAsyncTaskCartDao;

        deleteAsyncTask(CartDao CartDao) {
            mAsyncTaskCartDao = CartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            try {

                mAsyncTaskCartDao.delete(cartItems[0]);

            }
            catch (Exception e){

            }
            return null;
        }
    }
    private class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private CartDao mAsyncTaskCartDao;

        deleteAllAsyncTask(CartDao CartDao) {
            mAsyncTaskCartDao = CartDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                mAsyncTaskCartDao.deleteAll();

            }
            catch (Exception e){

            }
            return null;
        }
    }

}
