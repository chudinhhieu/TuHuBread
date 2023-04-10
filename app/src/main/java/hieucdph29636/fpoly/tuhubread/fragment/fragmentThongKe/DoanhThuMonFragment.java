package hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.ThongKeDAO;
import hieucdph29636.fpoly.tuhubread.DTO.TopDoanhThuMonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.ThongKe_doanhThuMAdapter;

public class DoanhThuMonFragment extends Fragment {
    ThongKeDAO dao;
    ThongKe_doanhThuMAdapter adapter;
    RecyclerView rcv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu_mon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv=view.findViewById(R.id.rcv_DoanhThu_MonAn);
        dao= new ThongKeDAO();
        ArrayList<TopDoanhThuMonAn> list =dao.topDoanhThuMonAn();
        adapter=new ThongKe_doanhThuMAdapter(getContext(),list);
        rcv.setAdapter(adapter);
    }
}