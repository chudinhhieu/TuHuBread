package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
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
        tv_id_dn_ct.setText((bundle.getString("tv_id_dn" + "")));
        tv_id_kh_dn_ct.setText((bundle.getString("tv_id_kh_dn" + "")));
        tv_tg_dn_ct.setText((bundle.getString("tv_tg_dn" + "")));
        tv_verify_dn_ct.setText((bundle.getString("tv_ver_dn" + "")));
        tv_money_dn_ct.setText((bundle.getString("tv_money_dn" + "")));

    }
}