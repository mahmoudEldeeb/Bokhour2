package com.eldeeb.bokhour.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.adapters.AdapterCategory;
import com.eldeeb.bokhour.models.dataModels.CategorysResults;
import com.eldeeb.bokhour.models.dataModels.MainCategory;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.viewModels.CategoryViewModel;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {

    private View root_view;
    private RecyclerView recyclerView;
   // private Call<CallbackCategory> callbackCall;
    private AdapterCategory adapter;
    CategoryViewModel categoryViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_category, null);
        initComponent();
       requestListCategory();
        return root_view;
    }

    private void initComponent() {
        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        //set data and list adapter
        adapter = new AdapterCategory(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
       // recyclerView.setVisibility(View.GONE);

     /*   adapter.setOnItemClickListener(new AdapterCategory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Category obj) {
                Snackbar.make(root_view, obj.name, Snackbar.LENGTH_SHORT).show();
               // ActivityCategoryDetails.navigate(getActivity(), obj);
            }
        });*/

       categoryViewModel= ViewModelProviders.of(this).get(CategoryViewModel.class);
    }


    private void requestListCategory() {
        Log.v("ooooo",
                 "    bb    ");
        categoryViewModel.getCategorys().observe(this, new Observer<CategorysResults>() {
            @Override
            public void onChanged(@Nullable CategorysResults categorysResults) {
                Log.v("ooooo",categorysResults.getCategories_main()
                .size()+  "    bb" );
adapter.setItems(categorysResults.getCategories_main(),categorysResults.getCategories(),categorysResults.getCategories_sub());
            }
        });
    }

    private void onFailRequest() {
        /*if (NetworkCheck.isConnect(getActivity())) {
            showFailedView(R.string.msg_failed_load_data);
        } else {
            showFailedView(R.string.no_internet_text);
        }*/
    }

    private void showFailedView(@StringRes int message) {
      //  ActivityMain.getInstance().showDialogFailed(message);
    }

}

