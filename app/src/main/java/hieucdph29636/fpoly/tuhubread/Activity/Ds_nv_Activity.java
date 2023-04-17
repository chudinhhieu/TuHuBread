package hieucdph29636.fpoly.tuhubread.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.NhanVienAdapter;

public class Ds_nv_Activity extends AppCompatActivity {

    private RecyclerView rcv_nv;
    private FloatingActionButton flbtn_add_nv;
    MyDate date;

    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> dsnv;
    NhanVienAdapter nhanVienAdapter;
    ImageView btn_search, btn_back_qlnv;
    NhanVien nhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nv);

        rcv_nv = findViewById(R.id.rcv_nv);
        btn_back_qlnv = findViewById(R.id.btn_back_qlnv);
        flbtn_add_nv = findViewById(R.id.flbtn_add_nv);
        btn_search = findViewById(R.id.btn_search_nv);
        nhanVienDAO = new NhanVienDAO();
        dsnv = nhanVienDAO.getAll();
        nhanVienAdapter = new NhanVienAdapter(this, dsnv);
        rcv_nv.setAdapter(nhanVienAdapter);
        btn_back_qlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Ds_nv_Activity.this);
                dialog.setContentView(R.layout.activity_search_share);
                TextInputEditText tied = dialog.findViewById(R.id.ed_search);
                TextInputLayout til = dialog.findViewById(R.id.edL_search);
                TextView btn_xacnhan = dialog.findViewById(R.id.btn_xacNhan_searchKH);
                tied.setHint("Nhập họ tên");
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tied.getText().toString().isEmpty()){
                            til.setError("Không được để trống");
                            return;
                        } else {
                            til.setErrorEnabled(false);
                        }
                        dsnv = nhanVienDAO.getByHoTen(tied.getText().toString().trim());
                        nhanVienAdapter = new NhanVienAdapter(Ds_nv_Activity.this, dsnv);
                        rcv_nv.setAdapter(nhanVienAdapter);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        flbtn_add_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVien = new NhanVien();
                Dialog dialog = new Dialog(Ds_nv_Activity.this);
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

                ten_nv.setText(ten_nv.getText().toString());
                sdt_nv.setText(sdt_nv.getText().toString());
                user_nv.setText(user_nv.getText().toString());
                pass_nv.setText(pass_nv.getText().toString());
                ngaysinh_nv.setText(ngaysinh_nv.getText().toString());
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Ds_nv_Activity.this, new DatePickerDialog.OnDateSetListener() {
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
                            Toast.makeText(Ds_nv_Activity.this, "Vui lòng chọn quyền nhân viên!", Toast.LENGTH_SHORT).show();
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
                            if (nhanVienDAO.insert(nhanVien)) {
                                Toast.makeText(Ds_nv_Activity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                                loadData();
                                dialog.dismiss();
                            }else {
                                edL_usernv.setError("Tên đăng nhập đã tồn tại!");
                            }
                        }catch (Exception ex){
                            Toast.makeText(Ds_nv_Activity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });


    }

    private void loadData() {
        nhanVienDAO = new NhanVienDAO();
        dsnv = nhanVienDAO.getAll();
        nhanVienAdapter = new NhanVienAdapter(this, dsnv);
        rcv_nv.setAdapter(nhanVienAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        dsnv = nhanVienDAO.getAll();
        nhanVienAdapter = new NhanVienAdapter(this, dsnv);
        rcv_nv.setAdapter(nhanVienAdapter);
    }

}