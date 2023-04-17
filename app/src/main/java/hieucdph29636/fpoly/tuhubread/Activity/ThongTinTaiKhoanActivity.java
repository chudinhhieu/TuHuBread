package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    KhachHangDAO dao;
    NhanVienDAO nhanVienDAO;
    TextInputLayout ltk,lht,lns,lsdt,ldc;
    TextInputEditText tk,ht,ns,sdt,dc;
    Button xacnhan,dmk;
    MyDate date;
    String quyen;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        dao = new KhachHangDAO();
        nhanVienDAO = new NhanVienDAO();
        ltk = findViewById(R.id.edL_tttk_tk);
        btn_back = findViewById(R.id.btn_back_tttk);
        tk = findViewById(R.id.ed_tttk_tk);
        lht = findViewById(R.id.edL_tttk_ht);
        ht = findViewById(R.id.ed_tttk_ht);
        lns = findViewById(R.id.edL_tttk_ns);
        ns = findViewById(R.id.ed_tttk_ns);
        ldc = findViewById(R.id.edL_tttk_dc);
        dc = findViewById(R.id.ed_tttk_dc);
        lsdt = findViewById(R.id.edL_tttk_sdt);
        sdt = findViewById(R.id.ed_tttk_sdt);
        xacnhan = findViewById(R.id.btn_tttk_xacnhan);
        dmk = findViewById(R.id.btn_tttk_dmk);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
         quyen = sharedPreferences.getString("quyen","");
        String taiKhoan = sharedPreferences.getString("TK","");
        dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ThongTinTaiKhoanActivity.this);
                dialog.setContentView(R.layout.dialog_dmk);
                TextInputLayout lmkc,lmkm,lmkm2;
                TextInputEditText mkc,mkm,mkm2;
                Button btn_dmk;
                lmkc = dialog.findViewById(R.id.edL_dmk_cu);
                mkc = dialog.findViewById(R.id.ed_dmk_cu);
                lmkm = dialog.findViewById(R.id.edL_dmk_moi);
                mkm = dialog.findViewById(R.id.ed_dmk_moi);
                lmkm2 = dialog.findViewById(R.id.edL_dmk_moi2);
                mkm2 = dialog.findViewById(R.id.ed_dmk_moi2);
                btn_dmk = dialog.findViewById(R.id.btn_xacNhan_dmk);
                btn_dmk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String matKhauCu;
                        if (quyen.equalsIgnoreCase("khachhang")){
                            matKhauCu = dao.getByTK(taiKhoan).get(0).getMatKhau();
                        }else {
                            matKhauCu = nhanVienDAO.getByTK(taiKhoan).get(0).getMatKhau();
                        }
                        if (mkc.getText().toString().isEmpty()) {
                            lmkc.setError("Vui lòng nhập mật khẩu!");
                            return;
                        } else {
                            lmkc.setErrorEnabled(false);
                        }
                        if (mkm.getText().toString().isEmpty()) {
                            lmkm.setError("Vui lòng nhập mật khẩu!");
                            return;
                        } else {
                            lmkm.setErrorEnabled(false);
                        }
                        if (mkm2.getText().toString().isEmpty()) {
                            lmkm2.setError("Vui lòng nhập mật khẩu!");
                            return;
                        } else {
                            lmkm2.setErrorEnabled(false);
                        }
                        if (mkc.getText().toString().length()<6) {
                            lmkc.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
                            return;
                        } else {
                            lmkc.setErrorEnabled(false);
                        }
                        if (mkm.getText().toString().length()<6) {
                            lmkm.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
                            return;
                        } else {
                            lmkm.setErrorEnabled(false);
                        }
                        if (mkm2.getText().toString().length()<6) {
                            lmkm2.setError("Mật khẩu nhập tối thiểu 6 ký tự!");
                            return;
                        } else {
                            lmkm2.setErrorEnabled(false);
                        }
                        if (!mkc.getText().toString().equalsIgnoreCase(matKhauCu)){
                            lmkc.setError("Mật khẩu cũ không đúng!");
                            return;
                        }else {
                            lmkc.setErrorEnabled(false);
                        }
                        if (!mkm.getText().toString().equalsIgnoreCase(mkm2.getText().toString())){
                            lmkm.setError("Mật khẩu không trùng nhau!");
                            lmkm2.setError("Mật khẩu không trùng nhau!");
                            return;
                        }else {
                            lmkm.setErrorEnabled(false);
                            lmkm2.setErrorEnabled(false);
                        }
                        if (quyen.equalsIgnoreCase("khachhang")){
                            if (dao.updateMK(taiKhoan,mkm.getText().toString())){
                                Toast.makeText(ThongTinTaiKhoanActivity.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(ThongTinTaiKhoanActivity.this, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (nhanVienDAO.updateMK(taiKhoan,mkm.getText().toString())){
                                Toast.makeText(ThongTinTaiKhoanActivity.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(ThongTinTaiKhoanActivity.this, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
        if (quyen.equalsIgnoreCase("khachhang")){
            KhachHang kh = dao.getByTK(taiKhoan).get(0);
            tk.setText(kh.getTaiKhoan());
            ht.setText(kh.getHoTen());
            sdt.setText(kh.getSoDienThoai());
            ns.setText(kh.getNgaySinh());
            dc.setText(kh.getDiaChi());
            ns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ThongTinTaiKhoanActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            calendar.set(i, i1, i2);
                            ns.setText(date.toString(calendar.getTime()));
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                }
            });
            xacnhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kiemTraTrong()) {
                        return;
                    }
                    if (kiemTraKyTu()){
                        return;
                    }
                    if (dao.update(taiKhoan,ht.getText().toString(),ns.getText().toString(),sdt.getText().toString(),dc.getText().toString())) {
                        Toast.makeText(ThongTinTaiKhoanActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            });
        }else {
            NhanVien nv = nhanVienDAO.getByTK(taiKhoan).get(0);
            tk.setText(nv.getTaiKhoan());
            ht.setText(nv.getHoTen());
            sdt.setText(nv.getSoDienThoai());
            ns.setText(nv.getNgaySinh());
            ldc.setVisibility(View.GONE);
            ns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ThongTinTaiKhoanActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            calendar.set(i, i1, i2);
                            ns.setText(date.toString(calendar.getTime()));
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                }
            });
            xacnhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kiemTraTrong()) {
                        return;
                    }
                    if (kiemTraKyTu()){
                        return;
                    }
                    if (nhanVienDAO.updateNV(ht.getText().toString(),ns.getText().toString(),sdt.getText().toString(),taiKhoan)) {
                        Toast.makeText(ThongTinTaiKhoanActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            });
        }
    }
    public boolean kiemTraTrong() {
        boolean isEmpty = false;
        if (ht.getText().toString().isEmpty()) {
            lht.setError("Vui lòng nhập họ tên!");
            isEmpty = true;
        } else {
            lht.setErrorEnabled(false);

        }
        if (ns.getText().toString().isEmpty()) {
            lns.setError("Vui lòng nhập ngày sinh!");
            isEmpty = true;
        } else {
            lns.setErrorEnabled(false);

        }
        if (sdt.getText().toString().isEmpty()) {
            lsdt.setError("Vui lòng nhập số điện thoại!");
            isEmpty = true;
        } else {
            lsdt.setErrorEnabled(false);

        }
        if (quyen.equalsIgnoreCase("khachhang")){
            if (dc.getText().toString().isEmpty()) {
                ldc.setError("Vui lòng nhập địa chỉ!");
                isEmpty = true;
            } else {
                ldc.setErrorEnabled(false);

            }
        }
        return isEmpty;
    }
    public boolean kiemTraKyTu() {
        boolean isEmpty = false;
        if (!sdt.getText().toString().matches("^0[3589]{1}\\d{8}$")) {
            lsdt.setError("Số điện thoại phải đúng định dạng!");
            isEmpty = true;
        } else {
            lsdt.setErrorEnabled(false);
        }
        return isEmpty;
    }
}