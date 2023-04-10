package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DanhGia_mon_Adapter;

public class DanhSach_DanhGia_Mon extends AppCompatActivity {
     DonHangDAO dao;
     DanhGia_mon_Adapter adapter;
     ArrayList<MonAn> list;
     RecyclerView rcv_Danhsach;
     ImageView btn_back_dgma;
    int id_dh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia_mon);
        rcv_Danhsach=findViewById(R.id.rcv_danhgia_mon);
        btn_back_dgma=findViewById(R.id.btn_back_dgma);
        dao=new DonHangDAO();
        Bundle bundle=getIntent().getExtras();
        id_dh=bundle.getInt("id dh");
        list=dao.getMonAnChuaDanhGia(id_dh);
        adapter=new DanhGia_mon_Adapter(this,list,id_dh);
        rcv_Danhsach.setAdapter(adapter);
        btn_back_dgma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list=dao.getMonAnChuaDanhGia(id_dh);
        adapter=new DanhGia_mon_Adapter(this,list,id_dh);
        rcv_Danhsach.setAdapter(adapter);
    }
}