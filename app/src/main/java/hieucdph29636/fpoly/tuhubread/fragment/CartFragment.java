package hieucdph29636.fpoly.tuhubread.fragment;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_cart);
        dao = new DonHangDAO(getContext());
        list = (ArrayList<DonHang>) dao.selectAll();
        adapter = new DonHangAdapter(list,getContext());
        rcv.setAdapter(adapter);

    }
}