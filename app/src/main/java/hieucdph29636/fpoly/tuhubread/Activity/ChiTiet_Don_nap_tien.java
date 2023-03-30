package hieucdph29636.fpoly.tuhubread.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;

public class ChiTiet_Don_nap_tien extends AppCompatActivity {
    TextView tv_id_dn_ct,tv_id_kh_dn_ct,tv_tg_dn_ct,tv_verify_dn_ct,tv_money_dn_ct;
    Button btn_sua_dnt,btn_xoa_ndnt;
    DonNapTienDAO donNapTienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_nap_tien);

        donNapTienDAO = new DonNapTienDAO(this);
        tv_id_dn_ct = findViewById(R.id.tv_id_dn_ct);
        tv_id_kh_dn_ct = findViewById(R.id.tv_id_kh_dn_ct);
        tv_tg_dn_ct = findViewById(R.id.tv_tg_dn_ct);
        tv_verify_dn_ct = findViewById(R.id.tv_verify_dn_ct);
        tv_money_dn_ct = findViewById(R.id.tv_money_dn_ct);
        btn_sua_dnt  = findViewById(R.id.btn_sua_dnt);
        btn_xoa_ndnt = findViewById(R.id.btn_xoa_ndnt);

        Bundle bundle = getIntent().getExtras();
        tv_id_dn_ct.setText((bundle.getInt("tv_id_dn" ))+ "");
        tv_id_kh_dn_ct.setText((bundle.getString("tv_id_kh_dn" ))+ "");
        tv_tg_dn_ct.setText((bundle.getString("tv_tg_dn" + "")));
        int trangthai = bundle.getInt("tv_ver_dn");
        tv_money_dn_ct.setText((bundle.getInt("tv_money_dn" ))+ "");


        if(trangthai == 0){
            tv_verify_dn_ct.setText("Chưa thanh toán");
        }else {
            tv_verify_dn_ct.setText("Đã thanh toán");
        }

        DonNapTien dnt = new DonNapTien();
        dnt.setid_DonNapTien((bundle.getInt("tv_id_dn")));
        dnt.setTaiKhoan((bundle.getString("tv_id_kh_dn" )));
        dnt.setThoiGianTao((bundle.getString("tv_tg_dn")));
        dnt.setTienNap((bundle.getInt("tv_money_dn")));
        dnt.setTrangThai(trangthai);


        btn_sua_dnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChiTiet_Don_nap_tien.this, "Nút này chưa làm!!!", Toast.LENGTH_SHORT).show();

            }
        });


        btn_xoa_ndnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donNapTienDAO.delete_donNapTien(ChiTiet_Don_nap_tien.this,(bundle.getInt("tv_id_dn")));
                Toast.makeText(ChiTiet_Don_nap_tien.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}