package hieucdph29636.fpoly.tuhubread.DAO;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.DanhGia;
import hieucdph29636.fpoly.tuhubread.DBHelper.ConnectionHelper;

public class DanhGiaDAO {
    public boolean insert_danhgia(DanhGia danhGia) {
        boolean success = false;
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "INSERT INTO DanhGia ( taiKhoan, id_monAn, binhLuan, diem,id_donHang) VALUES ( ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, danhGia.getTaiKhoan());
                preparedStatement.setInt(2, danhGia.getId_monAn());
                preparedStatement.setString(3, danhGia.getBinhLuan());
                preparedStatement.setInt(4, danhGia.getDiem());
                preparedStatement.setInt(5, danhGia.getId_donHang());
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
    public ArrayList<DanhGia> getByID(int id) {
        ArrayList<DanhGia> list = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM DanhGia where id_monAn='"+id+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DanhGia dg = new DanhGia();
                    dg.setId_danhGia(resultSet.getInt(1));
                    dg.setTaiKhoan(resultSet.getString(2));
                    dg.setId_monAn(resultSet.getInt(3));
                    dg.setBinhLuan(resultSet.getString(4));
                    dg.setDiem(resultSet.getInt(5));
                    dg.setId_donHang(resultSet.getInt(6));
                    list.add(dg);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
}
