package hieucdph29636.fpoly.tuhubread.DTO;

public class TopDoanhThuMonAn {
    String ten;
    int doanhThu;

    public TopDoanhThuMonAn() {
    }

    public TopDoanhThuMonAn(String ten, int doanhThu) {
        this.ten = ten;
        this.doanhThu = doanhThu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }
}
