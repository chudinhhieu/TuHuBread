package hieucdph29636.fpoly.tuhubread.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Duan1";
    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Loại món ăn
        String sql_Loai = "create table LoaiMonAn(" + "id_loaiDoAn integer primary key Autoincrement," + "tenLoai text not null)";
        db.execSQL(sql_Loai);
        //Món ăn
        String sql_Mon = "create table MonAn(" + "id_MonAn integer primary key autoincrement," +
                "tenMon text not null," + "gia number not null," +
                "moTa text not null," + "thanhPhan text not null," +
                "trangThai text not null," + "id_loaiDoAn integer references LoaiMonAn(id_loaiDoAn)," +
                "anhMonAn blob not null)";
        db.execSQL(sql_Mon);
        //Khuyến mãi
        String sql_KhuyenMai = "create table KhuyenMai(" + "id_KhuyenMai integer primary key autoincrement," + "code text not null," +
                "moTaKM text not null," +
                "ngayBatDau date not null," +
                "ngayKetThuc date not null," +
                "anhKhuyenMai blob not null," +
                "soTienGiam number not null)";
        db.execSQL(sql_KhuyenMai);
        //Khách hàng
        String sql_KhachHang = "create table KhachHang(" + "id_makhachhang integer primary key Autoincrement," +
                "hoTen text not null," + "soDienThoai text not null," +
                "taiKhoan text not null," + "matKhau text not null, " +
                "ngaySinh date not null," + "diaChi text not null," +
                "soDuTaiKhoan number not null)";
        db.execSQL(sql_KhachHang);
        //Đơn hàng
        String sql_DonHang = "create table DonHang(" + "id_madonhang integer primary key Autoincrement," +
                "id_khachHang integer references KhachHang(id_makhachhang)," + "thoiGianTao date not null," +
                "trangThai int," + "id_khuyenMai integer references KhuyenMai(id_KhuyenMai), " +
                "tongTien number not null)";
        db.execSQL(sql_DonHang);
        //Chi tiết đơn hàng
        String sql_ChiTietDonHang = "create table ChiTietDonHang(" + "id_ct integer primary key Autoincrement," +
                "id_donHang integer references DonHang," + "id_monAn integer references MonAn," +
                "soLuong int, " + "giaTien number not null)";
        db.execSQL(sql_ChiTietDonHang);
        //Món ăn yêu thích
        String sql_MonAnYeuThich = "create table MonAnYeuThich(" + "id_mamonan integer references MonAn," + "id_khachHang integer references KhachHang)";
        db.execSQL(sql_MonAnYeuThich);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_Loai = "drop table if exists LoaiMonAn";
        db.execSQL(sql_Loai);
        String sql_Mon = "drop table if exists MonAn";
        db.execSQL(sql_Mon);
        String sql_KM = "drop table if exists KhuyenMai";
        db.execSQL(sql_KM);
        String sql_KhachHang = "drop table if exists KhachHang";
        db.execSQL(sql_KhachHang);
        String sql_DonHang = "drop table if exists DonHang";
        db.execSQL(sql_DonHang);
        String sql_ChiTietDonHang = "drop table if exists ChiTietDonHang";
        db.execSQL(sql_ChiTietDonHang);
        String sql_MonAnYeuThich = "drop table if exists MonAnYeuThich";
        db.execSQL(sql_MonAnYeuThich);
    }
}
