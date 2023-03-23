package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import hieucdph29636.fpoly.tuhubread.DBHelper.DBHelper;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;

public class ChiTietDonHangDAO {
    SQLiteDatabase db;
    DBHelper dbHelper;
    public ChiTietDonHangDAO (Context context){
        dbHelper= new DBHelper(context);
=======
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class ChiTietDonHangDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public ChiTietDonHangDAO (Context context){
        dbHelper= new DbHelper(context);
>>>>>>> origin/master
        db=dbHelper.getWritableDatabase();

    }
    public void close(){dbHelper.close();}
    public List<ChiTietDonHang> selectAll(){
        List<ChiTietDonHang> listABC = new ArrayList<ChiTietDonHang>();

        Cursor c = db.rawQuery("SELECT * From ChiTietDonHang",null);

        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                int id = c.getInt(0);
                int id_donHang = c.getInt(1);
                int id_monAn = c.getInt(2);
                int soLuong = c.getInt(3);
                int giaTien = c.getInt(4);
                ChiTietDonHang ttKhachHang = new ChiTietDonHang(id,id_donHang,id_monAn,soLuong,giaTien);
                listABC.add(ttKhachHang);
                c.moveToNext();
            }

        }else {
            Log.d("@@@","selectAll():Không có thông tin");
        }
        return listABC;
    }
    public long insertChiTietDonHang(ChiTietDonHang ttcKhachHang){
        ContentValues values = new ContentValues();
        values.put("id_donHang",ttcKhachHang.getId_donHang());
        values.put("id_monAn",ttcKhachHang.getId_monAn());
        values.put("soLuong",ttcKhachHang.getSoLuong());
        values.put("giaTien",ttcKhachHang.getGiaTien());
        return db.insert("ChiTietDonHang",null,values);
    }
    public int updateChiTietDonHang(ChiTietDonHang ttcKhachHang){
        ContentValues values=new ContentValues();
        values.put("id_donHang",ttcKhachHang.getId_donHang());
        values.put("id_monAn",ttcKhachHang.getId_monAn());
        values.put("soLuong",ttcKhachHang.getSoLuong());
        values.put("giaTien",ttcKhachHang.getGiaTien());
        String[] tham_so=new String[]{ttcKhachHang.getId_donHang()+""};
        return db.update("ChiTietDonHang",values,"id_ChiTietDonHang=?",tham_so);
    }
<<<<<<< HEAD
    public int ChiTietDonHang(ChiTietDonHang ttcKhachHang){
=======
    public int deleteChiTietDonHang(ChiTietDonHang ttcKhachHang){
>>>>>>> origin/master
        String[] tham_so=new String[]{ttcKhachHang.getId_donHang()+""};
        return db.delete("ChiTietDonHang","id_ChiTietDonHang=?",tham_so);
    }
}
