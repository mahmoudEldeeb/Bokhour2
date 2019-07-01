package com.eldeeb.bokhour.models.dataModels;

import java.io.Serializable;

public class CategoryItem implements Serializable {
    public String name_en,name_ar,id,parent_id;
    public int type;
    public CategoryItem(String name_en, String name_ar, String id,int t,String parent_id) {
        this.name_en = name_en;
        this.name_ar = name_ar;
        this.id = id;
        this.type=t;
        this.parent_id=parent_id;
    }
}
