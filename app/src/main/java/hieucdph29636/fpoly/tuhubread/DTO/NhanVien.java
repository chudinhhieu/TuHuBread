package hieucdph29636.fpoly.tuhubread.DTO;

import java.util.Date;

public class NhanVien {
  private int id_NhanVien;
  private String hoTen,soDienThoai,taiKhoan,matKhau;
  private Date ngaySinh;
  private String quenNhanVien;


    public NhanVien() {
    }

    public NhanVien(int id, String hoTen, String soDienThoai, String taiKhoan, String matKhau, Date ngaySinh, String quenNhanVien) {
        this.id_NhanVien = id;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.quenNhanVien = quenNhanVien;
    }

    public int getId() {
        return id_NhanVien;
    }

    public void setId(int id) {
        this.id_NhanVien = id;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQuenNhanVien() {
        return quenNhanVien;
    }

    public void setQuenNhanVien(String quenNhanVien) {
        this.quenNhanVien = quenNhanVien;
    }
}
