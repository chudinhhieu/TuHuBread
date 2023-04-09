package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia_binh_luan_hien);
        rcv_Danhsach=findViewById(R.id.rcv_danhgia_binhLuan);
        dao=new DanhGiaDAO();
        list=dao.getAll();
        adapter=new Hien_DanhGia_mon_Adapter(this,list);
        rcv_Danhsach.setAdapter(adapter);
    }
}