package hieucdph29636.fpoly.tuhubread.DTO;

public class LoaiMon {
    private int id_loai;
    private String tenLoai;

    public LoaiMon(int id_loai, String tenLoai) {
        this.id_loai = id_loai;
        this.tenLoai = tenLoai;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
