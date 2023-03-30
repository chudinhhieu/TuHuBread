package hieucdph29636.fpoly.tuhubread.DTO;

public class KhachHang {
    private String hoTen;
    private String soDienThoai;
    private String taiKhoan;
    private String matKhau;
    private String ngaySinh;
    private String diaChi;
    private int soDuTaiKhoan;


    public KhachHang(String taiKhoan,String hoTen, String soDienThoai,  String matKhau, String ngaySinh, String diaChi, int soDuTaiKhoan) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDuTaiKhoan = soDuTaiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoDuTaiKhoan() {
        return soDuTaiKhoan;
    }

    public void setSoDuTaiKhoan(int soDuTaiKhoan) {
        this.soDuTaiKhoan = soDuTaiKhoan;
    }
}
