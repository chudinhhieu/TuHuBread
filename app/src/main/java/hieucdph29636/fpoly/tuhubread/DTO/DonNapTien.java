package hieucdph29636.fpoly.tuhubread.DTO;

import java.sql.Blob;
import java.util.Date;

public class DonNapTien {
    int id_DonNapTien, id_khachHang;
    Date thoiGianTao;
    int trangThai;
    Number tienNap;
    Blob anhHoaDon;

    public DonNapTien() {
    }

    public DonNapTien(int id, int id_khachHang, Date thoiGianTao, int trangThai, Number tienNap, Blob anhHoaDon) {
        this.id_DonNapTien = id;
        this.id_khachHang = id_khachHang;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.tienNap = tienNap;
        this.anhHoaDon = anhHoaDon;
    }

    public int getId() {
        return id_DonNapTien;
    }

    public void setId(int id) {
        this.id_DonNapTien = id;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Number getTienNap() {
        return tienNap;
    }

    public void setTienNap(Number tienNap) {
        this.tienNap = tienNap;
    }

    public Blob getAnhHoaDon() {
        return anhHoaDon;
    }

    public void setAnhHoaDon(Blob anhHoaDon) {
        this.anhHoaDon = anhHoaDon;
    }
}
