package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class NhanVienDAO {

    public static ArrayList<NhanVien> getAll_nv( Context context){
        DbHelper helper  = new DbHelper(context);
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from NhanVien", null);
        while ((!cs.isAfterLast())){
            int id_nv = cs.getInt(0);
            String ten_nv = cs.getString(1);
            String sdt_nv = cs.getString(2);
            String taikhoan = cs.getString(3);
            String matkhau = cs.getString(4);
            String ngaysinh_nv = cs.getString(5);
            int quyen_nv = cs.getInt(6);
            NhanVien nv = new NhanVien(id_nv,ten_nv,sdt_nv,taikhoan,matkhau,ngaysinh_nv,quyen_nv);
            dsnv.add(nv);

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
        int row = db.update("NhanVien", values,"id_NhanVien=?",new String[]{ttnhanvien.getId()+" "});
        return (row>0);
    }

    public static boolean delete_nv(Context context, NhanVien ttnhanvien){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("NhanVien","id_NhanVien=?",new String[]{ttnhanvien.getId()+" "});
        return (row>0);
    }




}
