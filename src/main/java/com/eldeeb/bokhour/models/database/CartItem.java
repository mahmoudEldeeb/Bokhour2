package com.eldeeb.bokhour.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "cart_table")
public class CartItem
{

    public String name,image;
    @PrimaryKey
    @NonNull
    public String id;
    public int amount;
    public double price;

    public CartItem(String name, @NonNull String id, int amount, double price,String image) {
        this.name = name;
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.image=image;
    }
}
