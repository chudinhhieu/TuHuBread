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
        db.execSQL("INSERT INTO LoaiMonAn(id_loaiDoAn,tenLoai) VALUES(0,'Bánh mì'),(1,'Sandwich'),(2,'Nước')");
        String sql_Mon="create table MonAn("+"id_MonAn integer primary key autoincrement,"+
                "tenMon text not null,"+"gia number not null,"+ "thanhPhan text,"+
                "trangThai integer not null,"+"id_loaiDoAn integer references LoaiMonAn(id_loaiDoAn),"+
                "anhMonAn blod)";
        db.execSQL(sql_Mon);
        db.execSQL("INSERT INTO MonAn\n" +
                "VALUES (1,'Bánh mì xúc xích bò băm hầm',30000,'Thành phần: Xúc xích bò băm hầm,rau mùi,hành tây',1,0,null)," +
                "(2,'Bánh mì xá xíu hongkong',35000,'Thành phần: Thịt xá xíu HongKong,rau mùi,dưa chuột,rau mix,sốt xá xíu,sốt hồng Tuhu',1,0,null)," +
                "(3,'Bánh mì thịt heo nướng phá lấu',35000,'Thành phần: Thịt heo phá lấu,Pate,rau mùi,dưa chuột,rau mix,sốt hồng Tuhu',1,0,null)," +
                "(4,'Bánh mì gà xoài cay',35000,'Thành phần: Gà cay,rau mix,xoài,sốt hồng Tuhu',1,0,null)," +
                "(5,'Bánh mì gà nướng sả',30000,'Thành phần: Gà nướng sả,Pate,rau mùi,dưa chuột,rau mix,sốt hồng Tuhu',1,0,null)," +
                "(6,'Bánh mì gà Hawaii',35000,'Thành phần: Gà Hawaii,rau mùi,hành tây tím,bắp cải tím,dứa,sốt hồng Tuhu',0,0,null)," +
                "(7,'Bánh mì bò sốt tiêu đen',45000,'Thành phần: Bò sốt tiêu đen,dưa chuột,rau mùi,hành tây,bắp cải tím,sốt hồng Tuhu',1,0,null)," +
                "(8,'Bánh mì bò sốt phô mai trứng muối',50000,'Thành phần: Bò sốt phô mai trứng muối,dưa chuột,rau mùi,hành tím muối,rau mix,sốt hồng Tuhu',1,0,null)," +
                "(9,'Bánh mì bò miếng sốt phô mai',45000,'Thành phần: Bò sốt phô mai,dưa chuột,rau mùi,hành tím muối,rau mix,sốt hồng Tuhu',1,0,null)," +
                "(10,'Sandwich phô mai sữa',30000,'Thành phần: Sandwich nóng giòn kèm theo phô mai sữa',1,1,null)," +
                "(11,'Sandwich phô mai Socola',30000,'Thành phần: Sandwich nóng giòn kèm theo phô mai socola thanh ngọt',1,1,null)," +
                "(12,'Sandwich phô mai xúc xích',30000,'Thành phần: Sandwich nóng giòn kèm theo phô mai xúc xích tiệt trùng',0,1,null)," +
                "(13,'Sandwich phô mai pate',30000,'Thành phần: Sandwich nóng giòn kèm theo phô mai pate chất lượng',1,1,null)," +
                "(14,'Sandwich phô mai jambon',30000,'Thành phần: Sandwich nóng giòn kèm theo phô mai jambon heo',1,1,null)," +
                "(15,'Trà tắc khổng lồ',15000,'Thành phần: Trà tắc mát lạnh cùng với size cực đại',1,2,null)," +
                "(16,'Trà cozy sữa',25000,'Thành phần: Trà Cozy mát lạnh cùng với vị sữa thanh mát',1,2,null)," +
                "(17,'Trà chanh khổng lồ',15000,'Thành phần: Trà chanh mát lạnh cùng với size cực đại',1,2,null)," +
                "(18,'Soda mojito',25000,'Thành phần: Soda mới lạ vị chanh',0,2,null)," +
                "(19,'Milo cafe',25000,'Sữa Milo kết hợp cùng vị cà phê mới lạ',1,2,null);");
        String sql_KhuyenMai="create table KhuyenMai("+"id_KhuyenMai integer primary key autoincrement,"+"code text not null,"+
                "moTaKM text not null,"+
                "ngayBatDau date not null,"+
                "ngayKetThuc date not null,"+
                "soTienGiam number not null)";
        db.execSQL(sql_KhuyenMai);
        db.execSQL("INSERT INTO KhuyenMai(id_KhuyenMai,code,moTaKM,ngayBatDau,ngayKetThuc,soTienGiam) VALUES(1,'code1','Khuyến mãi 1','11/02/2023','15/02/2023',15000)," +
                "(2,'code2','Khuyến mãi 2','11/02/2023','15/02/2023',25000)," +
                "(3,'code3','Khuyến mãi 3','11/02/2023','15/02/2023',35000)," +
                "(4,'code4','Khuyến mãi 4','11/02/2023','15/02/2023',45000)," +
                "(5,'code5','Khuyến mãi 5','11/02/2023','15/02/2023',55000)");
        //Khách hàng
        String sql_KhachHang = "create table KhachHang(" + "taiKhoan text primary key," +
                "hoTen text not null," + "soDienThoai text not null," +
                "matKhau text not null, " +
                "ngaySinh date not null," + "diaChi text not null," +
                "soDuTaiKhoan number)";
        db.execSQL(sql_KhachHang);
        db.execSQL("INSERT INTO KhachHang values ('chuhieu','Chu Đình Hiếu','0369066475','123456','15/11/2001','Quảng Ninh',3000)");
        db.execSQL("INSERT INTO KhachHang values ('nguyentruong','Nguyễn Xuân Trường','0123456789','123456','2/7/2002','Thái Bình',32000)");
        db.execSQL("INSERT INTO KhachHang values ('hahai','Hà Thị Ngọc Hải','0123456789','123456','6/7/2003','Hà Nội',5000)");
        db.execSQL("INSERT INTO KhachHang values ('tanlinh','Nguyễn Tấn Linh','0123456789','123456','1/1/2001','Hà Nội',10000)");
        //Đơn hàng
        String sql_DonHang = "create table DonHang(" + "id_madonhang integer primary key Autoincrement," +
                "taiKhoan text references KhachHang(taiKhoan)," + "thoiGianTao date not null," +
                "trangThai int," + "id_khuyenMai integer references KhuyenMai(id_KhuyenMai), " +
                "tongTien number not null)";
        db.execSQL(sql_DonHang);
        //Chi tiết đơn hàng
        String sql_ChiTietDonHang = "create table ChiTietDonHang(" + "id_ct integer primary key Autoincrement," +
                "id_donHang integer references DonHang(id_madonhang)," + "id_monAn integer references MonAn(id_MonAn)," +
                "soLuong int, " +  "rau int, " + "ot int, " +"giaTien number not null)";
        db.execSQL(sql_ChiTietDonHang);
        //Món ăn yêu thích
        String sql_MonAnYeuThich = "create table MonAnYeuThich(" + "id_mamonan integer references MonAn(id_MonAn)," + "taiKhoan text references KhachHang(taiKhoan))";
        db.execSQL(sql_MonAnYeuThich);
        // Nhân Viên
        String sql_NhanVien="create table NhanVien("+"taiKhoan text primary key,"+
                "hoTen text not null,"+
                "soDienThoai text not null,"+
                "matKhau text not null,"+
                "ngaySinh date not null,"+
                "quyenNhanVien int not null)";
        db.execSQL(sql_NhanVien);
        db.execSQL("INSERT INTO NhanVien values ('nhanvien','Nhân Viên','0123456789','123456','1/1/2001',2)");
        db.execSQL("INSERT INTO NhanVien values ('truongcuahang','Trưởng cửa hàng','0123456789','123456','1/1/2001',1)");
        db.execSQL("INSERT INTO NhanVien values ('admin','Chủ của hàng','0123456789','123456','1/1/2001',0)");

        // Đánh giá
        String sql_DanhGia="create table DanhGia("+"id_DanhGia integer primary key autoincrement,"+"code text not null,"+
                "taiKhoan text references KhachHang(taiKhoan),"+
                "id_monAn integer references MonAn(id_MonAn),"+
                " binhLuan text not null,"+
                "diem integer not null,"+
                "anhDanhGia blob not null)";
        db.execSQL(sql_DanhGia);
        // Đơn nạp tiền
        String sql_DonNapTien="create table DonNapTien("+"id_DonNapTien integer primary key autoincrement,"+
                "taiKhoan text references KhachHang(taiKhoan),"+
                "thoiGianTao date not null,"+
                "trangThai integer not null,"+
                "tienNap number not null,"+
                "anhHoaDon blob )";
        db.execSQL(sql_DonNapTien);
        db.execSQL("insert into DonNapTien(id_DonNapTien,taiKhoan,thoiGianTao,trangThai,tienNap,anhHoaDon)values(1,'chuhieu','25/3/2023',1,35000,null)");
        db.execSQL("insert into DonNapTien(id_DonNapTien,taiKhoan,thoiGianTao,trangThai,tienNap,anhHoaDon)values(2,'nguyentruong','20/3/2023',0,30000,null)");
        db.execSQL("insert into DonNapTien(id_DonNapTien,taiKhoan,thoiGianTao,trangThai,tienNap,anhHoaDon)values(3,'tanlinh','15/3/2023',1,70000,null)");
        db.execSQL("insert into DonNapTien(id_DonNapTien,taiKhoan,thoiGianTao,trangThai,tienNap,anhHoaDon)values(4,'hahai','20/3/2023',0,15000,null)");



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
