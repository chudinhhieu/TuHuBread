package hieucdph29636.fpoly.tuhubread.DTO;

public class KhuyenMai {
    private int id_KhuyenMai;
    private String code;
    private String moTaKM;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int soTienGiam;

    public KhuyenMai(int id_KhuyenMai, String code, String moTaKM, String ngayBatDau, String ngayKetThuc, int soTienGiam) {
        this.id_KhuyenMai = id_KhuyenMai;
        this.code = code;
        this.moTaKM = moTaKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soTienGiam = soTienGiam;
    }

    public int getId_KhuyenMai() {
        return id_KhuyenMai;
    }

    public void setId_KhuyenMai(int id_KhuyenMai) {
        this.id_KhuyenMai = id_KhuyenMai;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoTaKM() {
        return moTaKM;
    }

    public void setMoTaKM(String moTaKM) {
        this.moTaKM = moTaKM;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(int soTienGiam) {
        this.soTienGiam = soTienGiam;
    }
}
