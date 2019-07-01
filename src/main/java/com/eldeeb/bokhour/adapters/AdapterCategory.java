package com.eldeeb.bokhour.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.dataModels.Category;
import com.eldeeb.bokhour.models.dataModels.CategoryItem;
import com.eldeeb.bokhour.models.dataModels.MainCategory;
import com.eldeeb.bokhour.models.dataModels.SubCategory;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.view.ActivityProducts;

import java.util.ArrayList;
import java.util.List;


public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
   // private List<Category> items = new ArrayList<>();
   List<MainCategory> mainCategories; List<Category>categories; List<SubCategory>subCategories;

List<CategoryItem>list;



    public AdapterCategory(Context ctx) {
        this.ctx = ctx;
        list=new ArrayList<>();
        mainCategories=new ArrayList<>();
        categories=new ArrayList<>();
        subCategories=new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v;
        if (viewType==0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_category, parent, false);
        }else if(viewType==1){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category, parent, false);
        }
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vItem = (ViewHolder) holder;
            vItem.name.setText(list.get(position).name_en);
            vItem.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    int type=list.get(position).type;
                    if(position<list.size()-1) {
                        if (list.get(position + 1).type == type || list.get(position + 1).type < type) {
                            if (type == 0) {
                                addCategorys(position);
                            } else if (type == 1) {
                                addSubCategorys(position);

                            }
                        } else {
                            removeCategory(position);
                        }
                    }
                    else {
                        if (type == 0) {
                            addCategorys(position);
                        } else if (type == 1) {
                            addSubCategorys(position);
                        }
                    }
                    if(type==2){
                        Intent intent=new Intent(ctx, ActivityProducts.class);
                        intent.putExtra(Constants.CATEGORY,list.get(position));
                        ctx.startActivity(intent);
                    }
                }
            });
        }

    }

    private void addSubCategorys(int position) {
        for(int i=0;i<subCategories.size();i++){
            if(subCategories.get(i).category_id.equals(list.get(position).id)) {
                list.add(position+1, new CategoryItem(subCategories.get(i).sub_category_name,
                        subCategories.get(i).sub_category_name_ar
                        , subCategories.get(i).sub_category_id,
                        subCategories.get(i).type
                ,subCategories.get(i).category_id));
            }

        }
        notifyDataSetChanged();
    }

    private void removeCategory(int position) {
        Log.v("wwwwww","wwwwwww   "+list.get(position).type);

            for(int i=position+1;i<list.size();i++)
                {Log.v("wwwwww","   "+list.get(i).type);
                    if(list.get(i).type>list.get(position).type)
                    {
                        list.remove(i);
                        i--;
                    }
                    else
                        break;

                }
                notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    public void setItems(List<MainCategory> mainCategories, List<Category>categories, List<SubCategory>subCategories) {
       this.mainCategories=mainCategories;
       this.categories=categories;
       this.subCategories=subCategories;
        convertAllValues();
        notifyDataSetChanged();
    }
public void convertAllValues(){
        for(int i=0;i<mainCategories.size();i++){
            list.add(new CategoryItem(mainCategories.get(i).main_category_name,
                    mainCategories.get(i).main_category_name_ar,mainCategories.get(i).main_category_id,mainCategories.get(i).type
            ,mainCategories.get(i).main_category_id));
        }
}

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public MaterialRippleLayout lyt_parent;

        public ViewHolder(View v) {
            super(v);
            name =  v.findViewById(R.id.name);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }

    }

    private void addCategorys(int position) {
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).main_category_id.equals(list.get(position).id)) {
                list.add(position+1, new CategoryItem(categories.get(i).category_name, categories.get(i).category_name_ar, categories.get(i).category_id,
                        categories.get(i).type,categories.get(i).main_category_id));
            }

        }
        notifyDataSetChanged();
    }
}