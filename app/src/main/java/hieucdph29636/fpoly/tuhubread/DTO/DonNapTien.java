package hieucdph29636.fpoly.tuhubread.DTO;

public class DonNapTien {
    int id_DonNapTien, id_khachHang;
    String thoiGianTao;
    int trangThai;
    int tienNap;
    byte[] anhHoaDon;

    public DonNapTien() {
    }

    public DonNapTien(int id_DonNapTien, int id_khachHang, String thoiGianTao, int trangThai, int tienNap, byte[] anhHoaDon) {
        this.id_DonNapTien = id_DonNapTien;
        this.id_khachHang = id_khachHang;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.tienNap = tienNap;
        this.anhHoaDon = anhHoaDon;
    }

    public int getid_DonNapTien() {
        return id_DonNapTien;
    }

    public void setid_DonNapTien(int id) {
        this.id_DonNapTien = id;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTienNap() {
        return tienNap;
    }

    public void setTienNap(int tienNap) {
        this.tienNap = tienNap;
    }

    public byte[] getAnhHoaDon() {
        return anhHoaDon;
    }

    public void setAnhHoaDon(byte[] anhHoaDon) {
        this.anhHoaDon = anhHoaDon;
    }
}
