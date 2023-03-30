package hieucdph29636.fpoly.tuhubread.DTO;

public class DonNapTien {
    int id_DonNapTien;
    String thoiGianTao, taiKhoan;
    int trangThai;
    int tienNap;
    byte[] anhHoaDon;

    public DonNapTien() {
    }

    public DonNapTien(int id_DonNapTien, String taiKhoan, String thoiGianTao, int trangThai, int tienNap, byte[] anhHoaDon) {
        this.id_DonNapTien = id_DonNapTien;
        this.taiKhoan = taiKhoan;
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

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
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
