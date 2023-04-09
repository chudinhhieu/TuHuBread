package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
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
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;

public class ChiTietDonHangDAO {
    public boolean insert(ChiTietDonHang ctdh) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO ChiTietDonHang (id_donHang, id_monAn, soLuong, rau, ot, giaTien) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, ctdh.getId_donHang());
                preparedStatement.setInt(2, ctdh.getId_monAn());
                preparedStatement.setInt(3, ctdh.getSoLuong());
                preparedStatement.setInt(4, ctdh.getRau());
                preparedStatement.setInt(5, ctdh.getOt());
                preparedStatement.setInt(6, ctdh.getGiaTien());
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

    public int getSoLuongChiTiet(int id_donHang) {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        int soLuong = 0;
        try {
            if (connection != null) {
                String query = "SELECT COUNT(*) AS soLuong FROM ChiTietDonHang WHERE id_donHang = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_donHang);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    soLuong = resultSet.getInt("soLuong");
                }
            }
        } catch (Exception ex) {
            Log.e("QUERY_ERROR", ex.getMessage());
        }
        return soLuong;
    }

    public String layTenMonAn(int id_donHang) {
        String tenMonAn  = null;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT MonAn.tenMon FROM ChiTietDonHang INNER JOIN MonAn ON ChiTietDonHang.id_monAn = MonAn.id_MonAn WHERE ChiTietDonHang.id_donHang = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_donHang);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tenMonAn = resultSet.getString("tenMon");
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return tenMonAn;
    }
    public int tinhTongGiaTien(int id_donHang) {
        int tongGiaTien = 0;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT SUM(giaTien) as tongGiaTien FROM ChiTietDonHang WHERE id_donHang = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_donHang);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tongGiaTien = resultSet.getInt("tongGiaTien");
                }
            }
        } catch (Exception ex) {
            Log.e("QUERY_ERROR", ex.getMessage());
        }
        return tongGiaTien;
    }
    public List<ChiTietDonHang> getAll(int idDonhang) {
        List<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM ChiTietDonHang WHERE id_donHang = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idDonhang);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ChiTietDonHang ctdh = new ChiTietDonHang();
                    ctdh.setId_id(resultSet.getInt("id_ct"));
                    ctdh.setId_donHang(resultSet.getInt("id_donHang"));
                    ctdh.setId_monAn(resultSet.getInt("id_monAn"));
                    ctdh.setSoLuong(resultSet.getInt("soLuong"));
                    ctdh.setRau(resultSet.getInt("rau"));
                    ctdh.setOt(resultSet.getInt("ot"));
                    ctdh.setGiaTien(resultSet.getInt("giaTien"));
                    chiTietDonHangList.add(ctdh);
                }
            }
        } catch (Exception ex) {
            Log.e("SELECT_ERROR", ex.getMessage());
        }
        return chiTietDonHangList;
    }
    public String getTenMonFromChiTietDonHang(int id_chitietdonhang) {
        String tenMon = "";
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT tenMon FROM MonAn WHERE Id_MonAn = (SELECT id_monAn FROM ChiTietDonHang WHERE id_ct = ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id_chitietdonhang);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tenMon = resultSet.getString("tenMon");
                }
            }
        } catch (Exception ex) {
            Log.e("SELECT_ERROR", ex.getMessage());
        }
        return tenMon;
    }


}
