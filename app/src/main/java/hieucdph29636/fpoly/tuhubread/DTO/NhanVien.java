package hieucdph29636.fpoly.tuhubread.DTO;

import java.util.Date;

public class NhanVien {
    private String hoTen,soDienThoai,taiKhoan,matKhau;
    private String ngaySinh;
    private Integer quyenNhanVien;


    public NhanVien() {
    }

    public NhanVien(String taiKhoan, String hoTen, String soDienThoai,  String matKhau, String ngaySinh, Integer quyenNhanVien) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.quyenNhanVien = quyenNhanVien;
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

    public Integer getQuenNhanVien() {
        return quyenNhanVien;
    }

    public void setQuenNhanVien(Integer quenNhanVien) {
        this.quyenNhanVien = quenNhanVien;
    }
}
