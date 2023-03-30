package hieucdph29636.fpoly.tuhubread.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DAO.LoaiMonDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.Thuc_don;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_mon_Adapter;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_thucDon_Spinner_Adapter;

public class FoodFragment extends Fragment {
    LinearLayout linear_food;
    RecyclerView rcv_dsm;
    private Spinner spn_thucdon;
    private Ds_thucDon_Spinner_Adapter thucDon_adapter;
    LoaiMonDAO loaiMonDAO;
    MonAnDAO monAnDAO;
    private Ds_mon_Adapter ds_mon_adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_dsm = view.findViewById(R.id.rcv_dsm);
        linear_food = view.findViewById(R.id.linear_dm);

        loaiMonDAO = new LoaiMonDAO(getContext());
        monAnDAO = new MonAnDAO(getContext());
        List<String> list = new ArrayList<>();
        list.add("Bánh mì");
        list.add("Sandwich");
        list.add("Nước");
        spn_thucdon = view.findViewById(R.id.spn_thucdon);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, list);
        spn_thucdon.setAdapter(adapter);
        ArrayList<MonAn> listmon = monAnDAO.chonTheoLoai(1);
        ds_mon_adapter = new Ds_mon_Adapter(getContext(), listmon);
        rcv_dsm.setAdapter(ds_mon_adapter);


                spn_thucdon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                        int id = loaiMonDAO.getId(list.get(position));

                        ArrayList<MonAn> list = monAnDAO.chonTheoLoai(id);
                        ds_mon_adapter = new Ds_mon_Adapter(getContext(), list);
                        rcv_dsm.setAdapter(ds_mon_adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

}