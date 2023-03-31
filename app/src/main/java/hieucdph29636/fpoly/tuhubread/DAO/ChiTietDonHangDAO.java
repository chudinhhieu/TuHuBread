package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class ChiTietDonHangDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public ChiTietDonHangDAO (Context context){
        dbHelper= new DbHelper(context);
        db=dbHelper.getWritableDatabase();

    }
    public List<ChiTietDonHang> selectAll(int id_dh){
        List<ChiTietDonHang> listABC = new ArrayList<ChiTietDonHang>();

        Cursor c = db.rawQuery("SELECT * From ChiTietDonHang where id_donHang=?",new String[]{String.valueOf(id_dh)});

        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                int id = c.getInt(0);
                int id_donHang = c.getInt(1);
                int id_monAn = c.getInt(2);
                int soLuong = c.getInt(3);
                int rau = c.getInt(4);
                int ot = c.getInt(5);
                int giaTien = c.getInt(6);
                ChiTietDonHang ttKhachHang = new ChiTietDonHang(id,id_donHang,id_monAn,soLuong,rau,ot,giaTien);
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
        values.put("rau",ttcKhachHang.getRau());
        values.put("ot",ttcKhachHang.getOt());
        values.put("giaTien",ttcKhachHang.getGiaTien());
        return db.insert("ChiTietDonHang",null,values);
    }
    public int updateChiTietDonHang(ChiTietDonHang ttcKhachHang){
        ContentValues values=new ContentValues();
        values.put("id_donHang",ttcKhachHang.getId_donHang());
        values.put("id_monAn",ttcKhachHang.getId_monAn());
        values.put("soLuong",ttcKhachHang.getSoLuong());
        values.put("rau",ttcKhachHang.getRau());
        values.put("ot",ttcKhachHang.getOt());
        values.put("giaTien",ttcKhachHang.getGiaTien());
        String[] tham_so=new String[]{ttcKhachHang.getId_donHang()+""};
        return db.update("ChiTietDonHang",values,"id_ChiTietDonHang=?",tham_so);
    }
    public int deleteChiTietDonHang(ChiTietDonHang ttcKhachHang){
        String[] tham_so=new String[]{ttcKhachHang.getId_donHang()+""};
        return db.delete("ChiTietDonHang","id_ChiTietDonHang=?",tham_so);
    }
    @SuppressLint("Range")
    public int tinhTongGiaTienTheoIdDonHang(int idDonHang) {
        int tongGiaTien = 0;
        String selectQuery = "SELECT SUM(giaTien) AS tong_gia_tien FROM ChiTietDonHang WHERE id_donHang = " + idDonHang;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            tongGiaTien = cursor.getInt(cursor.getColumnIndex("tong_gia_tien"));
        }
        cursor.close();
        db.close();
        return tongGiaTien;
    }
    @SuppressLint("Range")
    public String getTenMonAn(int id_donHang) {
        db = dbHelper.getReadableDatabase();
        String[] projection = { "MonAn.tenMon" };
        String selection = "ChiTietDonHang.id_donHang = ?";
        String[] selectionArgs = { String.valueOf(id_donHang) };
        String joinQuery = "ChiTietDonHang INNER JOIN MonAn ON ChiTietDonHang.id_monAn = MonAn.id_MonAn";
        Cursor cursor = db.query(joinQuery, projection, selection, selectionArgs, null, null, null);
        String tenMonAn = null;
        if (cursor.moveToFirst()) {
            tenMonAn = cursor.getString(cursor.getColumnIndex("tenMon"));
        }
        cursor.close();
        return tenMonAn;
    }
    @SuppressLint("Range")
    public String getTenMonAnItem(int id) {
        db = dbHelper.getReadableDatabase();
        String[] projection = { "MonAn.tenMon" };
        String selection = "ChiTietDonHang.id_ct = ?";
        String[] selectionArgs = { String.valueOf(id) };
        String joinQuery = "ChiTietDonHang INNER JOIN MonAn ON ChiTietDonHang.id_monAn = MonAn.id_MonAn";
        Cursor cursor = db.query(joinQuery, projection, selection, selectionArgs, null, null, null);
        String tenMonAn = null;
        if (cursor.moveToFirst()) {
            tenMonAn = cursor.getString(cursor.getColumnIndex("tenMon"));
        }
        cursor.close();
        return tenMonAn;
    }
    @SuppressLint("Range")
    public int getSoLuongChiTiet(int id_donHang) {
        db = dbHelper.getReadableDatabase();
        String[] projection = { "COUNT(*) as soLuong" };
        String selection = "id_donHang = ?";
        String[] selectionArgs = { String.valueOf(id_donHang) };
        Cursor cursor = db.query("ChiTietDonHang", projection, selection, selectionArgs, null, null, null);
        int soLuong = 0;
        if (cursor.moveToFirst()) {
            soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
        }
        cursor.close();
        return soLuong;
    }
}
