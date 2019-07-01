package com.eldeeb.bokhour.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface CartDao {
    @Insert
    void insert(CartItem cartItem);

    @Update
    void update(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("DELETE FROM cart_table")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM cart_table")
    LiveData<Integer> getRowCount();

    @Query("SELECT * FROM cart_table WHERE id=:product_id")
    LiveData<CartItem> isExist(String product_id);

    @Query("SELECT * FROM cart_table")
    LiveData<List<CartItem>> getCart();

    @Query("SELECT SUM(price) FROM cart_table")
    LiveData<Double> getTotalPrice();



}
