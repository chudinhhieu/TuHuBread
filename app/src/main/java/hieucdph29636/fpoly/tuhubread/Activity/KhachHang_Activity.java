package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.KhachHangAdapter;

public class KhachHang_Activity extends AppCompatActivity {
    RecyclerView rcv_kh;
    KhachHangDAO khachHangDAO;
    KhachHangAdapter khachHangAdapter;
    ArrayList<KhachHang> listKh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        rcv_kh=findViewById(R.id.rcv_Khang);
        khachHangDAO=new KhachHangDAO(this);
        listKh= (ArrayList<KhachHang>) khachHangDAO.selectAll();
        khachHangAdapter=new KhachHangAdapter(this,listKh);
        rcv_kh.setAdapter(khachHangAdapter);

    }
}