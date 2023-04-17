package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.StringReader;
import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;

public class DangKyKhachHangActivity extends AppCompatActivity {
    TextInputLayout edL_taiKhoan, edL_matKhau,edL_nhapLaiMatKhau,edL_hoTen,edL_ngaysinh,edL_sdt,edL_diaChi;
    TextInputEditText ed_taiKhoan, ed_matKhau,ed_nhapLaiMatKhau,ed_hoTen,ed_ngaysinh,ed_sdt,ed_diaChi;
    Button btn_dangky;
    KhachHangDAO dao;
    MyDate date;
    String ns = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_khach_hang);
        dao = new KhachHangDAO();
        edL_taiKhoan = findViewById(R.id.edL_taiKhoannew);
        edL_matKhau = findViewById(R.id.edL_matKhaunew);
        edL_nhapLaiMatKhau = findViewById(R.id.edL_nhapLaiMatKhau);
        edL_hoTen = findViewById(R.id.edL_hotennew);
        edL_ngaysinh = findViewById(R.id.edL_ngaysinhnew);
        edL_sdt = findViewById(R.id.edL_sdtnew);
        edL_diaChi = findViewById(R.id.edL_diaChinew);
        ed_taiKhoan = findViewById(R.id.ed_taiKhoannew);
        ed_matKhau = findViewById(R.id.ed_matKhaunew);
        ed_nhapLaiMatKhau = findViewById(R.id.ed_nhapLaiMatKhau);
        ed_hoTen = findViewById(R.id.ed_hotennew);
        ed_ngaysinh = findViewById(R.id.ed_ngaysinhnew);
        ed_sdt = findViewById(R.id.ed_sdtnew);
        ed_diaChi = findViewById(R.id.ed_diaChinew);
        btn_dangky = findViewById(R.id.btn_dangKy_KH);
        ed_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DangKyKhachHangActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        ed_ngaysinh.setText(date.toStringVn(calendar.getTime()));
                        ns = date.toString(calendar.getTime());
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = ed_taiKhoan.getText().toString().trim();
                String matKhau = ed_matKhau.getText().toString().trim();
                String nhaplaimatkahu = ed_nhapLaiMatKhau.getText().toString().trim();
                String hoten = ed_hoTen.getText().toString().trim();
                String sdt = ed_sdt.getText().toString().trim();
                String diachi = ed_diaChi.getText().toString().trim();

                if (kiemTraTrong()) {
                    return;
                }
                if (kiemTraKyTu()){
                    return;
                }
                KhachHang kh = new KhachHang(taiKhoan,hoten,sdt,matKhau,ns,diachi,0);
                if (dao.insert(kh)){
                    Toast.makeText(DangKyKhachHangActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(DangKyKhachHangActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    edL_taiKhoan.setError("Tài khoản đã tồn tại!");
                }
            }
        });

    }
    public boolean kiemTraTrong() {
        boolean isEmpty = false;
        if (ed_taiKhoan.getText().toString().isEmpty()) {
            edL_taiKhoan.setError("Vui lòng nhập tài khoản!");
            isEmpty = true;
        } else {
            edL_taiKhoan.setErrorEnabled(false);

        }
        if (ed_matKhau.getText().toString().isEmpty()) {
            edL_matKhau.setError("Vui lòng nhập mật khẩu!");
            isEmpty = true;
        } else {
            edL_matKhau.setErrorEnabled(false);

        }
        if (ed_nhapLaiMatKhau.getText().toString().isEmpty()) {
            edL_nhapLaiMatKhau.setError("Vui lòng nhập mật khẩu!");
            isEmpty = true;
        } else {
            edL_nhapLaiMatKhau.setErrorEnabled(false);

        }
        if (ed_hoTen.getText().toString().isEmpty()) {
            edL_hoTen.setError("Vui lòng nhập họ tên!");
            isEmpty = true;
        } else {
            edL_hoTen.setErrorEnabled(false);

        }
        if (ed_ngaysinh.getText().toString().isEmpty()) {
            edL_ngaysinh.setError("Vui lòng nhập ngày sinh!");
            isEmpty = true;
        } else {
            edL_ngaysinh.setErrorEnabled(false);

        }
        if (ed_sdt.getText().toString().isEmpty()) {
            edL_sdt.setError("Vui lòng nhập số điện thoại!");
            isEmpty = true;
        } else {
            edL_sdt.setErrorEnabled(false);

        }
        if (ed_diaChi.getText().toString().isEmpty()) {
            edL_diaChi.setError("Vui lòng nhập địa chỉ!");
            isEmpty = true;
        } else {
            edL_diaChi.setErrorEnabled(false);

        }
        return isEmpty;
    }
    public boolean kiemTraKyTu() {
        boolean isEmpty = false;
        if (ed_taiKhoan.getText().toString().length()<4) {
            edL_taiKhoan.setError("Tài khoản nhập tối thiểu 4 ký tự!");
            isEmpty = true;
        } else {
            edL_taiKhoan.setErrorEnabled(false);

        }
        if (ed_matKhau.getText().toString().length()<6) {
            edL_matKhau.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
            isEmpty = true;
        } else {
            edL_matKhau.setErrorEnabled(false);

        }
        if (!ed_matKhau.getText().toString().equals(ed_nhapLaiMatKhau.getText().toString())){
            edL_nhapLaiMatKhau.setError("Phải trùng với mật khẩu");
            isEmpty=true;
        }else{
            edL_nhapLaiMatKhau.setErrorEnabled(false);
        }
        if (!ed_sdt.getText().toString().matches("^0[3589]{1}\\d{8}$")) {
            edL_sdt.setError("Số điện thoại phải đúng định dạng!");
            isEmpty = true;
        } else {
            edL_sdt.setErrorEnabled(false);

        }
        return isEmpty;
    }
}