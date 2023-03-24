package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hieucdph29636.fpoly.tuhubread.R;

public class DangNhapActivity extends AppCompatActivity {
    TextInputLayout edL_taiKhoan, edL_matKhau;
    TextInputEditText ed_taiKhoan, ed_matKhau;
    Button btn_dangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edL_taiKhoan = findViewById(R.id.edL_taiKhoan);
        edL_matKhau = findViewById(R.id.edL_matKhau);
        ed_taiKhoan = findViewById(R.id.ed_taiKhoan);
        ed_matKhau = findViewById(R.id.ed_matKhau);
        btn_dangNhap = findViewById(R.id.btn_dangNhap);
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
                 if(ed_taiKhoan.getText().toString().equals("admin") || ed_matKhau.getText().toString().equals("admin123")){
                    Intent intent = new Intent(DangNhapActivity.this,Chu_Activity.class);
                    startActivity(intent);
                    return;
                }
                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
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