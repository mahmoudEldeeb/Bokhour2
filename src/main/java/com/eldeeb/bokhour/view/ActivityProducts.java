package com.eldeeb.bokhour.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.adapters.AdapterProduct;
import com.eldeeb.bokhour.models.dataModels.CategoryItem;
import com.eldeeb.bokhour.models.dataModels.Product;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.viewModels.ProductOfCategory;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivityProducts extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;

    private RecyclerView recyclerView;
    CategoryItem categoryItem;
ProductOfCategory productOfCategory;
AdapterProduct adapterProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        categoryItem= (CategoryItem) getIntent().getSerializableExtra(Constants.CATEGORY);
        Constants.context=this;
        initToolbar();
        initComponent();
        productOfCategory= ViewModelProviders.of(this).get(ProductOfCategory.class);
        requestProduct();
    }

    private void requestProduct() {
        productOfCategory.getLastProducts().observe(this, new Observer<ProductsResults>() {
            @Override
            public void onChanged(@Nullable ProductsResults productsResults) {
             adapterProduct.insertData(productsResults.getallproducts);
            }
        });
    }


    private void initComponent() {
        recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, Tools.getGridSpanCount(this)));
        recyclerView.setHasFixedSize(true);
        adapterProduct=new AdapterProduct(this,new ArrayList<Product>());
        recyclerView.setAdapter(adapterProduct);

        ((TextView) findViewById(R.id.name)).setText(categoryItem.name_en);

    }
    private void initToolbar() {
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_category_details, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == android.R.id.home){
            super.onBackPressed();
        } else if(item_id == R.id.action_search){
          //  ActivitySearch.navigate(ActivityCategoryDetails.this, category);
        } else if(item_id == R.id.action_cart){
            Intent i = new Intent(this, ActivityShoppingCart.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
