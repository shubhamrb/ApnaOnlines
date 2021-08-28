package com.mamits.apnaonlines.data.model.orders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreDetailDataModel implements Serializable {

    @SerializedName("id")
    int id;

    @SerializedName("user_id")
    int user_id;

    @SerializedName("store_id")
    String store_id;

    @SerializedName("name")
    String name;

    @SerializedName("mobile_number")
    String mobile_number;

    @SerializedName("zipcode")
    String zipcode;

    @SerializedName("state_id")
    int state_id;

    @SerializedName("city")
    String city;

    @SerializedName("ratting")
    String ratting;

    @SerializedName("latitude")
    String latitude;

    @SerializedName("longitude")
    String longitude;

    @SerializedName("address")
    String address;

    @SerializedName("image")
    String image;

    @SerializedName("openingtime")
    String openingtime;

    @SerializedName("closingtime")
    String closingtime;

    @SerializedName("payment_accept_mode")
    String payment_accept_mode;

    @SerializedName("storelogo")
    String storelogo;

    @SerializedName("images")
    String images;

    @SerializedName("whatsapp_no")
    String whatsapp_no;

    @SerializedName("upi_number")
    String upi_number;

    @SerializedName("account_number")
    String account_number;

    @SerializedName("bank_name")
    String bank_name;

    @SerializedName("ifsc_code")
    String ifsc_code;

    @SerializedName("description")
    String description;

    @SerializedName("CreatedBy")
    int CreatedBy;

    @SerializedName("UpdatedBy")
    int UpdatedBy;

    @SerializedName("IsVerify")
    int IsVerify;

    @SerializedName("IsAvailable")
    int IsAvailable;

    @SerializedName("IsBlock")
    int IsBlock;

    @SerializedName("qrcode")
    String qrcode;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public void setClosingtime(String closingtime) {
        this.closingtime = closingtime;
    }

    public String getPayment_accept_mode() {
        return payment_accept_mode;
    }

    public void setPayment_accept_mode(String payment_accept_mode) {
        this.payment_accept_mode = payment_accept_mode;
    }

    public String getStorelogo() {
        return storelogo;
    }

    public void setStorelogo(String storelogo) {
        this.storelogo = storelogo;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getWhatsapp_no() {
        return whatsapp_no;
    }

    public void setWhatsapp_no(String whatsapp_no) {
        this.whatsapp_no = whatsapp_no;
    }

    public String getUpi_number() {
        return upi_number;
    }

    public void setUpi_number(String upi_number) {
        this.upi_number = upi_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int createdBy) {
        CreatedBy = createdBy;
    }

    public int getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        UpdatedBy = updatedBy;
    }

    public int getIsVerify() {
        return IsVerify;
    }

    public void setIsVerify(int isVerify) {
        IsVerify = isVerify;
    }

    public int getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        IsAvailable = isAvailable;
    }

    public int getIsBlock() {
        return IsBlock;
    }

    public void setIsBlock(int isBlock) {
        IsBlock = isBlock;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "StoreDetailDataModel{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", store_id='" + store_id + '\'' +
                ", name='" + name + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state_id=" + state_id +
                ", city='" + city + '\'' +
                ", ratting='" + ratting + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", openingtime='" + openingtime + '\'' +
                ", closingtime='" + closingtime + '\'' +
                ", payment_accept_mode='" + payment_accept_mode + '\'' +
                ", storelogo='" + storelogo + '\'' +
                ", images='" + images + '\'' +
                ", whatsapp_no='" + whatsapp_no + '\'' +
                ", upi_number='" + upi_number + '\'' +
                ", account_number='" + account_number + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", ifsc_code='" + ifsc_code + '\'' +
                ", description='" + description + '\'' +
                ", CreatedBy=" + CreatedBy +
                ", UpdatedBy=" + UpdatedBy +
                ", IsVerify=" + IsVerify +
                ", IsAvailable=" + IsAvailable +
                ", IsBlock=" + IsBlock +
                ", qrcode='" + qrcode + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
