package hieucdph29636.fpoly.tuhubread.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonHangAdapter;

public class CartFragment extends Fragment {
    RecyclerView rcv;
    DonHangDAO dao;
    ArrayList<DonHang> list;
    DonHangAdapter adapter;
    String quyen;
    String taiKhoan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
         taiKhoan = sharedPreferences.getString("TK","");
        quyen = sharedPreferences.getString("quyen","");
        rcv = view.findViewById(R.id.rcv_cart);
        dao = new DonHangDAO();
        if (quyen.equalsIgnoreCase("nhanvien")){
            list = (ArrayList<DonHang>) dao.selectAll();
        }else {
            list = (ArrayList<DonHang>) dao.selectAllKH(taiKhoan);
        }
        adapter = new DonHangAdapter(list,getContext());
        rcv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (quyen.equalsIgnoreCase("nhanvien")){
            list = (ArrayList<DonHang>) dao.selectAll();
        }else {
            list = (ArrayList<DonHang>) dao.selectAllKH(taiKhoan);
        }
        adapter = new DonHangAdapter(list,getContext());
        rcv.setAdapter(adapter);
    }
}