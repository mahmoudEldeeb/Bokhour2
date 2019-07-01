package com.eldeeb.bokhour.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.adapters.AdapterLastProducts;
import com.eldeeb.bokhour.models.dataModels.Product;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.view.Main;
import com.eldeeb.bokhour.viewModels.LastProductsViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentFeaturedNews extends Fragment {

    private View root_view;
    private ViewPager viewPager;
    private Handler handler = new Handler();
    private Runnable runnableCode = null;
    //private AdapterFeaturedNews adapter;
    //private Call<CallbackFeaturedNews> callbackCall;
    private TextView features_news_title;
    private View lyt_main_content;
    private ImageButton bt_previous, bt_next;
    private LinearLayout layout_dots;
    LastProductsViewModel lastProductsViewModel;
    AdapterLastProducts adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_featured_news, null);
        initComponent();
        requestFeaturedNews();
        return root_view;
    }

    private void initComponent() {
        lyt_main_content = (CardView) root_view.findViewById(R.id.lyt_cart);
        features_news_title = (TextView) root_view.findViewById(R.id.featured_news_title);
        layout_dots = (LinearLayout) root_view.findViewById(R.id.layout_dots);
        viewPager = (ViewPager) root_view.findViewById(R.id.pager);
        bt_previous = (ImageButton) root_view.findViewById(R.id.bt_previous);
        bt_next = (ImageButton) root_view.findViewById(R.id.bt_next);
        lastProductsViewModel= ViewModelProviders.of(this).get(LastProductsViewModel.class);
        adapter = new AdapterLastProducts(getActivity(), new ArrayList<Product>());
        lyt_main_content.setVisibility(View.GONE);

        bt_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevAction();
            }
        });
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextAction();
            }
        });
    }

    private void displayResultData(List<Product> items) {
        adapter.setItems(items);
        viewPager.setAdapter(adapter);


        LayoutParams params = viewPager.getLayoutParams();
        params.height = Tools.getFeaturedNewsImageHeight(getActivity());
        viewPager.setLayoutParams(params);
Log.v("eeee","ccccccc");
        // displaying selected image first
        viewPager.setCurrentItem(0);
        features_news_title.setText(adapter.getItem(0).product_short_name);
        addBottomDots(layout_dots, adapter.getCount(), 0);
        Log.v("eeee","ccccccc");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                Product cur = adapter.getItem(pos);
                features_news_title.setText(cur.product_short_name);
                addBottomDots(layout_dots, adapter.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapter.getCount());
/*
      adapter.setOnItemClickListener(new AdapterFeaturedNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, NewsInfo obj) {
                Snackbar.make(root_view, obj.title, Snackbar.LENGTH_SHORT).show();
                ActivityNewsInfoDetails.navigate(getActivity(), obj.id, false);
            }
        });*/

        lyt_main_content.setVisibility(View.VISIBLE);
        //ActivityMain.getInstance().news_load = true;
        //ActivityMain.getInstance().showDataLoaded();

    }

    private void requestFeaturedNews() {
        lastProductsViewModel.getLastProducts().observe(this, new Observer<ProductsResults>() {
            @Override
            public void onChanged(@Nullable ProductsResults productsResults) {
                Log.v("eeee",productsResults.getallproducts.size()+"");
                displayResultData(productsResults.getallproducts);
            }
        });
      }

    private void startAutoSlider(final int count) {
        runnableCode = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnableCode, 3000);
            }
        };
        handler.postDelayed(runnableCode, 3000);
    }

    private void prevAction() {
        int pos = viewPager.getCurrentItem();
        pos = pos - 1;
        if (pos < 0) pos = adapter.getCount();
        viewPager.setCurrentItem(pos);
    }

    private void nextAction() {
        int pos = viewPager.getCurrentItem();
        pos = pos + 1;
        if (pos >= adapter.getCount()) pos = 0;
        viewPager.setCurrentItem(pos);
    }

    @Override
    public void onDestroy() {
        if (runnableCode != null) handler.removeCallbacks(runnableCode);
        super.onDestroy();
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 10;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(getActivity(), R.color.darkOverlaySoft));
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimaryLight));
        }
    }

    private void onFailRequest() {
     /*   if (NetworkCheck.isConnect(getActivity())) {
            showFailedView(R.string.msg_failed_load_data);
        } else {
            showFailedView(R.string.no_internet_text);
        }*/
    }

    private void showFailedView(@StringRes int message) {
        //Main.getInstance().showDialogFailed(message);
    }
}
