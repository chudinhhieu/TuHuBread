package hieucdph29636.fpoly.tuhubread.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.Dialog_custom;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.NhanVienAdapter;

public class ChiTiet_Nhan_vien extends AppCompatActivity {

    TextView tv_ten_nv_ct, tv_sdt_nv_ct, tv_user_nv_ct;
    TextView tv_passwd_nv_ct, tv_ngaysinh_nv_ct, tv_quyen_nv_ct;
    Button btn_sua_nv, btn_xoa_nv;
    MyDate date;
    NhanVienDAO nhanVienDAO;
    ImageView btn_back_ctnv;
    NhanVien nhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nv);
        nhanVienDAO = new NhanVienDAO();
        btn_back_ctnv = findViewById(R.id.btn_back_ctnv);
        tv_ten_nv_ct = findViewById(R.id.tv_ten_nv_ct);
        tv_sdt_nv_ct = findViewById(R.id.tv_sdt_nv_ct);
        tv_user_nv_ct = findViewById(R.id.tv_user_nv_ct);
        tv_passwd_nv_ct = findViewById(R.id.tv_passwd_nv_ct);
        tv_ngaysinh_nv_ct = findViewById(R.id.tv_ngaysinh_nv_ct);
        tv_quyen_nv_ct = findViewById(R.id.tv_quyen_nv_ct);
        btn_sua_nv = findViewById(R.id.btn_sua_nv);
        btn_xoa_nv = findViewById(R.id.btn_xoa_nv);
        Bundle bundle = getIntent().getExtras();
        String tenNV = bundle.getString("tv_ten_nv" + "");
        String sdtNV =bundle.getString("tv_sdt_nv" + "");
        String taiKhoanNV = bundle.getString("tv_user_nv" + "");
        String passNV =bundle.getString("tv_pass_nv" + "");
        String ngaySinhNV = bundle.getString("tv_ngaysinh_nv" + "");
        int quyen = bundle.getInt("tv_quyen_nv");
        tv_ten_nv_ct.setText(tenNV);
        tv_sdt_nv_ct.setText(sdtNV);
        tv_user_nv_ct.setText(taiKhoanNV);
        tv_passwd_nv_ct.setText(passNV);
        tv_ngaysinh_nv_ct.setText(ngaySinhNV);
        if (quyen == 0) {
            tv_quyen_nv_ct.setText("Chủ cửa hàng");
        } else {
            tv_quyen_nv_ct.setText("Trưởng cửa hàng");
        }
        if (quyen == 2) {
            tv_quyen_nv_ct.setText("Nhân Viên");
        }
        btn_back_ctnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(quyen==0){
            btn_sua_nv.setVisibility(View.GONE);
            btn_xoa_nv.setVisibility(View.GONE);
        }
        btn_sua_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVien = new NhanVien();
                Dialog dialog = new Dialog(ChiTiet_Nhan_vien.this);
                dialog.setContentView(R.layout.dialog_them_nv);
                TextInputEditText ten_nv = dialog.findViewById(R.id.ed_them_tennv);
                TextInputEditText sdt_nv = dialog.findViewById(R.id.ed_them_sdtnv);
                TextInputEditText user_nv = dialog.findViewById(R.id.ed_them_usernv);
                TextInputEditText pass_nv = dialog.findViewById(R.id.ed_them_mknv);
                TextInputEditText ngaysinh_nv = dialog.findViewById(R.id.ed_them_ngaysinh_nv);
                Button btn_them_nv = dialog.findViewById(R.id.btn_them_nv);
                Button btn_huy_nv = dialog.findViewById(R.id.btn_huy_nv);
                RadioButton rdoTCH = dialog.findViewById(R.id.rdoTCH);
                RadioButton rdoNV = dialog.findViewById(R.id.rdoNV);
                TextInputLayout edL_tennv = dialog.findViewById(R.id.etl_them_tennv);
                TextInputLayout edL_sdtnv = dialog.findViewById(R.id.etl_them_sdtnv);
                TextInputLayout edL_usernv = dialog.findViewById(R.id.etl_them_usernv);
                TextInputLayout edL_passnv = dialog.findViewById(R.id.etl_them_mknv);
                TextInputLayout edL_ngaysinhnv = dialog.findViewById(R.id.etl_them_ngaysinh_nv);
                ten_nv.setText(tenNV);
                sdt_nv.setText(sdtNV);
                user_nv.setText(taiKhoanNV);
                pass_nv.setText(passNV);
                ngaysinh_nv.setText(ngaySinhNV);
                if (quyen == 1){
                    rdoTCH.setChecked(true);
                }else {
                    rdoNV.setChecked(true);
                }
                if (rdoTCH.isChecked()) {
                    nhanVien.setQuenNhanVien(1);
                } else {
                    nhanVien.setQuenNhanVien(2);
                }
                ngaysinh_nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ChiTiet_Nhan_vien.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                ngaysinh_nv.setText(date.toStringVn(calendar.getTime()));
                                nhanVien.setNgaySinh(date.toString(calendar.getTime()));
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                btn_huy_nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btn_them_nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ten_nv.getText().toString().isEmpty()) {
                            edL_tennv.setError("Vui lòng nhập họ tên");
                            return;
                        } else {
                            edL_tennv.setErrorEnabled(false);
                        }
                        if (sdt_nv.getText().toString().isEmpty()) {
                            edL_sdtnv.setError("Vui lòng nhập số điện thoại");
                            return;
                        } else {
                            edL_sdtnv.setErrorEnabled(false);
                        }
                        if (user_nv.getText().toString().isEmpty()) {
                            edL_usernv.setError("Vui lòng nhập tài khoản");
                            return;
                        } else {
                            edL_usernv.setErrorEnabled(false);
                        }
                        if (pass_nv.getText().toString().isEmpty()) {
                            edL_passnv.setError("Vui lòng nhập mật khẩu");
                            return;
                        } else {
                            edL_passnv.setErrorEnabled(false);
                        }
                        if (ngaysinh_nv.getText().toString().isEmpty()) {
                            edL_ngaysinhnv.setError("Vui lòng nhập ngày sinh");
                            return;
                        } else {
                            edL_ngaysinhnv.setErrorEnabled(false);
                        }
                        if (!rdoNV.isChecked()&&!rdoTCH.isChecked()){
                            Toast.makeText(ChiTiet_Nhan_vien.this, "Vui lòng chọn quyền nhân viên!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (user_nv.getText().toString().length()<4) {
                            edL_usernv.setError("Tài khoản nhập tối thiểu 4 ký tự!");
                            return;
                        } else {
                            edL_usernv.setErrorEnabled(false);
                        }
                        if (pass_nv.getText().toString().length()<6) {
                            edL_passnv.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
                            return;
                        } else {
                            edL_passnv.setErrorEnabled(false);
                        }
                        if (!sdt_nv.getText().toString().matches("^0[3589]{1}\\d{8}$")) {
                            edL_sdtnv.setError("Số điện thoại phải đúng định dạng!");
                            return;
                        } else {
                            edL_sdtnv.setErrorEnabled(false);
                        }
                        nhanVien.setHoTen(ten_nv.getText().toString());
                        nhanVien.setSoDienThoai(sdt_nv.getText().toString());
                        nhanVien.setTaiKhoan(user_nv.getText().toString());
                        nhanVien.setMatKhau(pass_nv.getText().toString());
                        try {
                            if (nhanVienDAO.update(nhanVien)) {
                                new Dialog_custom(ChiTiet_Nhan_vien.this).sendDialog();
                                tv_ten_nv_ct.setText(ten_nv.getText().toString());
                                tv_sdt_nv_ct.setText(sdt_nv.getText().toString());
                                tv_user_nv_ct.setText(user_nv.getText().toString());
                                tv_passwd_nv_ct.setText(pass_nv.getText().toString());
                                tv_ngaysinh_nv_ct.setText(ngaysinh_nv.getText().toString());
                                if (rdoTCH.isChecked()) {
                                    tv_quyen_nv_ct.setText("Trưởng cửa hàng");
                                } else {
                                    tv_quyen_nv_ct.setText("Nhân viên");
                                }
                                dialog.dismiss();
                            }
                        }catch (Exception ex){
                            Toast.makeText(ChiTiet_Nhan_vien.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });

        btn_xoa_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVienDAO.delete((bundle.getString("tv_user_nv")));
                Toast.makeText(ChiTiet_Nhan_vien.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });



    }
}

