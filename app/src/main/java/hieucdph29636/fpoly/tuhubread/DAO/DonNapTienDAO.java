package hieucdph29636.fpoly.tuhubread.DAO;

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

import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DbHelper.ConnectionHelper;

public class DonNapTienDAO {

    public DonNapTienDAO() {
    }

    public ArrayList<DonNapTien> getAll() {
        ArrayList<DonNapTien> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
       Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM DonNapTien";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DonNapTien dnt = new DonNapTien();
                    dnt.setid_DonNapTien(resultSet.getInt(1));
                    dnt.setTaiKhoan(resultSet.getString(2));
                    dnt.setThoiGianTao(resultSet.getString(3));
                    dnt.setTrangThai(resultSet.getInt(4));
                    dnt.setTienNap(resultSet.getInt(5));
                    dnt.setAnhHoaDon(resultSet.getBytes(6));
                    dnt.setMota(resultSet.getString(7));
                    list.add(dnt);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public boolean insert(DonNapTien dnt) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO DonNapTien (taiKhoan, thoiGianTao, trangThai, tienNap, anhHoaDon, mota) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dnt.getTaiKhoan());
                preparedStatement.setString(2, dnt.getThoiGianTao());
                preparedStatement.setInt(3, dnt.getTrangThai());
                preparedStatement.setInt(4, dnt.getTienNap());
                preparedStatement.setBytes(5, dnt.getAnhHoaDon());
                preparedStatement.setString(6, dnt.getMota());
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
    public boolean update(DonNapTien dnt) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonNapTien SET taiKhoan = ?, thoiGianTao = ?, trangThai = ?, tienNap = ?, anhHoaDon = ?, mota = ? WHERE id_DonNapTien = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dnt.getTaiKhoan());
                preparedStatement.setString(2, dnt.getThoiGianTao());
                preparedStatement.setInt(3, dnt.getTrangThai());
                preparedStatement.setInt(4, dnt.getTienNap());
                preparedStatement.setBytes(5, dnt.getAnhHoaDon());
                preparedStatement.setString(6, dnt.getMota());
                preparedStatement.setInt(7, dnt.getid_DonNapTien());
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
    public boolean updateTT(int tt,int id) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE DonNapTien SET trangThai = ? WHERE id_DonNapTien = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, tt);
                preparedStatement.setInt(2, id);
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

}
