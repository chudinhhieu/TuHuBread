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

import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DbHelper.ConnectionHelper;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class MonAnDAO {
    Connection connection;
    ConnectionHelper connectionHelper;
    public MonAnDAO() {
    }

    public int updateTTMon(int tt, int id) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        int rowsAffected = 0;
        try {
            if (connection != null) {
                String query = "UPDATE MonAn SET trangThai = ? WHERE id_MonAn = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, tt);
                statement.setInt(2, id);
                rowsAffected = statement.executeUpdate();
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
        return rowsAffected;
    }
    public void delete(int id) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "DELETE FROM MonAn WHERE id_MonAn = " + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
        }
    }

    public ArrayList<MonAn> layTheoLoai(int id_loaiDoAn,int tt) {
        ArrayList<MonAn> list = new ArrayList<>();
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn where id_loaiDoAn= '"+id_loaiDoAn+"' and trangThai= '"+tt+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt(1));
                    monAn.setTenMon(resultSet.getString(2));
                    monAn.setGia(resultSet.getInt(3));
                    monAn.setThanhPhan(resultSet.getString(4));
                    monAn.setTrangThai(resultSet.getInt(5));
                    monAn.setId_LoaiDoAn(resultSet.getInt(6));
                    monAn.setAnhMonAn(resultSet.getBytes(7));
                    list.add(monAn);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<MonAn> layTheoLoaiNV(int id_loaiDoAn) {
        ArrayList<MonAn> list = new ArrayList<>();
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn where id_loaiDoAn= '"+id_loaiDoAn+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt(1));
                    monAn.setTenMon(resultSet.getString(2));
                    monAn.setGia(resultSet.getInt(3));
                    monAn.setThanhPhan(resultSet.getString(4));
                    monAn.setTrangThai(resultSet.getInt(5));
                    monAn.setId_LoaiDoAn(resultSet.getInt(6));
                    monAn.setAnhMonAn(resultSet.getBytes(7));
                    list.add(monAn);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public ArrayList<MonAn> getAll() {
        ArrayList<MonAn> list = new ArrayList<>();
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt(1));
                    monAn.setTenMon(resultSet.getString(2));
                    monAn.setGia(resultSet.getInt(3));
                    monAn.setThanhPhan(resultSet.getString(4));
                    monAn.setTrangThai(resultSet.getInt(5));
                    monAn.setId_LoaiDoAn(resultSet.getInt(6));
                    monAn.setAnhMonAn(resultSet.getBytes(7));
                    list.add(monAn);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public boolean insert(MonAn monAn) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO MonAn (tenMon, gia, thanhPhan, trangThai, id_loaiDoAn, anhMonAn) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, monAn.getTenMon());
                preparedStatement.setInt(2, monAn.getGia());
                preparedStatement.setString(3, monAn.getThanhPhan());
                preparedStatement.setInt(4, monAn.getTrangThai());
                preparedStatement.setInt(5, monAn.getId_LoaiDoAn());
                preparedStatement.setBytes(6, monAn.getAnhMonAn());
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
    public boolean update(MonAn monAn) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "UPDATE MonAn SET tenMon = ?, gia = ?, thanhPhan = ?, trangThai = ?, id_loaiDoAn = ?, anhMonAn = ? WHERE id_MonAn = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, monAn.getTenMon());
                preparedStatement.setInt(2, monAn.getGia());
                preparedStatement.setString(3, monAn.getThanhPhan());
                preparedStatement.setInt(4, monAn.getTrangThai());
                preparedStatement.setInt(5, monAn.getId_LoaiDoAn());
                preparedStatement.setBytes(6, monAn.getAnhMonAn());
                preparedStatement.setInt(7, monAn.getId_MonAn());
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
    public byte[] layAnhTheoID(int id_monAn) {
        byte[] anhMonAn = null;
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT anhMonAn FROM MonAn WHERE id_MonAn = '"+id_monAn+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    anhMonAn = resultSet.getBytes("anhMonAn");
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return anhMonAn;
    }
    public MonAn getById(int id) {
        MonAn monAn = null;
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM MonAn WHERE Id_MonAn = " + id;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    monAn = new MonAn();
                    monAn.setId_MonAn(resultSet.getInt(1));
                    monAn.setTenMon(resultSet.getString(2));
                    monAn.setGia(resultSet.getInt(3));
                    monAn.setThanhPhan(resultSet.getString(4));
                    monAn.setTrangThai(resultSet.getInt(5));
                    monAn.setId_LoaiDoAn(resultSet.getInt(6));
                    monAn.setAnhMonAn(resultSet.getBytes(7));
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return monAn;
    }
}
