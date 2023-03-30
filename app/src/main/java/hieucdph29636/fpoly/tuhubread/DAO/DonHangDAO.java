package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import hieucdph29636.fpoly.tuhubread.DTO.DonHang;


public class DonHangDAO {
    SQLiteDatabase db;
    hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper dbHelper;


    public DonHangDAO(Context context) {
        dbHelper = new hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<DonHang> selectAll() {
        List<DonHang> listABC = new ArrayList<DonHang>();

        Cursor c = db.rawQuery("SELECT * From DonHang", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                int id = c.getInt(0);
                String taiKhoan = c.getString(1);
                String thoiGianTao = c.getString(2);
                int trangThai = c.getInt(3);
                int id_khuyenMai = c.getInt(4);
                int tongTien = c.getInt(5);
//                DonHang ttKhachHang = new DonHang(id,id_khachHang,thoiGianTao,trangThai,id_khuyenMai,tongTien);
                DonHang ttKhachHang = new DonHang(id, taiKhoan, thoiGianTao, trangThai, id_khuyenMai, tongTien);
                listABC.add(ttKhachHang);
                c.moveToNext();
            }

        } else {
            Log.d("@@@", "selectAll():Không có thông tin");
        }
        return listABC;
    }

    public long insertDonHang(DonHang ttcKhachHang) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", ttcKhachHang.getTaiKhoan());
        values.put("thoiGianTao", ttcKhachHang.getThoiGianTao());
        values.put("id_trangThai", ttcKhachHang.getTrangThai());
        values.put("id_khuyenMai", ttcKhachHang.getId_khuyenMai());
        values.put("tongTien", ttcKhachHang.getTongTien());
        return db.insert("DonHang", null, values);
    }

    public int updateDonHang(DonHang ttcKhachHang) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", ttcKhachHang.getTaiKhoan());
        values.put("thoiGianTao", ttcKhachHang.getThoiGianTao());
        values.put("id_trangThai", ttcKhachHang.getTrangThai());
        values.put("id_khuyenMai", ttcKhachHang.getId_khuyenMai());
        values.put("tongTien", ttcKhachHang.getTongTien());
        String[] tham_so = new String[]{ttcKhachHang.getId_DonHang() + ""};
        return db.update("DonHang", values, "id_DonHang=?", tham_so);
    }

    public int DonHang(DonHang ttcKhachHang) {
        String[] tham_so = new String[]{ttcKhachHang.getId_DonHang() + ""};
        return db.delete("DonHang", "id_DonHang=?", tham_so);
    }

}