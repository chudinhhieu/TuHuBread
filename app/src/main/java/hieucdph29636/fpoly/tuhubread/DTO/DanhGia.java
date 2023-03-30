package hieucdph29636.fpoly.tuhubread.DTO;

public class DanhGia {
    private int id_DanhGia,id_monAn;
    private String binhLuan,taiKhoan;
    private int diem;
    private byte[] anhDanhGia;

    public DanhGia() {
    }

    public DanhGia(int id_DanhGia, String taiKhoan, int id_monAn, String binhLuan, int diem, byte[] anhDanhGia) {
        this.id_DanhGia = id_DanhGia;
        this.taiKhoan = DanhGia.this.taiKhoan;
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

    public byte[] getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(byte[] anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }
}
