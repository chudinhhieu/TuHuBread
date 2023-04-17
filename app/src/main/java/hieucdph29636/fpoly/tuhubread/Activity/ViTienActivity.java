package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    ImageView anhHD,btn_back_vt;
    DonNapTien donNapTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vi_tien);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        btn_naptien = findViewById(R.id.btn_naptien);
        rcv_vitien = findViewById(R.id.rcv_vitien);
        khachHangDAO = new KhachHangDAO();
        donNapTien = new DonNapTien();
        tv_hoten_vi = findViewById(R.id.tv_hoten_vi);
        tv_soDuVi = findViewById(R.id.tv_soDuVi);
        btn_back_vt = findViewById(R.id.btn_back_vt);
        dao = new DonNapTienDAO();
        list = dao.getAllKH(taiKhoan);
        adapter = new DonNapTienKHAdapter(list,ViTienActivity.this);
        rcv_vitien.setAdapter(adapter);
        tv_hoten_vi.setText(khachHangDAO.getHoTen(taiKhoan));
        tv_soDuVi.setText(khachHangDAO.getSoDuVi(taiKhoan)+"");
        btn_back_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_naptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ViTienActivity.this);
                dialog.setContentView(R.layout.dialog_naptien);
                TextView tv_themAnh = dialog.findViewById(R.id.tv_themAnh);
                TextInputLayout edL_soTien = dialog.findViewById(R.id.edL_soTienNap);
                TextInputEditText ed_soTien = dialog.findViewById(R.id.ed_soTienNap);
                Button btn_xacNhan = dialog.findViewById(R.id.btn_xacNhanNapTien);
                anhHD = dialog.findViewById(R.id.img_anhHD);
                anhHD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, 1);
                    }
                });
                btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
                        String time = dateFormat.format(currentDate.getTime());

                        donNapTien.setMota("Nạp tiền");
                        donNapTien.setTaiKhoan(taiKhoan);
                        donNapTien.setThoiGianTao(time);
                        donNapTien.setTrangThai(0);
                        donNapTien.setTienNap(Integer.parseInt(ed_soTien.getText().toString().trim()));

                       if(dao.insert(donNapTien)){
                           Toast.makeText(ViTienActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                           list = dao.getAllKH(taiKhoan);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            anhHD.setImageURI(imageUri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                donNapTien.setAnhHoaDon(bytes);
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}