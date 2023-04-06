package hieucdph29636.fpoly.tuhubread.DTO;

public class TopMonBanChay {
    String ten;
    int soLuong;

    public TopMonBanChay() {
    }

    public TopMonBanChay(String ten, int soLuong) {
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
