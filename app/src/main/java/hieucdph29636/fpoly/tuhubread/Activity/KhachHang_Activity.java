package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTienKHAdapter;
import hieucdph29636.fpoly.tuhubread.adapter.KhachHangAdapter;

public class KhachHang_Activity extends AppCompatActivity {
    RecyclerView rcv_kh;
    KhachHangDAO khachHangDAO;
    KhachHangAdapter khachHangAdapter;
    ArrayList<KhachHang> listKh;
    ImageView btn_search,btn_back_qlkh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        rcv_kh=findViewById(R.id.rcv_Khang);
        btn_back_qlkh=findViewById(R.id.btn_back_qlkh);
        khachHangDAO=new KhachHangDAO();
        listKh= (ArrayList<KhachHang>) khachHangDAO.getAll();
        khachHangAdapter=new KhachHangAdapter(this,listKh);
        rcv_kh.setAdapter(khachHangAdapter);
        btn_search = findViewById(R.id.btn_search_kh);
        btn_back_qlkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(KhachHang_Activity.this);
                dialog.setContentView(R.layout.activity_search_share);
                TextInputLayout til = dialog.findViewById(R.id.edL_search);
                TextInputEditText tied = dialog.findViewById(R.id.ed_search);
                TextView btn_xacnhan = dialog.findViewById(R.id.btn_xacNhan_searchKH);
                tied.setHint("Nhập số điện thoại");
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tied.getText().toString().isEmpty()){
                            til.setError("Không được để trống");
                            return;
                        } else {
                            til.setErrorEnabled(false);
                        }
                        listKh =khachHangDAO.getBySoDienThoai(tied.getText().toString().trim());
                        khachHangAdapter = new KhachHangAdapter(KhachHang_Activity.this,listKh);
                        rcv_kh.setAdapter(khachHangAdapter);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }
}