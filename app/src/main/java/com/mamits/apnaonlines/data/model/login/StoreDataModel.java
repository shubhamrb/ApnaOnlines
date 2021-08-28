package com.mamits.apnaonlines.data.model.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreDataModel implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("store_id")
    private String store_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "StoreDataModel{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}
