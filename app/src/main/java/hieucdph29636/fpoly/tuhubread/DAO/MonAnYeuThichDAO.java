package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.MonAnYeuThich;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class MonAnYeuThichDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public MonAnYeuThichDAO (Context context){
        dbHelper= new DbHelper(context);
        db=dbHelper.getWritableDatabase();

    }
    public void close(){dbHelper.close();}
    public List<MonAnYeuThich> selectAll(){
        List<MonAnYeuThich> listABC = new ArrayList<MonAnYeuThich>();

        Cursor c = db.rawQuery("SELECT * From MonAnYeuThich",null);

        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                int id = c.getInt(0);
                int id_khachHang = c.getInt(1);
                MonAnYeuThich ttKhachHang = new MonAnYeuThich(id,id_khachHang);
                listABC.add(ttKhachHang);
                c.moveToNext();
            }

        }else {
            Log.d("@@@","selectAll():Không có thông tin");
        }
        return listABC;
    }
    public long insertMonAnYeuThich(MonAnYeuThich ttcKhachHang){
        ContentValues values = new ContentValues();
        values.put("id_khachHang",ttcKhachHang.getId_khachHang());
        return db.insert("MonAnYeuThich",null,values);
    }
    public int updateMonAnYeuThich(MonAnYeuThich ttcKhachHang){
        ContentValues values=new ContentValues();
        values.put("id_khachHang",ttcKhachHang.getId_khachHang());
        String[] tham_so=new String[]{ttcKhachHang.getId_khachHang()+""};
        return db.update("MonAnYeuThich",values,"id_MonAnYeuThich=?",tham_so);
    }
    public int MonAnYeuThich(MonAnYeuThich ttcKhachHang){
        String[] tham_so=new String[]{ttcKhachHang.getId_khachHang()+""};
        return db.delete("ChiTietDonHang","id_ChiTietDonHang=?",tham_so);
    }
}
