package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.R;

public class DatMonActivity extends AppCompatActivity {
    ImageView btn_back,btn_tru,btn_cong;
    TextView tv_tenMon_datMon,tv_gia_datMon,tv_thanhphan_datMon,tv_tongGiaMonAn,tv_soLuong;
    CardView btn_datMon;
    DonHangDAO donHangDAO;
    ChiTietDonHangDAO chiTietDonHangDAO;
    RadioButton rdo_coRau,rdo_kRau,rdo_coOt,rdo_kOt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        donHangDAO = new DonHangDAO(this);
        chiTietDonHangDAO = new ChiTietDonHangDAO(this);
        btn_back = findViewById(R.id.btn_back_monAn);
        rdo_coRau = findViewById(R.id.rdo_coRau);
        rdo_kRau = findViewById(R.id.rdo_khongRau);
        rdo_coOt = findViewById(R.id.rdo_coOt);
        rdo_kOt = findViewById(R.id.rdo_khongOt);
        btn_datMon = findViewById(R.id.btn_datMon);
        tv_tenMon_datMon = findViewById(R.id.tv_tenMon_datMon);
        btn_tru = findViewById(R.id.btn_truSL);
        btn_cong = findViewById(R.id.btn_congSL);
        tv_gia_datMon = findViewById(R.id.tv_gia_datMon);
        tv_thanhphan_datMon = findViewById(R.id.tv_thanhphan_datMon);
        tv_soLuong = findViewById(R.id.tv_soluong_datMon);
        tv_tongGiaMonAn = findViewById(R.id.tv_tongGia_monAn);
        Bundle bundle = getIntent().getExtras();
        int id =bundle.getInt("idMon");
        String ten= bundle.getString("tenMon");
        String thanhPhan= bundle.getString("thanhPhan");
        int gia = bundle.getInt("gia");
        int trangThai = bundle.getInt("trangThai");
        int loai =bundle.getInt("id_loai");
        tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+gia+"");
        tv_tenMon_datMon.setText(ten);
        tv_gia_datMon.setText(gia+"đ");
        tv_thanhphan_datMon.setText(thanhPhan);
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(tv_soLuong.getText().toString());
                soluong++;
                tv_soLuong.setText(soluong+"");
                tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+soluong*gia+"");
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(tv_soLuong.getText().toString());
                soluong--;
                tv_soLuong.setText(soluong+"");
                tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+soluong*gia+"");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_datMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHangDAO.checkDonHang().isEmpty()){
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    String time = dateFormat.format(currentDate.getTime());
                    DonHang donHang = new DonHang();
                    donHang.setTaiKhoan(taiKhoan);
                    donHang.setThoiGianTao(time);
                    donHang.setTrangThai(0);
                    donHangDAO.insertDonHang(donHang);
                    ChiTietDonHang ctdh = new ChiTietDonHang();
                    ctdh.setId_donHang(donHangDAO.checkDonHang().get(0).getId_DonHang());
                    ctdh.setSoLuong(Integer.parseInt(tv_soLuong.getText().toString()));
                    ctdh.setGiaTien(Integer.parseInt(tv_soLuong.getText().toString())*gia);
                    ctdh.setId_monAn(id);
                    if (rdo_coRau.isChecked()){
                        ctdh.setRau(1);
                    }
                    if (rdo_kRau.isChecked()){
                        ctdh.setRau(0);
                    }
                    if (rdo_coOt.isChecked()){
                        ctdh.setOt(1);
                    }
                    if (rdo_kOt.isChecked()){
                        ctdh.setOt(0);
                    }
                    if (chiTietDonHangDAO.insertChiTietDonHang(ctdh)>0){
                        Toast.makeText(DatMonActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DatMonActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    ChiTietDonHang ctdh = new ChiTietDonHang();
                    ctdh.setId_donHang(donHangDAO.checkDonHang().get(0).getId_DonHang());
                    ctdh.setSoLuong(Integer.parseInt(tv_soLuong.getText().toString()));
                    ctdh.setGiaTien(Integer.parseInt(tv_soLuong.getText().toString())*gia);
                    ctdh.setId_monAn(id);
                    if (rdo_coRau.isChecked()){
                        ctdh.setRau(1);
                    }
                    if (rdo_kRau.isChecked()){
                        ctdh.setRau(0);
                    }
                    if (rdo_coOt.isChecked()){
                        ctdh.setOt(1);
                    }
                    if (rdo_kOt.isChecked()){
                        ctdh.setOt(0);
                    }
                    if (chiTietDonHangDAO.insertChiTietDonHang(ctdh)>0){
                        Toast.makeText(DatMonActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DatMonActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}