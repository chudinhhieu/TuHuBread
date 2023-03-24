package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;
import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.DanhGia;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class DanhGiaDAO {
    DbHelper dbHelper;

    public DanhGiaDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ArrayList<DanhGia> getAll_danhGia(Context context){
        DbHelper helper  = new DbHelper(context);
        ArrayList<DanhGia> dsdg = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from DanhGia", null);





        while ((!cs.isAfterLast())){
            int id_DanhGia = cs.getInt(0);
            int id_khachHang = cs.getInt(1);
            int id_monAn = cs.getInt(2);
            String binhluan = cs.getString(3);
            int diem = cs.getInt(4);
            byte[] anhDanhGia = cs.getBlob(5);

            DanhGia dg = new DanhGia(id_DanhGia,id_khachHang,id_monAn,binhluan,diem,anhDanhGia);
            dsdg.add(dg);

        }
        cs.close();
        db.close();
        return dsdg;

    }

    public static boolean insert_danhgia(Context context, DanhGia ttdanhgia){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_DanhGia",ttdanhgia.getId_danhGia());
        values.put("id_khachHang",ttdanhgia.getId_khachHang());
        values.put("id_monAn",ttdanhgia.getId_monAn());
        values.put("binhLuan",ttdanhgia.getBinhLuan());
        values.put("diem",ttdanhgia.getDiem());
        values.put("anhDanhGia",ttdanhgia.getAnhDanhGia());
        long row = db.insert("DanhGia",null,values);
        return (row>0);
    }

    public static boolean update_danhgia(Context context, DanhGia ttdanhgia){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_DanhGia",ttdanhgia.getId_danhGia());
        values.put("id_khachHang",ttdanhgia.getId_khachHang());
        values.put("id_monAn",ttdanhgia.getId_monAn());
        values.put("binhLuan",ttdanhgia.getBinhLuan());
        values.put("diem",ttdanhgia.getDiem());
        values.put("anhDanhGia",ttdanhgia.getAnhDanhGia());
        int row = db.update("DanhGia", values,"id_DanhGia=?",new String[]{ttdanhgia.getId_danhGia()+" "});
        return (row>0);
    }
    public static boolean delete_danhgia(Context context, DanhGia ttdanhgia){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("DanhGia", "id_DanhGia=?",new String[]{ttdanhgia.getId_danhGia()+" "});
        return (row>0);
    }

}
