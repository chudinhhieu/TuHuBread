package hieucdph29636.fpoly.tuhubread.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTienKHAdapter;

public class ChiTiet_Don_nap_tien extends AppCompatActivity {
    TextView id,ten,tien,time,trangThai;
    ImageView img_hoadon;
    Button btn_xacNhan,btn_huy;
    KhachHangDAO khachHangDAO;
    DonNapTienDAO donNapTienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_nap_tien);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String quyen = sharedPreferences.getString("quyen","");
        id = findViewById(R.id.tv_id_ctdnt);
        khachHangDAO = new KhachHangDAO();
        donNapTienDAO = new DonNapTienDAO();
        ten = findViewById(R.id.tv_hoTen_ctdnt);
        tien = findViewById(R.id.tv_soTien_ctdnt);
        time = findViewById(R.id.tv_time_ctdnt);
        trangThai = findViewById(R.id.tv_trangThai_ctdnt);
        img_hoadon = findViewById(R.id.img_anhHD_ctdnt);
        btn_xacNhan = findViewById(R.id.btn_xacNhan_ctdnt);
        btn_huy = findViewById(R.id.btn_huy_ctdnt);
        Bundle bundle = getIntent().getExtras();
        DonNapTien donNapTien = (DonNapTien) bundle.getSerializable("DonNapTien");
        id.setText(donNapTien.getid_DonNapTien()+"");
        ten.setText(khachHangDAO.getHoTen(donNapTien.getTaiKhoan()));
        time.setText(donNapTien.getThoiGianTao());
        tien.setText(donNapTien.getTienNap()+"");
        int tt = donNapTien.getTrangThai();
        if(tt==0){
            trangThai.setText("Chờ xác nhận");
        }else if(tt==1){
            trangThai.setText("Thành công");
        }else {
            trangThai.setText("Hủy");
        }
        if (quyen.equalsIgnoreCase("nhanvien")){
            if (donNapTien.getTrangThai()!=0){
                btn_xacNhan.setVisibility(View.GONE);
                btn_huy.setVisibility(View.GONE);
                return;
            }
            btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    khachHangDAO.updateTien(donNapTien.getTienNap()+ khachHangDAO.getSoDuVi(donNapTien.getTaiKhoan()),donNapTien.getTaiKhoan());
                    donNapTienDAO.updateTT(1,donNapTien.getid_DonNapTien());
                    Toast.makeText(ChiTiet_Don_nap_tien.this, "Xác nhận thành công!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
            btn_huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    donNapTienDAO.updateTT(2,donNapTien.getid_DonNapTien());
                    Toast.makeText(ChiTiet_Don_nap_tien.this, "Hủy thành công!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }
        if (quyen.equalsIgnoreCase("khachhang")){
            btn_xacNhan.setText("Sửa");
            btn_huy.setText("Xóa");
            if (donNapTien.getTrangThai()!=0){
                btn_xacNhan.setVisibility(View.GONE);
                btn_huy.setVisibility(View.GONE);
                return;
            }
            btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(ChiTiet_Don_nap_tien.this);
                    dialog.setContentView(R.layout.dialog_naptien);
                    TextView tv_themAnh = dialog.findViewById(R.id.tv_themAnh);
                    TextView tv_dialog_nt = dialog.findViewById(R.id.tv_dialog_nt);
                    TextInputLayout edL_soTien = dialog.findViewById(R.id.edL_soTienNap);
                    TextInputEditText ed_soTien = dialog.findViewById(R.id.ed_soTienNap);
                    Button btn_xacNhan = dialog.findViewById(R.id.btn_xacNhanNapTien);
                    ImageView anhHD = dialog.findViewById(R.id.img_anhHD);
                    tv_dialog_nt.setText("Chỉnh sửa đơn nạp tiền");
                    ed_soTien.setText(donNapTien.getTienNap()+"");
                    anhHD.setVisibility(View.GONE);
                    tv_themAnh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anhHD.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Date currentDate = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                            String time1 = dateFormat.format(currentDate.getTime());
                            DonNapTien donNap = new DonNapTien();
                            donNap.setid_DonNapTien(donNapTien.getid_DonNapTien());
                            donNap.setMota("Nạp tiền");
                            donNap.setTaiKhoan(donNapTien.getTaiKhoan());
                            donNap.setThoiGianTao(time1);
                            donNap.setTrangThai(0);
                            donNap.setTienNap(Integer.parseInt(ed_soTien.getText().toString().trim()));
                            if(donNapTienDAO.update(donNap)){
                                Toast.makeText(ChiTiet_Don_nap_tien.this, "Thành công", Toast.LENGTH_SHORT).show();
                                tien.setText(ed_soTien.getText().toString().trim());
                                time.setText(time1);
                                dialog.dismiss();
                            }else {
                                Toast.makeText(ChiTiet_Don_nap_tien.this, "Thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.show();
                }

            });
            btn_huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChiTiet_Don_nap_tien.this);

                    builder.setMessage("Bạn có chắc chắn muốn xóa đơn nạp tiền này không?");

                    builder.setTitle("Thông báo");

                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(donNapTienDAO.delete( donNapTien.getid_DonNapTien())){
                                Toast.makeText(ChiTiet_Don_nap_tien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                onBackPressed();
                            }else {
                                Toast.makeText(ChiTiet_Don_nap_tien.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });
        }
    }
}