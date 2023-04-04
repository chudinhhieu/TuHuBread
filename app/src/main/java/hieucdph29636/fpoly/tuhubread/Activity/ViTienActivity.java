package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTienKHAdapter;

public class ViTienActivity extends AppCompatActivity {
    LinearLayout btn_naptien;
    DonNapTienDAO dao;
    RecyclerView rcv_vitien;
    ArrayList<DonNapTien> list;
    DonNapTienKHAdapter adapter;
    TextView tv_soDuVi,tv_hoten_vi;
    KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vi_tien);
        btn_naptien = findViewById(R.id.btn_naptien);
        rcv_vitien = findViewById(R.id.rcv_vitien);
        khachHangDAO = new KhachHangDAO();
        tv_hoten_vi = findViewById(R.id.tv_hoten_vi);
        tv_soDuVi = findViewById(R.id.tv_soDuVi);
        dao = new DonNapTienDAO();
        list = dao.getAll();
        adapter = new DonNapTienKHAdapter(list,ViTienActivity.this);
        rcv_vitien.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        tv_hoten_vi.setText(khachHangDAO.getHoTen(taiKhoan));
        tv_soDuVi.setText(khachHangDAO.getSoDuVi(taiKhoan)+"");
        btn_naptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ViTienActivity.this);
                dialog.setContentView(R.layout.dialog_naptien);
                TextView tv_themAnh = dialog.findViewById(R.id.tv_themAnh);
                TextInputLayout edL_soTien = dialog.findViewById(R.id.edL_soTienNap);
                TextInputEditText ed_soTien = dialog.findViewById(R.id.ed_soTienNap);
                Button btn_xacNhan = dialog.findViewById(R.id.btn_xacNhanNapTien);
                ImageView anhHD = dialog.findViewById(R.id.img_anhHD);
                anhHD.setVisibility(View.GONE);

                btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        String time = dateFormat.format(currentDate.getTime());
                        DonNapTien donNapTien = new DonNapTien();
                        donNapTien.setMota("Nạp tiền");
                        donNapTien.setTaiKhoan(taiKhoan);
                        donNapTien.setThoiGianTao(time);
                        donNapTien.setTrangThai(0);
                        donNapTien.setTienNap(Integer.parseInt(ed_soTien.getText().toString().trim()));
                       if(dao.insert(donNapTien)){
                           Toast.makeText(ViTienActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                           list = dao.getAll();
                           adapter = new DonNapTienKHAdapter(list,ViTienActivity.this);
                           rcv_vitien.setAdapter(adapter);
                           dialog.dismiss();
                       }else {
                           Toast.makeText(ViTienActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = dao.getAll();
        adapter = new DonNapTienKHAdapter(list,ViTienActivity.this);
        rcv_vitien.setAdapter(adapter);
    }
}