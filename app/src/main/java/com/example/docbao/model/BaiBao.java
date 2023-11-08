package com.example.docbao.model;

public class BaiBao {
    private String image;
    private String tieuDe;
    private String ngayDang;

    public BaiBao() {
    }

    public BaiBao(String image, String tieuDe, String ngayDang) {
        this.image = image;
        this.tieuDe = tieuDe;
        this.ngayDang = ngayDang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }
}
