package hieucdph29636.fpoly.tuhubread.DTO;

public class MonAnYeuThich {
    private int id_MonAn;
    private String taiKhoan;

    public MonAnYeuThich() {
    }

    public MonAnYeuThich(int id_MonAn, String taiKhoan) {
        this.id_MonAn = id_MonAn;
        this.taiKhoan = taiKhoan;
    }

    public int getId_MonAn() {
        return id_MonAn;
    }

    public void setId_MonAn(int id_MonAn) {
        this.id_MonAn = id_MonAn;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
