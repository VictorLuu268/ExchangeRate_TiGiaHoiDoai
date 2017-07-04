package com.victor.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Victor on 03/07/2017.
 */

public class TiGia implements Serializable{
    private String type;
    private String imageUrl;
    private Bitmap bitmap;
    private String muaTienMat;
    private String muaChuyenKhoan;
    private String banTienMat;
    private String banChuyenKhoan;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMuaTienMat() {
        return muaTienMat;
    }

    public void setMuaTienMat(String muaTienMat) {
        this.muaTienMat = muaTienMat;
    }

    public String getMuaChuyenKhoan() {
        return muaChuyenKhoan;
    }

    public void setMuaChuyenKhoan(String muaChuyenKhoan) {
        this.muaChuyenKhoan = muaChuyenKhoan;
    }

    public String getBanTienMat() {
        return banTienMat;
    }

    public void setBanTienMat(String banTienMat) {
        this.banTienMat = banTienMat;
    }

    public String getBanChuyenKhoan() {
        return banChuyenKhoan;
    }

    public void setBanChuyenKhoan(String banChuyenKhoan) {
        this.banChuyenKhoan = banChuyenKhoan;
    }

    public TiGia() {
    }

    public TiGia(String type, String imageUrl, Bitmap bitmap, String muaTienMat, String muaChuyenKhoan, String banTienMAt, String banChuyenKhoan) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.bitmap = bitmap;
        this.muaTienMat = muaTienMat;
        this.muaChuyenKhoan = muaChuyenKhoan;
        this.banTienMat = banTienMAt;
        this.banChuyenKhoan = banChuyenKhoan;
    }
}
