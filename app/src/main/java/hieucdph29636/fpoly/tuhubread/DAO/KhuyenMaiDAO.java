package hieucdph29636.fpoly.tuhubread.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;

public class KhuyenMaiDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public KhuyenMaiDAO(Context context) {
    dbHelper=new DbHelper(context);
    db=dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}
    public List<KhuyenMai> selectAll(){
        List<KhuyenMai> listPro = new ArrayList<KhuyenMai>();

        Cursor c = db.rawQuery("SELECT * FROM KhuyenMai",null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                int _id = c.getInt(0);
                String _name = c.getString(1);
                String moTa=c.getString(2);
                String ngayBatDau=c.getString(3);
                String ngayKT=c.getString(4);
                int soTiengiam=c.getInt(5);
                KhuyenMai tmpLoai = new KhuyenMai( _id, _name,moTa,ngayBatDau,ngayKT,soTiengiam);
                listPro.add( tmpLoai );
                c.moveToNext();
            }

        }else{
            Log.d("zzz", "selectAll: Không có dữ liệu");
        }

        return  listPro;
    }
    public long insertKMai(KhuyenMai objKM){
        ContentValues values=new ContentValues();
        values.put("code",objKM.getCode());
        values.put("moTaKM", objKM.getMoTaKM());
        values.put("ngayBatDau",objKM.getNgayBatDau());
        values.put("ngayKetThuc", objKM.getNgayKetThuc());
        values.put("soTienGiam",objKM.getSoTienGiam()+"");
        return db.insert("KhuyenMai",null,values);
    }
    public long updateKMai(KhuyenMai objKM){
        ContentValues values=new ContentValues();
        values.put("code",objKM.getCode());
        values.put("moTaKM", objKM.getMoTaKM());
        values.put("ngayBatDau",objKM.getNgayBatDau());
        values.put("ngayKetThuc", objKM.getNgayKetThuc());
        values.put("soTienGiam",objKM.getSoTienGiam());
        return db.update("KhuyenMai",values,"id_KhuyenMai=?",new String[]{String.valueOf(objKM.getId_KhuyenMai())});
    }
    public int deleteKMai(KhuyenMai objKM){
        String[] so=new String[]{objKM.getId_KhuyenMai()+""};
        return db.delete("KhuyenMai","id_KhuyenMai=?",so);
    }

}
