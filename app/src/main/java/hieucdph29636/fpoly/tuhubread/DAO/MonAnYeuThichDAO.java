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
                String taiKhoan = c.getString(1);
                MonAnYeuThich ttKhachHang = new MonAnYeuThich(id,taiKhoan);
                listABC.add(ttKhachHang);
                c.moveToNext();
            }

        }else {
            Log.d("@@@","selectAll():Không có thông tin");
        }
        return listABC;
    }
    public long insertMonAnYeuThich(MonAnYeuThich monAnYeuThich){
        ContentValues values = new ContentValues();
        values.put("taiKhoan",monAnYeuThich.getTaiKhoan());
        return db.insert("MonAnYeuThich",null,values);
    }
    public int updateMonAnYeuThich(MonAnYeuThich monAnYeuThich){
        ContentValues values=new ContentValues();
        values.put("taiKhoan",monAnYeuThich.getTaiKhoan());
        String[] tham_so=new String[]{monAnYeuThich.getTaiKhoan()+""};
        return db.update("MonAnYeuThich",values,"id_mamonan=?",tham_so);
    }
    public int MonAnYeuThich(MonAnYeuThich monAnYeuThich){
        String[] tham_so=new String[]{monAnYeuThich.getId_MonAn()+""};
        return db.delete("MonAnYeuThich","id_mamonan=?",tham_so);
    }
}
