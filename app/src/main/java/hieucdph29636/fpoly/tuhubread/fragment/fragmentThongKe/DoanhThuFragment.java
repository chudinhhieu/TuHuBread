package hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hieucdph29636.fpoly.tuhubread.DAO.ThongKeDAO;
import hieucdph29636.fpoly.tuhubread.R;

public class DoanhThuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_tongDoanhThu;
        tv_tongDoanhThu=view.findViewById(R.id.tv_tongDoanhThu);
        ThongKeDAO dao=new ThongKeDAO();
        tv_tongDoanhThu.setText(dao.tongDoanhThu()+" VND");
    }
}