package hieucdph29636.fpoly.tuhubread.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DbHelper.DbHelper;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;


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
    public boolean checkCode(String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT code FROM KhuyenMai WHERE code =? ",new String[]{code});
        if (c.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    @SuppressLint("Range")
    public  int getSoTienGiam(String code){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {"soTienGiam"};
        String selection = "code =?";
        String[] selectionArgs = { code };
        Cursor cursor = database.query(
                "KhuyenMai",   // Bảng
                projection,  // Các cột cần lấy ra
                selection,   // Điều kiện lấy dữ liệu
                selectionArgs, // Điều kiện lấy dữ liệu
                null,           // Không sắp xếp kết quả
                null,           // Không giới hạn kết quả
                null            // Không nhóm kết quả
        );
        int soTienGiam = 0;
        if (cursor.moveToFirst()) {
            // Lấy dữ liệu của cột "Avatar"
            soTienGiam = cursor.getInt(cursor.getColumnIndex("soTienGiam"));
        }
        cursor.close();
        return soTienGiam;
    }
}
