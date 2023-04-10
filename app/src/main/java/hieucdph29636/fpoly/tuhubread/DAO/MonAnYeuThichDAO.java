package hieucdph29636.fpoly.tuhubread.DAO;

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

import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.MonAnYeuThich;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;

public class MonAnYeuThichDAO {
    Connection connection;
    ConnectionHelper connectionHelper;

    public boolean insert(MonAnYeuThich monAnyt) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO MonAnYeuThich (id_MonAn, taiKhoan) VALUES (?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, monAnyt.getId_MonAn());
                preparedStatement.setString(2, monAnyt.getTaiKhoan());
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
    public void delete(int id) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "DELETE FROM MonAnYeuThich WHERE id_MonAn = " + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
        }
    }
    public ArrayList<MonAnYeuThich> layTheoLoaiTK(String tk) {
        ArrayList<MonAnYeuThich> list = new ArrayList<>();
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAnYeuThich where taiKhoan= '"+tk+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    MonAnYeuThich monAnyt = new MonAnYeuThich();
                    monAnyt.setId_MonAn(resultSet.getInt(1));
                    monAnyt.setTaiKhoan(resultSet.getString(2));
                    list.add(monAnyt);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public boolean kiemTraThichMonAn(String tk, int idMonAn) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        boolean daThich = false;
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAnYeuThich WHERE taiKhoan = ? AND id_MonAn = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, tk);
                statement.setInt(2, idMonAn);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    daThich = true;
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Log.e("CLOSE_CONN_ERROR", ex.getMessage());
            }
        }
        return daThich;
    }
}
