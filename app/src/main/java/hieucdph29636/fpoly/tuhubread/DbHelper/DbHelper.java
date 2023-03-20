package hieucdph29636.fpoly.tuhubread.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="Duan1";
    public static final int DB_VERSION=1;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_Loai="create table LoaiMonAn("+"id_loaiDoAn integer primary key Autoincrement,"+"tenLoai text not null)";
        db.execSQL(sql_Loai);
        String sql_Mon="create table MonAn("+"id_MonAn integer primary key autoincrement,"+
                "tenMon text not null,"+"gia number not null,"+
                "moTa text not null,"+"thanhPhan text not null,"+
                "trangThai text not null,"+"id_loaiDoAn integer references MonAn(id_MonAn),"+
                "anhMonAn blob not null)";
        db.execSQL(sql_Mon);
        String sql_KhuyenMai="create table KhuyenMai("+"id_KhuyenMai integer primary key autoincrement,"+"code text not null,"+
                "moTaKM text not null,"+
                "ngayBatDau date not null,"+
                "ngayKetThuc date not null,"+
                "soTienGiam number not null)";
        db.execSQL(sql_KhuyenMai);

        String sql_NhanVien="create table NhanVien("+"id_NhanVien integer primary key autoincrement,"+"code text not null,"+
                "hoTen text not null,"+
                "soDienThoai text not null,"+
                "taiKhoan text not null,"+
                "matKhau text not null,"+
                "ngaySinh date not null,"+
                "quyenNhanVien text not null)";
        db.execSQL(sql_NhanVien);

        String sql_DanhGia="create table DanhGia("+"id_DanhGia integer primary key autoincrement,"+"code text not null,"+
                "id_khachHang integer references KhachHang(id_KhachHang),"+
                "id_monAn integer references MonAn(id_MonAn),"+
               " binhLuan text not null,"+
                "diem integer not null,"+
                "anhDanhGia blob not null)";
        db.execSQL(sql_DanhGia);

        String sql_DonNapTien="create table DonNapTien("+"id_DonNapTien integer primary key autoincrement,"+"code text not null,"+
                "id_khachHang integer references KhachHang(id_KhachHang),"+
                "thoiGianTao date not null,"+
                "trangThai integer not null,"+
                "tienNap number not null,"+
                "anhHoaDon blob not null)";
        db.execSQL(sql_DonNapTien);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      String sql_Loai="drop table if exists LoaiMonAn";
      db.execSQL(sql_Loai);
      String sql_Mon="drop table if exists MonAn";
      db.execSQL(sql_Mon);
      String sql_KM="drop table if exists KhuyenMai";
      db.execSQL(sql_KM);
      String sql_NhanVien = "drop table if exists NhanVien";
      db.execSQL(sql_NhanVien);
      String sql_DanhGia = "drop table if exists DanhGia";
      db.execSQL(sql_DanhGia);
      String sql_DonNapTien = "drop table if exists DonNapTien";
      db.execSQL(sql_DonNapTien);

    }
}
