package hieucdph29636.fpoly.tuhubread.DTO;

import java.sql.Blob;

public class DanhGia {
 private int id_DanhGia, id_khachHang,id_monAn;
 private String binhLuan;
 private int diem;
 private Blob anhDanhGia;

    public DanhGia() {
    }

    public DanhGia(int id_danhGia, int id_khachHang, int id_monAn, String binhLuan, int diem, Blob anhDanhGia) {
        this.id_DanhGia = id_danhGia;
        this.id_khachHang = id_khachHang;
        this.id_monAn = id_monAn;
        this.binhLuan = binhLuan;
        this.diem = diem;
        this.anhDanhGia = anhDanhGia;
    }


    public int getId_danhGia() {
        return id_DanhGia;
    }

    public void setId_danhGia(int id_danhGia) {
        this.id_DanhGia = id_danhGia;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public int getId_monAn() {
        return id_monAn;
    }

    public void setId_monAn(int id_monAn) {
        this.id_monAn = id_monAn;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public Blob getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(Blob anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }
}
