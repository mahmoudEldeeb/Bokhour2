package com.eldeeb.bokhour.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.dataModels.Product;
import com.eldeeb.bokhour.models.database.CartItem;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.viewModels.ProductDeteailsViewModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class ActivityProductDetails extends AppCompatActivity {

    private Long product_id;
    private Boolean from_notif;

    // extra obj
    private Product product;

    private MenuItem wishlist_menu;
    private boolean flag_wishlist = false;
    private boolean flag_cart = false;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private View parent_view;
    ImageView pager;
    private SwipeRefreshLayout swipe_refresh;
    private MaterialRippleLayout lyt_add_cart;
    private TextView tv_add_cart,price,date;
    ProductDeteailsViewModel productDeteailsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initToolbar();
        product= (Product) getIntent().getSerializableExtra(Constants.PRODUCT);
        initComponent();
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
    }

    private void initComponent() {
        parent_view = findViewById(android.R.id.content);
        swipe_refresh =  findViewById(R.id.swipe_refresh_layout);
        lyt_add_cart =  findViewById(R.id.lyt_add_cart);
        tv_add_cart =  findViewById(R.id.tv_add_cart);
        price=findViewById(R.id.price);
        pager=findViewById(R.id.pager);
        Constants.application=this.getApplication();
        productDeteailsViewModel= ViewModelProviders.of(this).get(ProductDeteailsViewModel.class);

        ((TextView) findViewById(R.id.title)).setText(product.product_short_name);
        ((TextView) findViewById(R.id.date)).setText(product.dtadded);
        ((WebView) findViewById(R.id.content)).loadData(product.product_description,"text/html", null);

        price.setText(product.selling_price+" "+ SaveInPrefrence.getCurrency());
        Picasso.get().load(product.img1path).into(pager);

        // on swipe


        lyt_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product == null || (product.product_short_name != null && product.product_short_name
                        .equals(""))) {
                    Toast.makeText(getApplicationContext(), R.string.please_wait_text, Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    CartItem cartItem=new CartItem(product.product_short_name,product.product_id,1,
                            Double.parseDouble(product.selling_price),product.img1path);
                    if(flag_cart){
                        productDeteailsViewModel.deleteItem(cartItem);
                    }
                    else {
                        productDeteailsViewModel.addItem(cartItem);
                    }
                }
                toggleCartButton();
            }
        });
productDeteailsViewModel.isExist(product.product_id).observe(this, new Observer<CartItem>() {
    @Override
    public void onChanged(@Nullable CartItem cartItem) {
        if(cartItem!=null){
            lyt_add_cart.setBackgroundColor(getResources().getColor(R.color.colorRemoveCart));
            tv_add_cart.setText(R.string.bt_remove_cart);
            flag_cart=true;
        } else {
            flag_cart=false;
            lyt_add_cart.setBackgroundColor(getResources().getColor(R.color.colorAddCart));
            tv_add_cart.setText(R.string.bt_add_cart);
        }
    }
});
    }
    private void toggleCartButton() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_product_details, menu);
        //wishlist_menu = menu.findItem(R.id.action_wish);
        //refreshWishlistMenu();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == android.R.id.home){
            super.onBackPressed();
        } else if(item_id == R.id.action_cart){
            Intent i = new Intent(this, ActivityShoppingCart.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
