package com.eldeeb.bokhour.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.dataModels.Product;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.view.ActivityProductDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLastProducts extends PagerAdapter {


    private List<Product> items;
Activity context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        //void onItemClick(View view, NewsInfo obj);
    }
    // constructor
    public AdapterLastProducts(Activity c, List<Product> items) {
        this.context = c;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    public Product getItem(int pos) {
        return items.get(pos);
    }

    public void setItems(List<Product> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
       // View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        final Product o = items.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_last_products, container, false);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
        Picasso.get().load(items.get(position).img1path).into(image);
        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                  //  onItemClickListener.onItemClick(v, o);
                    Intent intent=new Intent(context, ActivityProductDetails.class);
                    intent.putExtra(Constants.PRODUCT,o);
                    context.startActivity(intent);

            }
        });

        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}
