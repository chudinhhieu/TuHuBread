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
import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DbHelper.ConnectionHelper;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;


public class KhuyenMaiDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public KhuyenMaiDAO(Context context) {
    dbHelper=new DbHelper(context);
    db=dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}
    public List<KhuyenMai> selectAll(){
        List<KhuyenMai> listPro = new ArrayList<KhuyenMai>();

        Cursor c = db.rawQuery("SELECT * FROM KhuyenMai",null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);
                String moTa=c.getString(2);
                String ngayBatDau=c.getString(3);
                String ngayKT=c.getString(4);
                int soTiengiam=c.getInt(5);
                KhuyenMai tmpLoai = new KhuyenMai( _id, _name,moTa,ngayBatDau,ngayKT,soTiengiam);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }
    public long insertKMai(KhuyenMai objKM){
        ContentValues values=new ContentValues();
        values.put("code",objKM.getCode());
        values.put("moTaKM", objKM.getMoTaKM());
        values.put("ngayBatDau",objKM.getNgayBatDau());
        values.put("ngayKetThuc", objKM.getNgayKetThuc());
        values.put("soTienGiam",objKM.getSoTienGiam()+"");
        return db.insert("KhuyenMai",null,values);
    }
    public long updateKMai(KhuyenMai objKM){
        ContentValues values=new ContentValues();
        values.put("code",objKM.getCode());
        values.put("moTaKM", objKM.getMoTaKM());
        values.put("ngayBatDau",objKM.getNgayBatDau());
        values.put("ngayKetThuc", objKM.getNgayKetThuc());
        values.put("soTienGiam",objKM.getSoTienGiam());
        return db.update("KhuyenMai",values,"id_KhuyenMai=?",new String[]{String.valueOf(objKM.getId_KhuyenMai())});
    }
    public int deleteKMai(KhuyenMai objKM){
        String[] so=new String[]{objKM.getId_KhuyenMai()+""};
        return db.delete("KhuyenMai","id_KhuyenMai=?",so);
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
