package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.KhuyenMaiAdapter;

public class KhuyenMaiActivity extends AppCompatActivity {
    TextView tv_mota_km , tv_sotiengiam_km ;
    Button btn_chinhsua_km ;
    KhuyenMaiAdapter khuyenMaiAdapter;
    List<KhuyenMai> list ;
    KhuyenMaiDAO khuyenMaiDAO ;
    RecyclerView rcv_khuyenMai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khuyen_mai);
        rcv_khuyenMai = findViewById(R.id.rcv_khuyenMai);

        khuyenMaiDAO = new KhuyenMaiDAO(this);
        tv_mota_km = findViewById(R.id.tv_mota_km);
        tv_sotiengiam_km = findViewById(R.id.tv_sotiengiam_km);
        btn_chinhsua_km = findViewById(R.id.btn_chinhsua_km);

        list = khuyenMaiDAO.selectAll();
        khuyenMaiAdapter = new KhuyenMaiAdapter(this,list);
        rcv_khuyenMai.setAdapter(khuyenMaiAdapter);


    }
}
