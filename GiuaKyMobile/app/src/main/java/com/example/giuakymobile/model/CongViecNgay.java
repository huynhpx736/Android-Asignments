package com.example.giuakymobile.model;

import java.io.Serializable;

public class CongViecNgay implements Serializable {
    private String hinhAnh;

    private Boolean trangThai;

    private CongViec congViec;

    private String ngayLam;

    private Integer maCvNgay;

    public String getHinhAnh() {
        return this.hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Boolean getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public CongViec getCongViec() {
        return this.congViec;
    }

    public void setCongViec(CongViec congViec) {
        this.congViec = congViec;
    }

    public String getNgayLam() {
        return this.ngayLam;
    }

    public void setNgayLam(String ngayLam) {
        this.ngayLam = ngayLam;
    }

    public Integer getMaCvNgay() {
        return this.maCvNgay;
    }

    public void setMaCvNgay(Integer maCvNgay) {
        this.maCvNgay = maCvNgay;
    }
}
