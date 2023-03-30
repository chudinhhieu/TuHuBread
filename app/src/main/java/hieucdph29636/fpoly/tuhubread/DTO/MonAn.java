package hieucdph29636.fpoly.tuhubread.DTO;

public class MonAn {
    private int id_MonAn;
    private String tenMon;
    private int gia;
    private String thanhPhan;
    private Integer trangThai;
    private int id_LoaiDoAn;
    private int anhMonAn;

    public MonAn() {
    }

    public MonAn(int id_MonAn, String tenMon, int gia, String thanhPhan, Integer trangThai, int id_LoaiDoAn, int anhMonAn) {
        this.id_MonAn = id_MonAn;
        this.tenMon = tenMon;
        this.gia = gia;

        this.thanhPhan = thanhPhan;
        this.trangThai = trangThai;
        this.id_LoaiDoAn = id_LoaiDoAn;
        this.anhMonAn = anhMonAn;
    }

    public int getId_MonAn() {
        return id_MonAn;
    }

    public void setId_MonAn(int id_MonAn) {
        this.id_MonAn = id_MonAn;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }



    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public int getId_LoaiDoAn() {
        return id_LoaiDoAn;
    }

    public void setId_LoaiDoAn(int id_LoaiDoAn) {
        this.id_LoaiDoAn = id_LoaiDoAn;
    }

    public int getAnhMonAn() {
        return anhMonAn;
    }

    public void setAnhMonAn(int anhMonAn) {
        this.anhMonAn = anhMonAn;
    }
}
