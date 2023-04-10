package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.SetNotification;
import hieucdph29636.fpoly.tuhubread.adapter.ChiTietDonHangAdapter;

public class DonHangChiTietActivity extends AppCompatActivity {
    RecyclerView rcv_ttdh;
    ChiTietDonHangAdapter adapter;
    ArrayList<ChiTietDonHang> list;
    ChiTietDonHangDAO dao;
    TextView tv_tongTamTinh;
    LinearLayout view_km,ln_km;
    SetNotification notification;
    Button btn_km_ctdh;
    DonHangDAO donHangDAO;
    KhuyenMaiDAO khuyenMaiDAO;
    EditText ed_km_ctdh;
    TextView tv_giaKM,tv_tongTienCTHD,tv_tt_dh,tv_diachi_ctdh;
    CardView btn_thanhtoan;
    ImageView btn_back_ctdh;
    KhachHangDAO khachHangDAO;
    KhuyenMai km;
    int giaSauKM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        tv_tongTamTinh = findViewById(R.id.tv_tongTamTinh);
        view_km = findViewById(R.id.view_km);
        notification = new SetNotification(this);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        tv_giaKM = findViewById(R.id.tv_giaKM);
        tv_diachi_ctdh = findViewById(R.id.tv_diachi_ctdh);
        ln_km = findViewById(R.id.ln_km);
        NotificationManagerCompat.from(this).areNotificationsEnabled();
        khachHangDAO = new KhachHangDAO();
        btn_back_ctdh = findViewById(R.id.btn_back_ctdh);
        ed_km_ctdh = findViewById(R.id.ed_km_ctdh);
        tv_tongTienCTHD = findViewById(R.id.tv_tongTienCTHD);
        tv_tt_dh = findViewById(R.id.tv_tt_dh);
        view_km.setVisibility(View.GONE);
        btn_km_ctdh = findViewById(R.id.btn_km_ctdh);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        Bundle bundle = getIntent().getExtras();
        int id_donHang = bundle.getInt("id_dh");
        int tt_dh = bundle.getInt("trangThai_dh");
        String tk = bundle.getString("tk");
        tv_diachi_ctdh.setText(khachHangDAO.getDiaChi(tk));
        rcv_ttdh = findViewById(R.id.rcv_ttdh);
        dao = new ChiTietDonHangDAO();
        list = (ArrayList<ChiTietDonHang>) dao.getAll(id_donHang);
        adapter = new ChiTietDonHangAdapter(list,this);
        rcv_ttdh.setAdapter(adapter);
        int tongTamTinh = dao.tinhTongGiaTien(id_donHang);
        donHangDAO = new DonHangDAO();
        tv_tongTamTinh.setText(tongTamTinh+"");
        tv_tongTienCTHD.setText(donHangDAO.getByID(id_donHang).get(0).getTongTien()+"");
        khuyenMaiDAO = new KhuyenMaiDAO();
        if (tt_dh!=0){
            ln_km.setVisibility(View.GONE);
        }
        if ((tt_dh!=0&&tt_dh!=3&&tt_dh!=1)&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setVisibility(View.GONE);
        }else if(tt_dh==1){
            tv_tt_dh.setText("Hủy");
            btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notification.sendNotification("Đơn hàng của bạn đã bị hủy");
                    donHangDAO.updateTrangThai(id_donHang,5);
                    khachHangDAO.updateTien(donHangDAO.getByID(id_donHang).get(0).getTongTien()+khachHangDAO.getSoDuVi(taiKhoan),taiKhoan);
                    onBackPressed();
                }
            });
        }
        if (tt_dh==0&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (khachHangDAO.getSoDuVi(taiKhoan)<Integer.parseInt(tv_tongTienCTHD.getText().toString())){
                        Dialog dialog = new Dialog(DonHangChiTietActivity.this);
                        dialog.setContentView(R.layout.dialog_thatbai);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },1000);
                        return;
                    }
                    if (km==null){
                        if (donHangDAO.updateDonHangKKM(id_donHang, 1, tongTamTinh)) {
                            khachHangDAO.updateTien(khachHangDAO.getSoDuVi(taiKhoan) - Integer.parseInt(tv_tongTienCTHD.getText().toString()), taiKhoan);
                            Toast.makeText(DonHangChiTietActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            notification.sendNotification("Đơn hàng của bạn đang chờ xác nhận");
                            onBackPressed();
                        }
                    }else {
                        if (donHangDAO.updateDonHang(id_donHang, 1, km.getId_KhuyenMai(), giaSauKM)) {
                            khachHangDAO.updateTien(khachHangDAO.getSoDuVi(taiKhoan) - Integer.parseInt(tv_tongTienCTHD.getText().toString()), taiKhoan);
                            Toast.makeText(DonHangChiTietActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            notification.sendNotification("Đơn hàng của bạn đang chờ xác nhận");
                            onBackPressed();
                        }
                    }
                }
            });
        }else if (tt_dh==2&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setVisibility(View.GONE);
        } else if (tt_dh==3&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setVisibility(View.VISIBLE);
            tv_tt_dh.setText("Đã nhận hàng");
            btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( donHangDAO.updateTrangThai(id_donHang,4)){
                        notification.sendNotification("Đơn hàng của bạn đã hoàn thành");
                        onBackPressed();
                    }
                }
            });

        }
        if (tt_dh!=0&&quyen.equalsIgnoreCase("nhanvien")){
            ln_km.setVisibility(View.GONE);
            switch (tt_dh){
                case 1:
                    tv_tt_dh.setText("Đang làm");
                    btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if( donHangDAO.updateTrangThai(id_donHang,2)){
                                onBackPressed();
                            }
                        }
                    });
                    break;
                case 2:
                    tv_tt_dh.setText("Đang giao hàng");
                    btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if( donHangDAO.updateTrangThai(id_donHang,3)){
                                onBackPressed();
                            }
                        }
                    });
                    break;
                case 3:
                    tv_tt_dh.setText("Giao hàng thất bại");
                    btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if( donHangDAO.updateTrangThai(id_donHang,6)){
                                onBackPressed();
                            }
                        }
                    });
                    break;
                case 4:
                    btn_thanhtoan.setVisibility(View.GONE);
                    break;
                case 5:
                    btn_thanhtoan.setVisibility(View.GONE);
                    break;
            }
        }else if (tt_dh==0&&quyen.equalsIgnoreCase("nhanvien")){
            btn_thanhtoan.setVisibility(View.GONE);
            ln_km.setVisibility(View.GONE);
        }

        btn_back_ctdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_km_ctdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!khuyenMaiDAO.checkCode(ed_km_ctdh.getText().toString().trim()).isEmpty()){
                    km = khuyenMaiDAO.checkCode(ed_km_ctdh.getText().toString()).get(0);
                    view_km.setVisibility(View.VISIBLE);
                    int khuyenMai = khuyenMaiDAO.checkCode(ed_km_ctdh.getText().toString().trim()).get(0).getSoTienGiam();
                    tv_giaKM.setText(khuyenMai+"");
                    giaSauKM = tongTamTinh-khuyenMai;
                    if (giaSauKM<0){
                        tv_tongTienCTHD.setText(0+"");

                    }else {
                    tv_tongTienCTHD.setText(giaSauKM+"");
                    }
                }else {
                    view_km.setVisibility(View.GONE);
                    Toast.makeText(DonHangChiTietActivity.this, "Mã khuyến mãi không tồn tại!", Toast.LENGTH_SHORT).show();
                    tv_tongTienCTHD.setText(tongTamTinh+"");
                }
            }
        });

    }
}