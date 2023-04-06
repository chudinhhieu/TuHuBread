package hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.ThongKeDAO;
import hieucdph29636.fpoly.tuhubread.DTO.TopDoanhThuMonAn;
import hieucdph29636.fpoly.tuhubread.DTO.TopMonBanChay;
import hieucdph29636.fpoly.tuhubread.R;

public class DoanhThuMonFragment extends Fragment {
    ThongKeDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu_mon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao= new ThongKeDAO();
        ArrayList<TopDoanhThuMonAn> list =dao.topDoanhThuMonAn();
        for (int i = 0; i < list.size(); i++) {
            Log.d("tttttt", "Tên: "+list.get(i).getTen());
            Log.d("tttttt", "Doanh thu: "+list.get(i).getDoanhThu()+"");
        }
    }
}