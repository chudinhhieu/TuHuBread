package hieucdph29636.fpoly.tuhubread.DAO;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.LoaiMon;
import hieucdph29636.fpoly.tuhubread.DbHelper.ConnectionHelper;

public class LoaiMonDAO {
    Connection connection;
    ConnectionHelper connectionHelper;

    public LoaiMonDAO() {
    }

    public void create(String tenLoai) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "INSERT INTO LoaiMonAn (tenLoai) VALUES (N'" + tenLoai + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("CREATE_ERROR", ex.getMessage());
        }
    }
    public ArrayList<LoaiMon> getAll() {
        ArrayList<LoaiMon> list = new ArrayList<>();
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String query = "SELECT * FROM LoaiMonAn";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    LoaiMon loaiMon = new LoaiMon();
                    loaiMon.setId_loai(resultSet.getInt(1));
                    loaiMon.setTenLoai(resultSet.getString(2));
                    list.add(loaiMon);
                }
            }
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        }
        return list;
    }
    public void update(int id, String tenLoai) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "UPDATE LoaiMonAn SET tenLoai =N'" + tenLoai + "'WHERE id_loaiDoAn = " + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
        }
    }
    public void delete(int id) {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        try {
            if (connection != null) {
                String sql = "DELETE FROM LoaiMonAn WHERE id_loaiDoAn = " + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
        }
    }
    public void anh(ImageView imageView) throws SQLException {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.connectionClass();
        if (connection!=null){
            String query = "SELECT anhMonAn FROM MonAn WHERE id_MonAn = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, 0); // Thay thế 1 bằng ID của ảnh bạn muốn lấy
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("anhMonAn");

                // Hiển thị ảnh sử dụng ImageView trong Android
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView.setImageBitmap(bitmap);
            }
        }


    }
}
