package hieucdph29636.fpoly.tuhubread.DTO;

public class MonAnYeuThich {
    private int id_MonAn;
    private int id_khachHang;

    public MonAnYeuThich() {
    }

    public MonAnYeuThich(int id_MonAn, int id_khachHang) {
        this.id_MonAn = id_MonAn;
        this.id_khachHang = id_khachHang;
    }

    public int getId_MonAn() {
        return id_MonAn;
    }

    public void setId_MonAn(int id_MonAn) {
        this.id_MonAn = id_MonAn;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }
}
