package com.example.giuakymobile.model;

import java.io.Serializable;

public class CongViec implements Serializable {


    private Integer maCV;
    private String tieuDe;

    private String noiDung;

    private String ngayBatDau;
    private Integer tinhChat;
    private String chuKi;

    private Integer soLan;

    private String dungSauNgay;



    private NguoiDung nguoiDung;

    public Integer getMaCV() {
        return maCV;
    }

    public void setMaCV(Integer maCV) {
        this.maCV = maCV;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Integer getTinhChat() {
        return tinhChat;
    }

    public void setTinhChat(Integer tinhChat) {
        this.tinhChat = tinhChat;
    }

    public String getChuKi() {
        return chuKi;
    }

    public void setChuKi(String chuKi) {
        this.chuKi = chuKi;
    }

    public Integer getSoLan() {
        return soLan;
    }

    public void setSoLan(Integer soLan) {
        this.soLan = soLan;
    }

    public String getDungSauNgay() {
        return dungSauNgay;
    }

    public void setDungSauNgay(String dungSauNgay) {
        this.dungSauNgay = dungSauNgay;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
