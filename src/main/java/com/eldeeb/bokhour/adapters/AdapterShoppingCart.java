package com.eldeeb.bokhour.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.database.CartItem;
import com.eldeeb.bokhour.utils.Tools;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AdapterShoppingCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private List<CartItem> items = new ArrayList<>();




    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView amount;
        public TextView price;
        public ImageView image;
        public RelativeLayout lyt_image;
        public MaterialRippleLayout lyt_parent;
        public  OnItemClickListener onItemClickListener;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            amount = (TextView) v.findViewById(R.id.amount);
            price = (TextView) v.findViewById(R.id.price);
            image = (ImageView) v.findViewById(R.id.image);
            lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            lyt_image = (RelativeLayout) v.findViewById(R.id.lyt_image);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(CartItem obj);
    }

    public AdapterShoppingCart(Context ctx, List<CartItem> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vItem = (ViewHolder) holder;
            final CartItem c = items.get(position);
            vItem.title.setText(c.name);
            vItem.price.setText(c.price+"");
            vItem.amount.setText(c.amount + " " + ctx.getString(R.string.items));
            Picasso.get().load(items.get(position).image).into(((ViewHolder) holder).image);
            vItem.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    OnItemClickListener onItemClickListener= (OnItemClickListener) ctx;
                    onItemClickListener.onItemClick(c);
                }
            });

            if (true) {
                vItem.lyt_image.setVisibility(View.VISIBLE);
                vItem.title.setMaxLines(2);
                vItem.lyt_parent.setEnabled(true);
            } else {
                vItem.lyt_image.setVisibility(View.GONE);
                vItem.title.setMaxLines(1);
                vItem.lyt_parent.setEnabled(false);
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<CartItem> getItem() {
        return items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }


}