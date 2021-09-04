package com.mamits.apnaonlines.data.model.services;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VariationDataModel implements Serializable {

    @SerializedName("name")
    String name;

    @SerializedName("value")
    double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VariationDataModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
