package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia_mon);
        rcv_Danhsach=findViewById(R.id.rcv_danhgia_mon);
        dao=new DonHangDAO();
        Bundle bundle=getIntent().getExtras();
        int id_dh=bundle.getInt("id dh");
        list=dao.getMonAnInDonHang(id_dh);
        adapter=new DanhGia_mon_Adapter(this,list);
        rcv_Danhsach.setAdapter(adapter);
    }
}