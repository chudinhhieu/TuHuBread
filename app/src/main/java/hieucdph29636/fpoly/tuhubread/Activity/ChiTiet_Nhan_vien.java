package hieucdph29636.fpoly.tuhubread.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.NhanVienAdapter;

public class ChiTiet_Nhan_vien extends AppCompatActivity {

    TextView tv_id_nv_ct, tv_ten_nv_ct, tv_sdt_nv_ct, tv_user_nv_ct;
    TextView tv_passwd_nv_ct, tv_ngaysinh_nv_ct, tv_quyen_nv_ct;
    Button btn_sua_nv, btn_xoa_nv;
    MyDate date;
    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> dsnv ;
    NhanVienAdapter nhanVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nv);
        nhanVienDAO = new NhanVienDAO();
        tv_id_nv_ct = findViewById(R.id.tv_id_nv_ct);
        tv_ten_nv_ct = findViewById(R.id.tv_ten_nv_ct);
        tv_sdt_nv_ct = findViewById(R.id.tv_sdt_nv_ct);
        tv_user_nv_ct = findViewById(R.id.tv_user_nv_ct);
        tv_passwd_nv_ct = findViewById(R.id.tv_passwd_nv_ct);
        tv_ngaysinh_nv_ct = findViewById(R.id.tv_ngaysinh_nv_ct);
        tv_quyen_nv_ct = findViewById(R.id.tv_quyen_nv_ct);
        btn_sua_nv = findViewById(R.id.btn_sua_nv);
        btn_xoa_nv = findViewById(R.id.btn_xoa_nv);

        Bundle bundle = getIntent().getExtras();
        tv_id_nv_ct.setText((bundle.getInt("tv_id")) + "");
        tv_ten_nv_ct.setText(bundle.getString("tv_ten_nv" + ""));
        tv_sdt_nv_ct.setText(bundle.getString("tv_sdt_nv" + ""));
        tv_user_nv_ct.setText(bundle.getString("tv_user_nv" + ""));
        tv_passwd_nv_ct.setText(bundle.getString("tv_pass_nv" + ""));
        tv_ngaysinh_nv_ct.setText(bundle.getString("tv_ngaysinh_nv" + ""));
        int quyen = bundle.getInt("tv_quyen_nv");
        if (quyen == 0) {
            tv_quyen_nv_ct.setText("Chủ cửa hàng");
        } else {
            tv_quyen_nv_ct.setText("Trưởng cửa hàng");
        }
        if (quyen == 2) {
            tv_quyen_nv_ct.setText("Nhân Viên");
        }



        NhanVien nhanVien = new NhanVien();
        int id = bundle.getInt("tv_id");
        nhanVien.setHoTen(bundle.getString("tv_ten_nv"));
        nhanVien.setQuenNhanVien(quyen);
        nhanVien.setSoDienThoai(bundle.getString("tv_sdt_nv"));
        nhanVien.setTaiKhoan(bundle.getString("tv_user_nv"));
        nhanVien.setMatKhau(bundle.getString("tv_pass_nv"));


        btn_sua_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ChiTiet_Nhan_vien.this);
                dialog.setContentView(R.layout.dialog_sua_nv);
                TextInputEditText ten_nv = dialog.findViewById(R.id.ed_sua_tennv);
                TextInputEditText sdt_nv = dialog.findViewById(R.id.ed_sua_sdtnv);
                TextInputEditText user_nv = dialog.findViewById(R.id.ed_sua_usernv);
                TextInputEditText pass_nv = dialog.findViewById(R.id.ed_sua_mknv);
                TextInputEditText ngaysinh_nv = dialog.findViewById(R.id.ed_sua_ngaysinh_nv);
                TextInputEditText quyen_nv = dialog.findViewById(R.id.ed_sua_quyennv);
                Button btn_sua_nv = dialog.findViewById(R.id.btn_sua_nv);
                Button btn_huy_nv = dialog.findViewById(R.id.btn_huy_nv);

                TextInputLayout edL_tennv = dialog.findViewById(R.id.etl_sua_tennv);
                TextInputLayout edL_sdtnv = dialog.findViewById(R.id.etl_sua_sdtnv);
                TextInputLayout edL_usernv = dialog.findViewById(R.id.etl_sua_usernv);
                TextInputLayout edL_passnv = dialog.findViewById(R.id.etl_sua_mknv);
                TextInputLayout edL_ngaysinhnv = dialog.findViewById(R.id.etl_sua_ngaysinh_nv);
                TextInputLayout edL_quyennv = dialog.findViewById(R.id.etl_sua_quyennv);

                ten_nv.setText(ten_nv.getText().toString());
                sdt_nv.setText(sdt_nv.getText().toString());
                user_nv.setText(user_nv.getText().toString());
                pass_nv.setText(pass_nv.getText().toString());
                quyen_nv.setText(quyen_nv.getText().toString());
                ngaysinh_nv.setText(ngaysinh_nv.getText().toString());
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
                btn_sua_nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ten_nv.getText().toString().isEmpty()) {
                            edL_tennv.setError("Hãy nhập tên nhân viên !!");
                            return;
                        } else {
                            edL_tennv.setError("");

                        }
                        if (sdt_nv.getText().toString().isEmpty()) {
                            edL_sdtnv.setError("Hãy nhập số điện thoại !!");
                        } else {
                            edL_sdtnv.setError("");

                        }
                        if (user_nv.getText().toString().isEmpty()) {
                            edL_sdtnv.setError("Hãy tên đăng nhập của nhân viên !!");
                        } else {
                            edL_usernv.setError("");

                        }
                        if (pass_nv.getText().toString().isEmpty()) {
                            edL_passnv.setError("Hãy nhập mật khẩu !!");
                            return;
                        } else {
                            edL_tennv.setError("");
                        }
                        if (ngaysinh_nv.getText().toString().isEmpty()) {
                            edL_tennv.setError("Hãy nhập ngày sinh !!");
                            return;
                        } else {
                            edL_ngaysinhnv.setError("");

                        }
                        if (quyen_nv.getText().toString().isEmpty()) {
                            edL_quyennv.setError("Hãy nhập tên nhân viên !!");
                            return;
                        } else {
                            edL_quyennv.setError("");
                        }
                        NhanVien nhanVien = new NhanVien();
                        nhanVien.setHoTen(ten_nv.getText().toString());
                        nhanVien.setSoDienThoai(sdt_nv.getText().toString());
                        nhanVien.setTaiKhoan(user_nv.getText().toString());
                        nhanVien.setMatKhau(pass_nv.getText().toString());
                        nhanVien.setNgaySinh(ngaysinh_nv.getText().toString());
                        nhanVien.setQuenNhanVien(Integer.valueOf(quyen_nv.getText().toString()));

                        if (nhanVienDAO.update(nhanVien)) {
                            Toast.makeText(ChiTiet_Nhan_vien.this, "Đã sửa thành công", Toast.LENGTH_SHORT).show();
                            // loadData();
                            tv_ten_nv_ct.setText(ten_nv.getText().toString());
                            tv_sdt_nv_ct.setText(sdt_nv.getText().toString());
                            tv_user_nv_ct.setText(user_nv.getText().toString());
                            tv_passwd_nv_ct.setText(pass_nv.getText().toString());
                            tv_ngaysinh_nv_ct.setText(ngaysinh_nv.getText().toString());
                            tv_quyen_nv_ct.setText(quyen_nv.getText().toString());
                            dialog.dismiss();
                        } else {
                            Toast.makeText(ChiTiet_Nhan_vien.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            Log.d("zzzz","Lỗi: ");

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

//    private void loadData () {
//        nhanVienDAO = new NhanVienDAO(ChiTiet_Nhan_vien.this);
//        dsnv = nhanVienDAO.getAll_nv(this);
//        nhanVienAdapter = new NhanVienAdapter(this, dsnv);
//        rcv_nv.setAdapter(nhanVienAdapter);
//    }

}

