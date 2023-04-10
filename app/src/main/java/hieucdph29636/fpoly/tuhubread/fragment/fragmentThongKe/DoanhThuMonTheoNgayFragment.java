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

public class DoanhThuMonTheoNgayFragment extends Fragment {

    ThongKeDAO dao;
    ThongKe_doanhThuMAdapter adapter;
    RecyclerView rcv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doanh_thu_mon_theo_ngay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv=view.findViewById(R.id.rcv_DThu_mon_theoNgay);
        dao= new ThongKeDAO();
        ArrayList<TopDoanhThuMonAn> list =dao.topDoanhThuMonAnTheoNgay("2023-04-01","2023-04-07");
        adapter=new ThongKe_doanhThuMAdapter(getContext(),list);
        rcv.setAdapter(adapter);
//        if (list.isEmpty()){
//            Log.d("tttttt", "null");
//        }
//        for (int i = 0; i < list.size(); i++) {
//            Log.d("tttttt", "Tên: "+list.get(i).getTen());
//            Log.d("tttttt", "Doanh thu: "+list.get(i).getDoanhThu()+"");
//        }
    }
}