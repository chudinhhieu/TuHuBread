package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.R;

public class ChiTiet_Nhan_vien extends AppCompatActivity {

    TextView tv_id_nv_ct,tv_ten_nv_ct,tv_sdt_nv_ct,tv_user_nv_ct;
    TextView tv_passwd_nv_ct,tv_ngaysinh_nv_ct,tv_quyen_nv_ct;
    Button btn_sua_nv,btn_xoa_nv;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nv);
        nhanVienDAO = new NhanVienDAO(this);
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
        tv_id_nv_ct.setText((bundle.getString("tv_id" + "")));
        tv_ten_nv_ct.setText(bundle.getString("tv_ten_nv"+""));
        tv_sdt_nv_ct.setText(bundle.getString("tv_sdt_nv"+""));
        tv_user_nv_ct.setText(bundle.getString("tv_user_nv"+""));
        tv_passwd_nv_ct.setText(bundle.getString("tv_pass_nv"+""));
        tv_ngaysinh_nv_ct.setText(bundle.getString("tv_ngaysinh_nv"+""));
        tv_quyen_nv_ct.setText(bundle.getString("tv_quyen_nv" + ""));

        btn_sua_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChiTiet_Nhan_vien.this, "Nút này chưa làm!!!", Toast.LENGTH_SHORT).show();
            }
        });


//        btn_xoa_nv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                nhanVienDAO.delete_nv();
//                Toast.makeText(ChiTiet_Nhan_vien.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
//                onBackPressed();
//            }
//        });
    }
}