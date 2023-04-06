package hieucdph29636.fpoly.tuhubread.DAO;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.TopDoanhThuMonAn;
import hieucdph29636.fpoly.tuhubread.DTO.TopMonBanChay;
import hieucdph29636.fpoly.tuhubread.DbHelper.ConnectionHelper;

public class ThongKeDAO {
    public ThongKeDAO() {
    }
    public ArrayList<TopMonBanChay> topMonBanChay(){
        ArrayList<TopMonBanChay> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT MonAn.tenMon, SUM(ChiTietDonHang.soLuong) as TongSoLuong\n" +
                        "FROM MonAn\n" +
                        "JOIN ChiTietDonHang ON MonAn.id_MonAn = ChiTietDonHang.id_monAn\n" +
                        "GROUP BY MonAn.tenMon;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    TopMonBanChay top = new TopMonBanChay();
                    top.setTen(resultSet.getString(1));
                    top.setSoLuong(resultSet.getInt(2));
                    list.add(top);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<TopDoanhThuMonAn> topDoanhThuMonAn(){
        ArrayList<TopDoanhThuMonAn> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT MonAn.tenMon, SUM(ChiTietDonHang.soLuong * MonAn.gia) as DoanhThu\n" +
                        "FROM MonAn\n" +
                        "INNER JOIN ChiTietDonHang ON MonAn.id_MonAn = ChiTietDonHang.id_monAn\n" +
                        "GROUP BY MonAn.id_MonAn, MonAn.tenMon\n" +
                        "ORDER BY DoanhThu DESC;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    TopDoanhThuMonAn top = new TopDoanhThuMonAn();
                    top.setTen(resultSet.getString(1));
                    top.setDoanhThu(resultSet.getInt(2));
                    list.add(top);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<TopDoanhThuMonAn> topDoanhThuMonAnTheoNgay(String tuNgay,String denNgay){
        ArrayList<TopDoanhThuMonAn> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "CT MonAn.tenMon, SUM(ChiTietDonHang.soLuong * MonAn.gia) as DoanhThu\n" +
                        "FROM MonAn\n" +
                        "INNER JOIN ChiTietDonHang ON MonAn.id_MonAn = ChiTietDonHang.id_monAn\n" +
                        "INNER JOIN DonHang ON ChiTietDonHang.id_donHang = DonHang.id_madonhang\n" +
                        "WHERE DonHang.thoiGianTao >= '"+tuNgay+"' AND DonHang.thoiGianTao <= '"+denNgay+"'" +
                        "GROUP BY MonAn.id_MonAn, MonAn.tenMon\n" +
                        "ORDER BY DoanhThu DESC;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    TopDoanhThuMonAn top = new TopDoanhThuMonAn();
                    top.setTen(resultSet.getString(1));
                    top.setDoanhThu(resultSet.getInt(2));
                    list.add(top);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
}
