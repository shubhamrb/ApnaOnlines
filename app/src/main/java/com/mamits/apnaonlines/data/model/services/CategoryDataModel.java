package com.mamits.apnaonlines.data.model.services;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryDataModel implements Serializable {

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("subcategory")
    List<SubCategoryDataModel> subcategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategoryDataModel> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<SubCategoryDataModel> subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public String toString() {
        return "CategoryDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subcategory=" + subcategory +
                '}';
    }
}
