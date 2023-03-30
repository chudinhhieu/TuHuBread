package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class NhanVienDAO {
    DbHelper dbHelper;

    public NhanVienDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ArrayList<NhanVien> getAll_nv(Context context){
        DbHelper helper  = new DbHelper(context);
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from NhanVien", null);

        if (cs.getCount() !=0){
            cs.moveToFirst();
            do {
                dsnv.add(new NhanVien(cs.getString(0), cs.getString(1),cs.getString(2),cs.getString(3), cs.getString(4), cs.getInt(5)));
            }while (cs.moveToNext());

        }
        cs.close();
        db.close();
        return dsnv;

    }

    public static boolean insert_nv(Context context, NhanVien ttnhanvien){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen",ttnhanvien.getHoTen());
        values.put("soDienThoai",ttnhanvien.getSoDienThoai());
        values.put("taiKhoan",ttnhanvien.getTaiKhoan());
        values.put("matKhau",ttnhanvien.getMatKhau());
        values.put("ngaySinh",ttnhanvien.getNgaySinh());
        values.put("quyenNhanVien",ttnhanvien.getQuenNhanVien());
        long row = db.insert("NhanVien",null,values);
        return (row>0);
        // lớn hơn trả về true, nhỏ hơn hoặc bằng thì trả về false

    }

    public static boolean update_nv(Context context, NhanVien ttnhanvien){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen",ttnhanvien.getHoTen());
        values.put("soDienThoai",ttnhanvien.getSoDienThoai());
        values.put("taiKhoan",ttnhanvien.getTaiKhoan());
        values.put("matKhau",ttnhanvien.getMatKhau());
        values.put("ngaySinh",ttnhanvien.getNgaySinh());
        values.put("quyenNhanVien",ttnhanvien.getQuenNhanVien());
        int row = db.update("NhanVien", values,"taiKhoan=?",new String[]{ttnhanvien.getTaiKhoan()});
        return (row>0);
    }

    public static boolean delete_nv(Context context, String taikhoan){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("NhanVien","taiKhoan=?",new String[]{taikhoan});
        return (row>0);
    }
    public boolean checkDangNhap(String taiKhoan,String matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT taiKhoan, matKhau FROM NhanVien WHERE taiKhoan =? AND matKhau =?",new String[]{taiKhoan,matKhau});
        if (c.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }



}
