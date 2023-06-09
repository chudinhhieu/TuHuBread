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
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;

public class NhanVienDAO {
    public NhanVienDAO() {
    }
    public int getQuyen(String taiKhoan) {
        int quyen = 0;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {

            if (connection != null) {
                String query = "SELECT quyenNhanVien FROM NhanVien WHERE taiKhoan = '"+taiKhoan+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    quyen = resultSet.getInt("quyenNhanVien");
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return quyen;
    }
    public ArrayList<NhanVien> getByHoTen(String hoTen) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM NhanVien WHERE hoTen = '" + hoTen + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setTaiKhoan(resultSet.getString(1));
                    nv.setHoTen(resultSet.getString(2));
                    nv.setSoDienThoai(resultSet.getString(3));
                    nv.setMatKhau(resultSet.getString(4));
                    nv.setNgaySinh(resultSet.getString(5));
                    nv.setQuenNhanVien(resultSet.getInt(6));
                    list.add(nv);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<NhanVien> getByTK(String tk) {
        ArrayList<NhanVien> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM NhanVien WHERE taiKhoan = '" + tk + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setTaiKhoan(resultSet.getString(1));
                    nv.setHoTen(resultSet.getString(2));
                    nv.setSoDienThoai(resultSet.getString(3));
                    nv.setMatKhau(resultSet.getString(4));
                    nv.setNgaySinh(resultSet.getString(5));
                    nv.setQuenNhanVien(resultSet.getInt(6));
                    list.add(nv);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM NhanVien";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setTaiKhoan(resultSet.getString(1));
                    nv.setHoTen(resultSet.getString(2));
                    nv.setSoDienThoai(resultSet.getString(3));
                    nv.setMatKhau(resultSet.getString(4));
                    nv.setNgaySinh(resultSet.getString(5));
                    nv.setQuenNhanVien(resultSet.getInt(6));
                    list.add(nv);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }

    public boolean insert(NhanVien nv) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO NhanVien (hoTen, soDienThoai, taiKhoan, matKhau, ngaySinh,quyenNhanVien) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nv.getHoTen());
                preparedStatement.setString(2, nv.getSoDienThoai());
                preparedStatement.setString(3, nv.getTaiKhoan());
                preparedStatement.setString(4, nv.getMatKhau());
                preparedStatement.setString(5, nv.getNgaySinh());
                preparedStatement.setInt(6, nv.getQuenNhanVien());
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
    public boolean update(NhanVien nv) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE NhanVien SET hoTen = ?, soDienThoai = ?, matKhau = ?, ngaySinh = ?, quyenNhanVien = ? WHERE taiKhoan = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nv.getHoTen());
                preparedStatement.setString(2, nv.getSoDienThoai());
                preparedStatement.setString(3, nv.getMatKhau());
                preparedStatement.setString(4, nv.getNgaySinh());
                preparedStatement.setInt(5, nv.getQuenNhanVien());
                preparedStatement.setString(6, nv.getTaiKhoan());
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
    public boolean updateNV(String ht,String ns,String sdt,String tk) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE NhanVien SET hoTen = ?, soDienThoai = ?, ngaySinh = ? WHERE taiKhoan = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ht);
                preparedStatement.setString(2, sdt);
                preparedStatement.setString(3, ns);
                preparedStatement.setString(4, tk);
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
    public boolean updateMK(String tk,String mk) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE NhanVien SET matKhau = ? WHERE taiKhoan = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, mk);
                preparedStatement.setString(2, tk);
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
    public void delete(String taiKhoan) {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "DELETE FROM NhanVien WHERE taiKhoan = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, taiKhoan);
                statement.executeUpdate();
            }
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
        }
    }

    public boolean checkDangNhap(String taiKhoan, String matKhau) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT taiKhoan, matKhau FROM NhanVien WHERE taiKhoan = ? AND matKhau = ?";
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
}
