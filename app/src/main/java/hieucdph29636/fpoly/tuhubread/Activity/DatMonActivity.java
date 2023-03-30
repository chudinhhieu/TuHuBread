package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hieucdph29636.fpoly.tuhubread.R;

public class DatMonActivity extends AppCompatActivity {
    ImageView btn_back,btn_tru,btn_cong;
    TextView tv_tenMon_datMon,tv_gia_datMon,tv_thanhphan_datMon,tv_tongGiaMonAn,tv_soLuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        btn_back = findViewById(R.id.btn_back_monAn);
        tv_tenMon_datMon = findViewById(R.id.tv_tenMon_datMon);
        btn_tru = findViewById(R.id.btn_truSL);
        btn_cong = findViewById(R.id.btn_congSL);
        tv_gia_datMon = findViewById(R.id.tv_gia_datMon);
        tv_thanhphan_datMon = findViewById(R.id.tv_thanhphan_datMon);
        tv_soLuong = findViewById(R.id.tv_soluong_datMon);
        tv_tongGiaMonAn = findViewById(R.id.tv_tongGia_monAn);
        Bundle bundle = getIntent().getExtras();
        int id =bundle.getInt("idMon");
        String ten= bundle.getString("tenMon");
        String thanhPhan= bundle.getString("thanhPhan");
        int gia = bundle.getInt("gia");
        int trangThai = bundle.getInt("trangThai");
        int loai =bundle.getInt("id_loai");
        tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+gia+"");
        tv_tenMon_datMon.setText(ten);
        tv_gia_datMon.setText(gia+"");
        tv_thanhphan_datMon.setText(thanhPhan);
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(tv_soLuong.getText().toString());
                soluong++;
                tv_soLuong.setText(soluong+"");
                int tongGia = soluong*gia;
                tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+tongGia+"");
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(tv_soLuong.getText().toString());
                soluong--;
                tv_soLuong.setText(soluong+"");
                int tongGia = soluong*gia;
                tv_tongGiaMonAn.setText("Thêm vào giỏ hàng - "+tongGia+"");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}