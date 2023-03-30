package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class KhachHangDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public KhachHangDAO(Context context){
        dbHelper= new DbHelper(context);

        db=dbHelper.getWritableDatabase();
    }
    public void close(){dbHelper.close();}
    public List<KhachHang> selectAll(){
        List<KhachHang> listABC = new ArrayList<KhachHang>();

        Cursor c = db.rawQuery("SELECT * From KhachHang",null);

        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                String taiKhoan = c.getString(0);
                String hoTen = c.getString(1);
                String soDienThoai = c.getString(2);
                String matKhau = c.getString(3);
                String ngaySinh = c.getString(4);
                String diaChi = c.getString(5);
                int soDuTaiKhoan = c.getInt(6);
                KhachHang ttKhachHang = new KhachHang(taiKhoan,hoTen,soDienThoai,matKhau,ngaySinh,diaChi,soDuTaiKhoan);
                listABC.add(ttKhachHang);
                c.moveToNext();
            }

        }else {
            Log.d("@@@","selectAll():Không có thông tin");
        }
        return listABC;
    }
    public long insertKhachHang(KhachHang ttcKhachHang){
        ContentValues values = new ContentValues();
        values.put("hoTen",ttcKhachHang.getHoTen());
        values.put("soDienThoai",ttcKhachHang.getSoDienThoai());
        values.put("taiKhoan",ttcKhachHang.getTaiKhoan());
        values.put("matKhau",ttcKhachHang.getMatKhau());
        values.put("ngaySinh",ttcKhachHang.getNgaySinh());
        values.put("diaChi",ttcKhachHang.getDiaChi());
        values.put("soDuTaiKhoan",ttcKhachHang.getSoDuTaiKhoan());
        return db.insert("KhachHang",null,values);
    }
    public int updateKhachHang(KhachHang ttcKhachHang){
        ContentValues values=new ContentValues();
        values.put("hoTen",ttcKhachHang.getHoTen());
        values.put("soDienThoai",ttcKhachHang.getSoDienThoai());
        values.put("taiKhoan",ttcKhachHang.getTaiKhoan());
        values.put("matKhau",ttcKhachHang.getMatKhau());
        values.put("ngaySinh",ttcKhachHang.getNgaySinh());
        values.put("diaChi",ttcKhachHang.getDiaChi());
        values.put("soDuTaiKhoan",ttcKhachHang.getSoDuTaiKhoan());
        return db.update("KhachHang",values,"taiKhoan=?",new String[]{ttcKhachHang.getTaiKhoan()});
    }
    public int deleteKhachHang(KhachHang ttcKhachHang){
        return db.delete("KhachHang","taiKhoan=?",new String[]{ttcKhachHang.getTaiKhoan()});
    }
    public boolean checkDangNhap(String taiKhoan,String matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT taiKhoan, matKhau FROM KhachHang WHERE taiKhoan =? AND matKhau =?",new String[]{taiKhoan,matKhau});
        if (c.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    @SuppressLint("Range")
    public  String getHoTen(String taiKhoan){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {"hoTen"};
        String selection = "taiKhoan =?";
        String[] selectionArgs = { taiKhoan };
        Cursor cursor = database.query(
                "KhachHang",   // Bảng
                projection,  // Các cột cần lấy ra
                selection,   // Điều kiện lấy dữ liệu
                selectionArgs, // Điều kiện lấy dữ liệu
                null,           // Không sắp xếp kết quả
                null,           // Không giới hạn kết quả
                null            // Không nhóm kết quả
        );
        String ten = null;
        if (cursor.moveToFirst()) {
            // Lấy dữ liệu của cột "Avatar"
            ten = cursor.getString(cursor.getColumnIndex("hoTen"));
        }
        cursor.close();
        return ten;
    }
}
