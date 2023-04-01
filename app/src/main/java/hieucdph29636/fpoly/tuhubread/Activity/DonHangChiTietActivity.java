package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.ChiTietDonHangAdapter;

public class DonHangChiTietActivity extends AppCompatActivity {
    RecyclerView rcv_ttdh;
    ChiTietDonHangAdapter adapter;
    ArrayList<ChiTietDonHang> list;
    ChiTietDonHangDAO dao;
    TextView tv_tongTamTinh;
    LinearLayout view_km;
    Button btn_km_ctdh;
    DonHangDAO donHangDAO;
    KhuyenMaiDAO khuyenMaiDAO;
    EditText ed_km_ctdh;
    TextView tv_giaKM,tv_tongTienCTHD,tv_tt_dh;
    CardView btn_thanhtoan;
    ImageView btn_back_ctdh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        tv_tongTamTinh = findViewById(R.id.tv_tongTamTinh);
        view_km = findViewById(R.id.view_km);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        tv_giaKM = findViewById(R.id.tv_giaKM);
        btn_back_ctdh = findViewById(R.id.btn_back_ctdh);
        ed_km_ctdh = findViewById(R.id.ed_km_ctdh);
        tv_tongTienCTHD = findViewById(R.id.tv_tongTienCTHD);
        tv_tt_dh = findViewById(R.id.tv_tt_dh);
        view_km.setVisibility(View.GONE);
        btn_km_ctdh = findViewById(R.id.btn_km_ctdh);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String quyen = sharedPreferences.getString("quyen","");
        Bundle bundle = getIntent().getExtras();
        int id_donHang = bundle.getInt("id_dh");
        int tt_dh = bundle.getInt("trangThai_dh");
        rcv_ttdh = findViewById(R.id.rcv_ttdh);
        dao = new ChiTietDonHangDAO(this);
        list = (ArrayList<ChiTietDonHang>) dao.selectAll(id_donHang);
        adapter = new ChiTietDonHangAdapter(list,this);
        rcv_ttdh.setAdapter(adapter);
        int tongTamTinh = dao.tinhTongGiaTienTheoIdDonHang(id_donHang);
        tv_tongTamTinh.setText(tongTamTinh+"");
        tv_tongTienCTHD.setText(tongTamTinh+"");
        khuyenMaiDAO = new KhuyenMaiDAO(this);
        donHangDAO = new DonHangDAO(this);
        if (tt_dh==0&&quyen.equalsIgnoreCase("nhanvien")){
            btn_thanhtoan.setVisibility(View.GONE);
        }
        if (tt_dh==0&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( donHangDAO.updateTrangThai(1,id_donHang)>0){
                        Toast.makeText(DonHangChiTietActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            });
        }
        if (tt_dh==3&&quyen.equalsIgnoreCase("khachhang")){
            btn_thanhtoan.setVisibility(View.VISIBLE);
            tv_tt_dh.setText("Đã nhận hàng");
            btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( donHangDAO.updateTrangThai(4,id_donHang)>0){
                        onBackPressed();
                    }
                }
            });

        }
        if (tt_dh!=0&&quyen.equalsIgnoreCase("nhanvien")){
            btn_thanhtoan.setVisibility(View.VISIBLE);
            switch (tt_dh){
                case 1:
                    tv_tt_dh.setText("Đang làm");
                    btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if( donHangDAO.updateTrangThai(2,id_donHang)>0){
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
                            if( donHangDAO.updateTrangThai(3,id_donHang)>0){
                                onBackPressed();
                            }
                        }
                    });
                    break;
                case 3:
                    btn_thanhtoan.setVisibility(View.GONE);
                    break;
                case 4:
                    btn_thanhtoan.setVisibility(View.GONE);
                    break;
                case 5:
                    btn_thanhtoan.setVisibility(View.GONE);
                    break;
            }
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
                if (khuyenMaiDAO.checkCode(ed_km_ctdh.getText().toString().trim())){
                    view_km.setVisibility(View.VISIBLE);
                    int khuyenMai = khuyenMaiDAO.getSoTienGiam(ed_km_ctdh.getText().toString().trim());
                    tv_giaKM.setText(khuyenMai+"");
                    tv_tongTienCTHD.setText(tongTamTinh-khuyenMai+"");
                }else {
                    Toast.makeText(DonHangChiTietActivity.this, "Mã khuyến mãi không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}