package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class MonAnDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public MonAnDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public void close(){dbHelper.close();}
    public ArrayList<MonAn> selectAll(){
        ArrayList<MonAn> listPro = new ArrayList<MonAn>();

        Cursor c = db.rawQuery("SELECT * FROM MonAn ",null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);
                int _gia=c.getInt(2);

                String _thanhphan=c.getString(3);
                int _trangThai=c.getInt(4);
                int id_LoaiDoAn=c.getInt(5);
                int _anh=c.getInt(6);
                MonAn tmpLoai = new MonAn( _id, _name,_gia,_thanhphan,_trangThai,id_LoaiDoAn,_anh);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }
    public ArrayList<MonAn> selectAllKH(int trangThai){
        ArrayList<MonAn> listPro = new ArrayList<MonAn>();

        Cursor c = db.rawQuery("SELECT * FROM MonAn where trangThai=?  ",new String[]{String.valueOf(trangThai)});

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);
                int _gia=c.getInt(2);

                String _thanhphan=c.getString(3);
                int _trangThai=c.getInt(4);
                int id_LoaiDoAn=c.getInt(5);
                int _anh=c.getInt(6);
                MonAn tmpLoai = new MonAn( _id, _name,_gia,_thanhphan,_trangThai,id_LoaiDoAn,_anh);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }
    public long insertMon(MonAn objMon){
        ContentValues values=new ContentValues();
        values.put("tenMon", objMon.getTenMon());
        values.put("gia",objMon.getGia()+"");
        values.put("thanhPhan",objMon.getThanhPhan());
        values.put("trangThai",objMon.getTrangThai());
        values.put("id_LoaiDoAn",objMon.getId_LoaiDoAn()+"");
        values.put("anhDoAn", objMon.getAnhMonAn()+"");
        return db.insert("MonAn",null,values);
    }
    public int updateMon(MonAn objMon){
        ContentValues values=new ContentValues();
        values.put("tenMon", objMon.getTenMon());
        values.put("gia",objMon.getGia()+"");
        values.put("thanhPhan",objMon.getThanhPhan());
        values.put("trangThai",objMon.getTrangThai());
        values.put("id_LoaiDoAn",objMon.getId_LoaiDoAn()+"");
        values.put("anhDoAn", objMon.getAnhMonAn()+"");
        String[] tham_so=new String[]{objMon.getId_MonAn()+""};
        return db.update("MonAn",values,"id_MonAn=?",tham_so);
    }
    public int updateTTMon(int tt,int id){
        ContentValues values=new ContentValues();
        values.put("trangThai",tt);
        return db.update("MonAn",values,"id_MonAn=?",new String[]{String.valueOf(id)});
    }
    public int deleteMon(MonAn objMon){
        String[] tham_so=new String[]{objMon.getId_MonAn()+""};
        return db.delete("MonAn","id_MonAn=?",tham_so);}

    public ArrayList<MonAn> chonTheoLoai(int id_loaiDoAn,int tt){
        ArrayList<MonAn> listPro = new ArrayList<MonAn>();

        Cursor c = db.rawQuery("SELECT * FROM MonAn where id_loaiDoAn=? and trangThai=? ",new String[]{String.valueOf(id_loaiDoAn), String.valueOf(tt)});

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);
                int _gia=c.getInt(2);
                String _thanhphan=c.getString(3);
                int _trangThai=c.getInt(4);
                int id_LoaiDoAn=c.getInt(5);
                int _anh=c.getInt(6);
                MonAn tmpLoai = new MonAn( _id, _name,_gia,_thanhphan,_trangThai,id_LoaiDoAn,_anh);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }

}
