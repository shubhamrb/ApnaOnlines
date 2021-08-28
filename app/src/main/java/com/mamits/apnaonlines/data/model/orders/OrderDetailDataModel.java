package com.mamits.apnaonlines.data.model.orders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetailDataModel implements Serializable {

    @SerializedName("name")
    String name;

    @SerializedName("filedata")
    FileDataModel filedata;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDataModel getFiledata() {
        return filedata;
    }

    public void setFiledata(FileDataModel filedata) {
        this.filedata = filedata;
    }

    @Override
    public String toString() {
        return "OrderDetailDataModel{" +
                "name='" + name + '\'' +
                ", filedata=" + filedata +
                '}';
    }
}
