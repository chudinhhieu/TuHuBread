package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DanhGiaDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DanhGia;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.Hien_DanhGia_mon_Adapter;

public class DanhGia_BinhLuan_Hien extends AppCompatActivity {
    DanhGiaDAO dao;
    Hien_DanhGia_mon_Adapter adapter;
    ArrayList<DanhGia> list;
    RecyclerView rcv_Danhsach;
    ImageView btn_back_dgbl;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia_binh_luan_hien);
        Bundle bundle = getIntent().getExtras();
        int id_ma =bundle.getInt("id_ma");
        rcv_Danhsach=findViewById(R.id.rcv_danhgia_binhLuan);
        btn_back_dgbl=findViewById(R.id.btn_back_dgbl);
        dao=new DanhGiaDAO();
        list=dao.getByID(id_ma);
        adapter=new Hien_DanhGia_mon_Adapter(this,list);
        rcv_Danhsach.setAdapter(adapter);
        btn_back_dgbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}