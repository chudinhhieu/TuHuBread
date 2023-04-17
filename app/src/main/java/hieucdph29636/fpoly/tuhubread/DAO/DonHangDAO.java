package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;


public class DonHangDAO {
    public DonHangDAO() {
    }
    public ArrayList<MonAn> getMonAnChuaDanhGia(int idDonHang) {
        ArrayList<MonAn> monAnList = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn WHERE id_MonAn IN (SELECT id_monAn FROM ChiTietDonHang WHERE id_donHang=?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idDonHang);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt("id_MonAn"));
                    monAn.setTenMon(resultSet.getString("tenMon"));
                    monAn.setGia(resultSet.getInt("gia"));
                    monAn.setThanhPhan(resultSet.getString("thanhPhan"));
                    monAn.setTrangThai(resultSet.getInt("trangThai"));
                    monAn.setId_LoaiDoAn(resultSet.getInt("id_loaiDoAn"));
                    monAn.setAnhMonAn(resultSet.getBytes("anhMonAn"));

                    // Kiểm tra món ăn có trong bảng DanhGia hay không
                    String danhGiaQuery = "SELECT * FROM DanhGia WHERE id_monAn=? and id_donHang=?";
                    PreparedStatement danhGiaStatement = connection.prepareStatement(danhGiaQuery);
                    danhGiaStatement.setInt(1, monAn.getId_MonAn());
                    danhGiaStatement.setInt(2, idDonHang);
                    ResultSet danhGiaResultSet = danhGiaStatement.executeQuery();
                    if (!danhGiaResultSet.next()) {
                        // Món ăn chưa được đánh giá
                        monAnList.add(monAn);
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("DONHANG_ERROR", ex.getMessage());
        }
        return monAnList;
    }
    public ArrayList<MonAn> getMonAnInDonHang(int idDonHang) {
        ArrayList<MonAn> monAnList = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn WHERE id_MonAn IN (SELECT id_monAn FROM ChiTietDonHang WHERE id_donHang=?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idDonHang);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt("id_MonAn"));
                    monAn.setTenMon(resultSet.getString("tenMon"));
                    monAn.setGia(resultSet.getInt("gia"));
                    monAn.setThanhPhan(resultSet.getString("thanhPhan"));
                    monAn.setTrangThai(resultSet.getInt("trangThai"));
                    monAn.setId_LoaiDoAn(resultSet.getInt("id_loaiDoAn"));
                    monAn.setAnhMonAn(resultSet.getBytes("anhMonAn"));
                    monAnList.add(monAn);
                }
            }
        } catch (Exception ex) {
            Log.e("DONHANG_ERROR", ex.getMessage());
        }
        return monAnList;
    }
    public ArrayList<DonHang> selectAll() {
        ArrayList<DonHang> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
       Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM DonHang ORDER BY id_madonhang DESC";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DonHang dh = new DonHang();
                    dh.setId_DonHang(resultSet.getInt(1));
                    dh.setTaiKhoan(resultSet.getString(2));
                    dh.setThoiGianTao(resultSet.getString(3));
                    dh.setTrangThai(resultSet.getInt(4));
                    dh.setId_khuyenMai(resultSet.getInt(5));
                    dh.setTongTien(resultSet.getInt(6));
                    list.add(dh);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<DonHang> selectAllKH(String tk) {
        ArrayList<DonHang> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM DonHang where taiKhoan = '"+tk+"' ORDER BY id_madonhang DESC";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DonHang dh = new DonHang();
                    dh.setId_DonHang(resultSet.getInt(1));
                    dh.setTaiKhoan(resultSet.getString(2));
                    dh.setThoiGianTao(resultSet.getString(3));
                    dh.setTrangThai(resultSet.getInt(4));
                    dh.setId_khuyenMai(resultSet.getInt(5));
                    dh.setTongTien(resultSet.getInt(6));
                    list.add(dh);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public boolean insert(DonHang dh) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO DonHang (taiKhoan, thoiGianTao, trangThai,tongTien) VALUES (?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dh.getTaiKhoan());
                preparedStatement.setString(2, dh.getThoiGianTao());
                preparedStatement.setInt(3, dh.getTrangThai());
                preparedStatement.setInt(4, dh.getTongTien());
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("INSERT_ERROR", ex.getMessage());
        }
        return success;
    }

    public List<DonHang> checkDonHang(){
        List<DonHang> listABC = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM DonHang WHERE trangThai = 0 ORDER BY id_madonhang DESC");
            while (rs.next()) {
                int id = rs.getInt("id_madonhang");
                String taiKhoan = rs.getString("taiKhoan");
                String thoiGianTao = rs.getString("thoiGianTao");
                int trangThai = rs.getInt("trangThai");
                int id_khuyenMai = rs.getInt("id_khuyenMai");
                int tongTien = rs.getInt("tongTien");
                DonHang ttKhachHang = new DonHang(id, taiKhoan, thoiGianTao, trangThai, id_khuyenMai, tongTien);
                listABC.add(ttKhachHang);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch (Exception e){
        }
        return listABC;
    }

    public boolean updateDonHang(int idDonHang, int trangThai, int idKhuyenMai,int tongTien) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonHang SET trangThai=?, id_khuyenMai=?,tongTien=? WHERE id_madonhang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, trangThai);
                preparedStatement.setInt(2, idKhuyenMai);
                preparedStatement.setInt(3, tongTien);
                preparedStatement.setInt(4, idDonHang);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
        return success;
    }
    public boolean updateDonHangKKM(int idDonHang, int trangThai,int tongTien) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonHang SET trangThai=?,tongTien=? WHERE id_madonhang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, trangThai);
                preparedStatement.setInt(2, tongTien);
                preparedStatement.setInt(3, idDonHang);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
        return success;
    }
    public boolean updateTrangThai(int idDonHang, int trangThai) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonHang SET trangThai=? WHERE id_madonhang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, trangThai);
                preparedStatement.setInt(2, idDonHang);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
        return success;
    }
    public boolean updateGia(int idDonHang, int gia) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonHang SET tongTien=? WHERE id_madonhang=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, gia);
                preparedStatement.setInt(2, idDonHang);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
        return success;
    }
    public List<DonHang> getByID(int iddh){
        List<DonHang> listABC = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DonHang WHERE id_madonhang = "+iddh);
            while (rs.next()) {
                int id = rs.getInt("id_madonhang");
                String taiKhoan = rs.getString("taiKhoan");
                String thoiGianTao = rs.getString("thoiGianTao");
                int trangThai = rs.getInt("trangThai");
                int id_khuyenMai = rs.getInt("id_khuyenMai");
                int tongTien = rs.getInt("tongTien");
                DonHang ttKhachHang = new DonHang(id, taiKhoan, thoiGianTao, trangThai, id_khuyenMai, tongTien);
                listABC.add(ttKhachHang);
            }
            rs.close();
            stmt.close();
            connection.close();
        }catch (Exception e){
        }
        return listABC;
    }
}