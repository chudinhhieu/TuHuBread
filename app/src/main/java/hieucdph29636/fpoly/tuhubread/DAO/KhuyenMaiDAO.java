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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.MyDate;


public class KhuyenMaiDAO {
    public KhuyenMaiDAO() {
    }
    public ArrayList<KhuyenMai> getAll() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection  connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM KhuyenMai";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    KhuyenMai km = new KhuyenMai();
                    km.setId_KhuyenMai(resultSet.getInt(1));
                    km.setCode(resultSet.getString(2));
                    km.setMoTaKM(resultSet.getString(3));
                    km.setNgayBatDau(resultSet.getString(4));
                    km.setNgayKetThuc(resultSet.getString(5));
                    km.setSoTienGiam(resultSet.getInt(6));
                    list.add(km);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }

    public boolean insert(KhuyenMai km) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO KhuyenMai (code, moTaKM, ngayBatDau, ngayKetThuc, soTienGiam) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, km.getCode());
                preparedStatement.setString(2, km.getMoTaKM());
                preparedStatement.setString(3, km.getNgayBatDau());
                preparedStatement.setString(4, km.getNgayKetThuc());
                preparedStatement.setInt(5, km.getSoTienGiam());
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
    public boolean update(KhuyenMai km) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE KhuyenMai SET code = ?, moTaKM = ?, ngayBatDau = ?, ngayKetThuc = ?, soTienGiam = ? WHERE id_KhuyenMai = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, km.getCode());
                preparedStatement.setString(2, km.getMoTaKM());
                preparedStatement.setString(3, km.getNgayBatDau());
                preparedStatement.setString(4, km.getNgayKetThuc());
                preparedStatement.setInt(5, km.getSoTienGiam());
                preparedStatement.setInt(6, km.getId_KhuyenMai());
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
    public void delete(int id) {
       ConnectionHelper connectionHelper = new ConnectionHelper();
       Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "DELETE FROM KhuyenMai WHERE id_KhuyenMai = " + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
        }
    }
    public List<KhuyenMai> checkCode(String code) {
        List<KhuyenMai> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM KhuyenMai WHERE code = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, code);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    KhuyenMai km = new KhuyenMai();
                    km.setId_KhuyenMai(resultSet.getInt("id_KhuyenMai"));
                    km.setCode(resultSet.getString("code"));
                    km.setMoTaKM(resultSet.getString("moTaKM"));
                    km.setNgayBatDau(resultSet.getString("ngayBatDau"));
                    km.setNgayKetThuc(resultSet.getString("ngayKetThuc"));
                    km.setSoTienGiam(resultSet.getInt("soTienGiam"));
                    list.add(km);
                }
            }
        } catch (Exception ex) {
            Log.e("SELECT_ERROR", ex.getMessage());
        }
        return list;
    }
    public List<KhuyenMai> getKhuyenMaiById(int id_KhuyenMai) {
        List<KhuyenMai> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT soTienGiam FROM KhuyenMai WHERE id_KhuyenMai = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_KhuyenMai);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int soTienGiam = resultSet.getInt("soTienGiam");
                    KhuyenMai km = new KhuyenMai();
                    km.setId_KhuyenMai(id_KhuyenMai);
                    km.setSoTienGiam(soTienGiam);
                    list.add(km);
                }
            }
        } catch (Exception ex) {
            Log.e("SELECT_ERROR", ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Log.e("CONNECTION_ERROR", ex.getMessage());
            }
        }
        return list;
    }

}
