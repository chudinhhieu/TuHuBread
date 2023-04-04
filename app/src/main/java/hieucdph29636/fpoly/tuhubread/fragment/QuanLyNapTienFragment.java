package hieucdph29636.fpoly.tuhubread.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTienKHAdapter;

public class QuanLyNapTienFragment extends Fragment {
    RecyclerView rcv;
    ArrayList<DonNapTien> list;
    DonNapTienKHAdapter adapter;
    DonNapTienDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_don_nap_tien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_qldnt);
        dao = new DonNapTienDAO();
        list = dao.getAll();
        adapter = new DonNapTienKHAdapter(list,getContext());
        rcv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = dao.getAll();
        adapter = new DonNapTienKHAdapter(list,getContext());
        rcv.setAdapter(adapter);
    }
}