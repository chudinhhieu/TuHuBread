package hieucdph29636.fpoly.tuhubread.DTO;

public class DonHang {
    private int id_DonHang;
    private String taiKhoan;
    private String soDienThoai;
    private String thoiGianTao;
    private int trangThai;
    private int id_khuyenMai;
    private int tongTien;

    public DonHang(int id, String taiKhoan, String thoiGianTao, int trangThai, int id_khuyenMai, int tongTien) {
    }

    public DonHang(int id_DonHang, String taiKhoan, String soDienThoai, String thoiGianTao, int trangThai, int id_khuyenMai, int tongTien) {
        this.id_DonHang = id_DonHang;
        this.taiKhoan = taiKhoan;
        this.soDienThoai = soDienThoai;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.id_khuyenMai = id_khuyenMai;
        this.tongTien = tongTien;
    }

    public int getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(int id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
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

    public int getId_khuyenMai() {
        return id_khuyenMai;
    }

    public void setId_khuyenMai(int id_khuyenMai) {
        this.id_khuyenMai = id_khuyenMai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
