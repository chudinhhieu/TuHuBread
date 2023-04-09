package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;

public class KhachHangDAO {
    public KhachHangDAO() {
    }
    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM KhachHang";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setTaiKhoan(resultSet.getString(1));
                    kh.setHoTen(resultSet.getString(2));
                    kh.setSoDienThoai(resultSet.getString(3));
                    kh.setMatKhau(resultSet.getString(4));
                    kh.setNgaySinh(resultSet.getString(5));
                    kh.setDiaChi(resultSet.getString(6));
                    kh.setSoDuTaiKhoan(resultSet.getInt(7));
                    list.add(kh);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public boolean insert(KhachHang kh) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO KhachHang (hoTen, soDienThoai, taiKhoan, matKhau, ngaySinh, diaChi,soDuTaiKhoan) VALUES (?, ?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, kh.getHoTen());
                preparedStatement.setString(2, kh.getSoDienThoai());
                preparedStatement.setString(3, kh.getTaiKhoan());
                preparedStatement.setString(4, kh.getMatKhau());
                preparedStatement.setString(5, kh.getNgaySinh());
                preparedStatement.setString(6, kh.getDiaChi());
                preparedStatement.setInt(7, kh.getSoDuTaiKhoan());
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
    public boolean updateTien(int tien,String taiKhoan) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE KhachHang SET soDuTaiKhoan = ? WHERE taiKhoan = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, tien);
                preparedStatement.setString(2, taiKhoan);
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
    public boolean checkDangNhap(String taiKhoan, String matKhau) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT taiKhoan, matKhau FROM KhachHang WHERE taiKhoan = ? AND matKhau = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, taiKhoan);
                preparedStatement.setString(2, matKhau);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    success = true;
                }
            }
        } catch (Exception ex) {
            Log.e("SELECT_ERROR", ex.getMessage());
        }
        return success;
    }

    public String getHoTen(String taiKhoan) {
        String hoTen = null;
       ConnectionHelper connectionHelper = new ConnectionHelper();
       Connection connection = connectionHelper.connectionClass();
        try {

            if (connection != null) {
                String query = "SELECT hoTen FROM KhachHang WHERE taiKhoan = '"+taiKhoan+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    hoTen = resultSet.getString("hoTen");
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return hoTen;
    }
    public int getSoDuVi(String taiKhoan) {
        int soDu = 0;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {

            if (connection != null) {
                String query = "SELECT soDuTaiKhoan FROM KhachHang WHERE taiKhoan = '"+taiKhoan+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    soDu = resultSet.getInt("soDuTaiKhoan");
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return soDu;
    }
}
