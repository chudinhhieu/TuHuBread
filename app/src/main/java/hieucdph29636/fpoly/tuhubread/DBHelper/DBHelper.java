package hieucdph29636.fpoly.tuhubread.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME= "Duan1";
    public static final int DB_VERISON=1;
    public DBHelper(@Nullable Context context){
        super(context,DB_NAME,null,DB_VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_KhachHang="create table KhachHang("+"id_makhachhang integer primary key Autoincrement,"+
                "hoTen text not null,"+"soDienThoai text not null,"+
                "taiKhoan text not null,"+"matKhau text not null, "+
                "ngaySinh date not null,"+"diaChi text not null,"+
                "soDuTaiKhoan number not null)";
        db.execSQL(sql_KhachHang);

        String sql_DonHang="create table DonHang("+"id_madonhang integer primary key Autoincrement,"+
                "id_khachHang integer references KhachHang,"+"thoiGianTao date not null,"+
                "trangThai int,"+"id_khuyenMai integer references KhuyenMai, "+
                "tongTien number not null)";
        db.execSQL(sql_DonHang);

        String sql_ChiTietDonHang="create table ChiTietDonHang("+"id_id integer primary key Autoincrement,"+
                "id_donHang integer references DonHang,"+"id_monAn integer references MonAn,"+
                "soLuong int, "+"giaTien number not null)";
        db.execSQL(sql_ChiTietDonHang);

        String sql_MonAnYeuThich="create table MonAnYeuThich("+"id_mamonan integer references MonAn,"+"id_khachHang integer references KhachHang)";
        db.execSQL(sql_MonAnYeuThich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_KhachHang="drop table if exists KhachHang";
        db.execSQL(sql_KhachHang);
        String sql_DonHang="drop table if exists DonHang";
        db.execSQL(sql_DonHang);
        String sql_ChiTietDonHang="drop table if exists ChiTietDonHang";
        db.execSQL(sql_ChiTietDonHang);
        String sql_MonAnYeuThich="drop table if exists MonAnYeuThich";
        db.execSQL(sql_MonAnYeuThich);
    }
}
