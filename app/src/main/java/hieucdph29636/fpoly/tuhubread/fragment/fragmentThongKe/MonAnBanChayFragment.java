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
import hieucdph29636.fpoly.tuhubread.DTO.TopMonBanChay;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.ThongkeAdapter;

public class MonAnBanChayFragment extends Fragment {
    ThongKeDAO dao;
    ThongkeAdapter adapter;
    ArrayList<TopMonBanChay> list;
    RecyclerView rcv_Top;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mon_an_ban_chay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_Top=view.findViewById(R.id.rcv_MonTop);
        dao= new ThongKeDAO();
         list =dao.topMonBanChay();
         adapter=new ThongkeAdapter(getActivity(),list);
         rcv_Top.setAdapter(adapter);
    }
}