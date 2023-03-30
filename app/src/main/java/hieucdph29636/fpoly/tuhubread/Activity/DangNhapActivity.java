package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.R;

public class DangNhapActivity extends AppCompatActivity {
    TextInputLayout edL_taiKhoan, edL_matKhau;
    TextInputEditText ed_taiKhoan, ed_matKhau;
    Button btn_dangNhap;
    TextView btn_dangKy;
    CheckBox chk_nhoTaiKhoan;
    RadioButton rdokhachHang,rdonhanVien;
    KhachHangDAO khachHangDAO;
    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edL_taiKhoan = findViewById(R.id.edL_taiKhoan);
        edL_matKhau = findViewById(R.id.edL_matKhau);
        ed_taiKhoan = findViewById(R.id.ed_taiKhoan);
        ed_matKhau = findViewById(R.id.ed_matKhau);
        rdokhachHang = findViewById(R.id.rdokhachHang);
        rdonhanVien = findViewById(R.id.rdoNhanVien);
        btn_dangNhap = findViewById(R.id.btn_dangNhap);
        btn_dangKy = findViewById(R.id.btn_dangKy);
        chk_nhoTaiKhoan = findViewById(R.id.chk_nhoTaiKhoan);
        khachHangDAO = new KhachHangDAO(this);
        nhanVienDAO = new NhanVienDAO(this);

        rdonhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_dangKy.setVisibility(View.GONE);

            }
        });
        rdokhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_dangKy.setVisibility(View.VISIBLE);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("nhotaiKhoan", MODE_PRIVATE);
        chk_nhoTaiKhoan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isChecked) {
                    editor.putString("username", ed_taiKhoan.getText().toString());
                    editor.putString("password", ed_matKhau.getText().toString());
                } else {
                    editor.remove("username");
                    editor.remove("password");
                }
                editor.apply();
            }
        });
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (!username.isEmpty() && !password.isEmpty()) {
            ed_taiKhoan.setText(username);
            ed_matKhau.setText(password);
        } else {
            // Không có tài khoản và mật khẩu được lưu trữ.
        }
        doiMauButton();
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTraTrong()) {
                    return;
                }
                if (kiemTraKyTu()){
                    return;
                }
                if (!rdonhanVien.isChecked()&&!rdokhachHang.isChecked()){
                    Toast.makeText(DangNhapActivity.this, "Vui lòng chọn quyền!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rdokhachHang.isChecked()){
                    if(khachHangDAO.checkDangNhap(ed_taiKhoan.getText().toString().trim(),ed_matKhau.getText().toString().trim())){
                        SharedPreferences shareQuyen = getSharedPreferences("luuDangNhap",MODE_PRIVATE);
                        SharedPreferences.Editor edit = shareQuyen.edit();
                        edit.putString("TK",ed_taiKhoan.getText().toString().trim());
                        edit.putString("quyen","khachhang");
                        edit.commit();
                        Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
                if (rdonhanVien.isChecked()){
                    if(nhanVienDAO.checkDangNhap(ed_taiKhoan.getText().toString().trim(),ed_matKhau.getText().toString().trim())){
                        SharedPreferences shareQuyen = getSharedPreferences("luuDangNhap",MODE_PRIVATE);
                        SharedPreferences.Editor edit = shareQuyen.edit();
                        edit.putString("TK",ed_taiKhoan.getText().toString().trim());
                        edit.putString("quyen","nhanvien");
                        edit.commit();
                        Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    public void doiMauButton() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!ed_taiKhoan.getText().toString().isEmpty() && !ed_matKhau.getText().toString().isEmpty()) {
                    btn_dangNhap.setBackgroundColor(getResources().getColor(R.color.main));
                } else {
                    btn_dangNhap.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        ed_taiKhoan.addTextChangedListener(textWatcher);
        ed_matKhau.addTextChangedListener(textWatcher);
    }
    public boolean kiemTraTrong() {
        boolean isEmpty = false;
        if (ed_taiKhoan.getText().toString().isEmpty()) {
            edL_taiKhoan.setError("Vui lòng nhập tài khoản!");
            isEmpty = true;
        } else {
            edL_taiKhoan.setError(null);
        }
        if (ed_matKhau.getText().toString().isEmpty()) {
            edL_matKhau.setError("Vui lòng nhập mật khẩu!");
            isEmpty = true;
        } else {
            edL_matKhau.setError(null);
        }
        return isEmpty;
    }
    public boolean kiemTraKyTu() {
        boolean isEmpty = false;
        if (ed_taiKhoan.getText().toString().length()<4) {
            edL_taiKhoan.setError("Tài khoản nhập tối thiểu 4 ký tự!");
            isEmpty = true;
        } else {
            edL_taiKhoan.setError(null);
        }
        if (ed_matKhau.getText().toString().length()<6) {
            edL_matKhau.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
            isEmpty = true;
        } else {
            edL_matKhau.setError(null);
        }
        return isEmpty;
    }
}