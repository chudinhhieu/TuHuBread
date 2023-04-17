package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnYeuThichDAO;
import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.DTO.MonAnYeuThich;
import hieucdph29636.fpoly.tuhubread.R;

public class DatMonActivity extends AppCompatActivity {
    MonAnYeuThichDAO monAnYeuThichDAO;
    ImageView btn_back,btn_tru,btn_cong,img_datMon , btnMonAnyt,star;
    TextView tv_tenMon_datMon,tv_gia_datMon,tv_thanhphan_datMon,tv_tongGiaMonAn,tv_soLuong;
    CardView btn_datMon;
    DonHangDAO donHangDAO;
    ChiTietDonHangDAO chiTietDonHangDAO;
    RadioButton rdo_coRau,rdo_kRau,rdo_coOt,rdo_kOt;
    RelativeLayout view6,view2,view3;
    MonAnDAO monAnDAO;
    NhanVienDAO nhanVienDAO;
    int soluong = 1;
    int idDH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        btnMonAnyt = findViewById(R.id.btn_monAnYT);
        star = findViewById(R.id.star);
        donHangDAO = new DonHangDAO();
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        if(quyen.equalsIgnoreCase("khachhang")){
            btnMonAnyt.setVisibility(View.VISIBLE);
        }else {
            btnMonAnyt.setVisibility(View.GONE);
        }
        try {
            idDH = donHangDAO.checkDonHang().get(0).getId_DonHang();
        }catch (Exception e){

        }
        chiTietDonHangDAO = new ChiTietDonHangDAO();
        monAnYeuThichDAO = new MonAnYeuThichDAO();
        monAnDAO = new MonAnDAO();
        btn_back = findViewById(R.id.btn_back_monAn);
        img_datMon = findViewById(R.id.img_datMon);
        rdo_coRau = findViewById(R.id.rdo_coRau);
        view6 = findViewById(R.id.view6);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
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
        Bitmap bitmap = BitmapFactory.decodeByteArray(monAnDAO.layAnhTheoID(id), 0, monAnDAO.layAnhTheoID(id).length);
        img_datMon.setImageBitmap(bitmap);
        tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+gia+"");
        tv_tenMon_datMon.setText(ten);
        tv_gia_datMon.setText(gia+"đ");
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatMonActivity.this,DanhGia_BinhLuan_Hien.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("id_ma",id);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        if (monAnYeuThichDAO.kiemTraThichMonAn(taiKhoan,id)){
            btnMonAnyt.setImageResource(R.drawable.ic_baseline_favorite_24_green);
        }
        btnMonAnyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAnYeuThich monAnYeuThich = new MonAnYeuThich();
                monAnYeuThich.setId_MonAn(id);
                monAnYeuThich.setTaiKhoan(taiKhoan);
                if(monAnYeuThichDAO.insert(monAnYeuThich)){
                    btnMonAnyt.setImageResource(R.drawable.ic_baseline_favorite_24_green);
                    Toast.makeText(DatMonActivity.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DatMonActivity.this, "Đã tồn tại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_thanhphan_datMon.setText(thanhPhan);
        if (loai!=0){
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);
        }

        if (quyen.equalsIgnoreCase("nhanvien")){
            view6.setVisibility(View.GONE);
            if (trangThai==1){
                tv_tongGiaMonAn.setText("Ngừng bán");
            }else {
                tv_tongGiaMonAn.setText("Mở bán");
            }
        }
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong==10){
                    return;
                }
                soluong = Integer.parseInt(tv_soLuong.getText().toString());
                soluong++;
                tv_soLuong.setText(soluong+"");
                tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+soluong*gia+"");
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong==1){
                    return;
                }
                soluong = Integer.parseInt(tv_soLuong.getText().toString());
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
                if (quyen.equalsIgnoreCase("khachhang")){
                    if (donHangDAO.checkDonHang().isEmpty()){
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
                        String time = dateFormat.format(currentDate.getTime());
                        DonHang donHang = new DonHang();
                        donHang.setTaiKhoan(taiKhoan);
                        donHang.setThoiGianTao(time);
                        donHang.setTrangThai(0);
                        donHang.setTongTien(0);
                        donHangDAO.insert(donHang);
                        idDH = donHangDAO.checkDonHang().get(0).getId_DonHang();
                        ChiTietDonHang ctdh = new ChiTietDonHang();
                        ctdh.setId_donHang(idDH);
                        ctdh.setSoLuong(Integer.parseInt(tv_soLuong.getText().toString()));
                        ctdh.setGiaTien(Integer.parseInt(tv_soLuong.getText().toString())*gia);
                        ctdh.setId_monAn(id);
                       if (loai==0){
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
                       }
                       if (!rdo_kOt.isChecked()&&!rdo_coOt.isChecked()){
                           Toast.makeText(DatMonActivity.this, "Vui lòng chọn ớt", Toast.LENGTH_SHORT).show();
                            return;
                       }
                        if (!rdo_kRau.isChecked()&&!rdo_coRau.isChecked()){
                            Toast.makeText(DatMonActivity.this, "Vui lòng chọn rau", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (chiTietDonHangDAO.insert(ctdh)){
                            Toast.makeText(DatMonActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                            onBackPressed();
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
                        if (!rdo_kOt.isChecked()&&!rdo_coOt.isChecked()){
                            Toast.makeText(DatMonActivity.this, "Vui lòng chọn ớt", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!rdo_kRau.isChecked()&&!rdo_coRau.isChecked()){
                            Toast.makeText(DatMonActivity.this, "Vui lòng chọn rau", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (chiTietDonHangDAO.insert(ctdh)){
                            Toast.makeText(DatMonActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DatMonActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    if (trangThai==1){
                        monAnDAO.updateTTMon(0,id);
                        onBackPressed();
                    }else {
                        monAnDAO.updateTTMon(1,id);
                        onBackPressed();
                    }
                }
            }
        });
    }
}