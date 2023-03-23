package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.LoaiMon;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class LoaiMonDAO {
    SQLiteDatabase db;
DbHelper dbHelper;

    public LoaiMonDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();

    }
    public void close(){dbHelper.close();}
    public List<LoaiMon> selectAll(){
        List<LoaiMon> listPro = new ArrayList<LoaiMon>();

        Cursor c = db.rawQuery("SELECT * FROM LoaiMon ",null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);

                LoaiMon tmpLoai = new LoaiMon( _id, _name);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }
    public long insertLoai(LoaiMon obj) {
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return db.insert("LoaiMon",null,values);
    }
    public int updateLoai(LoaiMon obj) {
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        String[] tham_so=new String[]{obj.getId_loai()+""};
        return db.update("LoaiMon",values,"id_Loai=?",tham_so);
    }
    public int deleteLoai(LoaiMon obj){
        String[] tham_so=new String[]{obj.getId_loai()+""};
        return db.delete("LoaiMon","id_Loai=?",tham_so);
    }
}
