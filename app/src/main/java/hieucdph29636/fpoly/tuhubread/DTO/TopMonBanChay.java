package hieucdph29636.fpoly.tuhubread.DTO;

public class TopMonBanChay {
    String ten;
    int soLuong;
    int id_monAn;

    public int getId_monAn() {
        return id_monAn;
    }

    public void setId_monAn(int id_monAn) {
        this.id_monAn = id_monAn;
    }

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
