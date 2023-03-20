package hieucdph29636.fpoly.tuhubread.DAO;

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
                int id = c.getInt(0);
                String hoTen = c.getString(1);
                String soDienThoai = c.getString(2);
                String taiKhoan = c.getString(3);
                String matKhau = c.getString(4);
                String ngaySinh = c.getString(5);
                String diaChi = c.getString(6);
                int soDuTaiKhoan = c.getInt(7);
                KhachHang ttKhachHang = new KhachHang(id,hoTen,soDienThoai,taiKhoan,matKhau,ngaySinh,diaChi,soDuTaiKhoan);
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
        values.put("taiKhoann",ttcKhachHang.getTaiKhoan());
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
        values.put("taiKhoann",ttcKhachHang.getTaiKhoan());
        values.put("matKhau",ttcKhachHang.getMatKhau());
        values.put("ngaySinh",ttcKhachHang.getNgaySinh());
        values.put("diaChi",ttcKhachHang.getDiaChi());
        values.put("soDuTaiKhoan",ttcKhachHang.getSoDuTaiKhoan());
        String[] so=new String[]{ttcKhachHang.getId_KhachHang()+""};
        return db.update("KhachHang",values,"id_KhachHang=?",so);
    }
    public int deleteKhachHang(KhachHang ttcKhachHang){
        String[] so=new String[]{ttcKhachHang.getId_KhachHang()+""};
        return db.delete("KhachHang","id_KhachHang=?",so);
    }

}
