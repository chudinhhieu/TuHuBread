package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class DonNapTienDAO {

    DbHelper dbHelper;

    public DonNapTienDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public static ArrayList<DonNapTien> getAll_donNapTien(Context context){
        DbHelper helper  = new DbHelper(context);
        ArrayList<DonNapTien> ds_dnt = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from DonNapTien", null);

        if (cs.getCount() !=0){
            cs.moveToFirst();
            do {
                ds_dnt.add(new DonNapTien(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getInt(3),cs.getInt(4),cs.getBlob(5)));
            }while (cs.moveToNext());

        }
        cs.close();
        db.close();
        return ds_dnt;
    }

    public static boolean insert_donNapTien(Context context, DonNapTien ttdonnaptien){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_DonNapTien",ttdonnaptien.getid_DonNapTien());
        values.put("id_khachHang",ttdonnaptien.getId_khachHang());
        values.put("thoiGianTao",ttdonnaptien.getThoiGianTao());
        values.put("trangThai",ttdonnaptien.getTrangThai());
        values.put("tienNap",ttdonnaptien.getTienNap());
        values.put("anhHoaDon",ttdonnaptien.getAnhHoaDon());
        long row = db.insert("DonNapTien",null,values);
        return (row>0);
    }

    public static boolean update_donNapTien(Context context, DonNapTien ttdonnaptien){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_DonNapTien",ttdonnaptien.getid_DonNapTien());
        values.put("id_khachHang",ttdonnaptien.getId_khachHang());
        values.put("thoiGianTao",ttdonnaptien.getThoiGianTao());
        values.put("trangThai",ttdonnaptien.getTrangThai());
        values.put("tienNap",ttdonnaptien.getTienNap());
        values.put("anhHoaDon",ttdonnaptien.getAnhHoaDon());
        int row = db.update("DonNapTien", values,"id_DonNapTien=?",new String[]{ttdonnaptien.getid_DonNapTien()+" "});
        return (row>0);
    }

    public static boolean delete_donNapTien(Context context, int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("DonNapTien","id_DonNapTien=?",new String[]{id+" "});
        return (row>0);
    }

//    while ((!cs.isAfterLast())){
//            int id_DonNapTien = cs.getInt(0);
//            int id_khachHang = cs.getInt(1);
//            String thoiGianTao = cs.getString(2);
//            int trangThai = cs.getInt(3);
//            int tienNap = cs.getInt(4);
//            byte[] anhHoaDon = cs.getBlob(5);
//            DonNapTien dnt = new DonNapTien(id_DonNapTien,id_khachHang,thoiGianTao,trangThai,tienNap,anhHoaDon);
//            ds_dnt.add(dnt);
}
