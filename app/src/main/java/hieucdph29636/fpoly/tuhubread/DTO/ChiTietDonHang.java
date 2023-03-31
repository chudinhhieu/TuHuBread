package hieucdph29636.fpoly.tuhubread.DTO;

public class ChiTietDonHang {
    private int id_id;
    private int id_donHang;
    private int id_monAn;
    private int soLuong;
    private int giaTien,rau,ot;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int id_id, int id_donHang, int id_monAn, int soLuong, int rau, int ot, int giaTien) {
        this.id_id = id_id;
        this.id_donHang = id_donHang;
        this.id_monAn = id_monAn;
        this.rau = rau;
        this.ot = ot;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public int getRau() {
        return rau;
    }

    public void setRau(int rau) {
        this.rau = rau;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public int getId_id() {
        return id_id;
    }

    public void setId_id(int id_id) {
        this.id_id = id_id;
    }

    public int getId_donHang() {
        return id_donHang;
    }

    public void setId_donHang(int id_donHang) {
        this.id_donHang = id_donHang;
    }

    public int getId_monAn() {
        return id_monAn;
    }

    public void setId_monAn(int id_monAn) {
        this.id_monAn = id_monAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
