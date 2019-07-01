package com.eldeeb.bokhour.models.dataModels;

import java.util.List;

public class CategorysResults {

    public boolean result;
    public String message;
    private List<MainCategory>categories_main;

    private List<Category>categories;

    private List<SubCategory>categories_sub;

    public List<MainCategory> getCategories_main() {
        return categories_main;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<SubCategory> getCategories_sub() {
        return categories_sub;
    }

    public void setCategories_main(List<MainCategory> categories_main) {
        this.categories_main = categories_main;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategories_sub(List<SubCategory> categories_sub) {
        this.categories_sub = categories_sub;
    }
}
