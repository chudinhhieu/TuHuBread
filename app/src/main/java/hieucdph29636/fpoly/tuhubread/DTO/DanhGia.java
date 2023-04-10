package hieucdph29636.fpoly.tuhubread.DTO;

public class DanhGia {
    private int id_DanhGia,id_monAn;
    private String binhLuan,taiKhoan;
    private int diem,id_donHang;

    public DanhGia() {
    }

    public int getId_donHang() {
        return id_donHang;
    }

    public void setId_donHang(int id_donHang) {
        this.id_donHang = id_donHang;
    }

    public int getId_danhGia() {
        return id_DanhGia;
    }

    public void setId_danhGia(int id_danhGia) {
        this.id_DanhGia = id_danhGia;
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

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }


}
