package hieucdph29636.fpoly.tuhubread.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.Activity.ThemMonAnActivity;
import hieucdph29636.fpoly.tuhubread.DAO.LoaiMonDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.LoaiMon;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_mon_Adapter;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_thucDon_Spinner_Adapter;

public class FoodFragment extends Fragment {
    ImageView btn_tkma;
    LinearLayout linear_food;
    RecyclerView rcv_dsm;
    private Spinner spn_thucdon;
    private Ds_thucDon_Spinner_Adapter thucDon_adapter;
    LoaiMonDAO loaiMonDAO;
    MonAnDAO monAnDAO;
    FloatingActionButton btn_themMon;
    private Ds_mon_Adapter ds_mon_adapter;
    private ArrayList<LoaiMon> list;
    ArrayList<MonAn> listmon;
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
        loaiMonDAO = new LoaiMonDAO();
        monAnDAO = new MonAnDAO();
        spn_thucdon = view.findViewById(R.id.spn_thucdon);
        spn_thucdon = view.findViewById(R.id.spn_thucdon);
        spn_thucdon = view.findViewById(R.id.spn_thucdon);
        btn_tkma = view.findViewById(R.id.btn_tkma);
        btn_themMon = view.findViewById(R.id.btn_themMon);
        list = (ArrayList<LoaiMon>) loaiMonDAO.getAll();
        thucDon_adapter = new Ds_thucDon_Spinner_Adapter(getContext(),list);
        spn_thucdon.setAdapter(thucDon_adapter);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        if (quyen.equalsIgnoreCase("khachhang")){
            btn_themMon.setVisibility(View.GONE);
        }else {
            btn_themMon.setVisibility(View.VISIBLE);
        }
        btn_tkma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.activity_search_share);
                TextInputEditText tied = dialog.findViewById(R.id.ed_search);
                TextInputLayout til = dialog.findViewById(R.id.edL_search);
                TextView btn_xacnhan = dialog.findViewById(R.id.btn_xacNhan_searchKH);
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tied.getText().toString().isEmpty()){
                            til.setError("Không được để trống");
                            return;
                        }else {
                            til.setErrorEnabled(false);
                        }


                        ArrayList<MonAn> list1 = monAnDAO.timKiemMonAnTheoTen(tied.getText().toString().trim(),1);
                        ds_mon_adapter = new Ds_mon_Adapter(getContext(),list1);
                        rcv_dsm.setAdapter(ds_mon_adapter);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btn_themMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ThemMonAnActivity.class));
            }
        });
        if (quyen.equalsIgnoreCase("khachhang")){
            listmon = monAnDAO.layTheoLoai(0,1);
        }else {
            listmon = monAnDAO.layTheoLoaiNV(0);
        }
        ds_mon_adapter = new Ds_mon_Adapter(getContext(), listmon);
        rcv_dsm.setAdapter(ds_mon_adapter);

                spn_thucdon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        ArrayList<MonAn> listMon;
                        if (quyen.equalsIgnoreCase("khachhang")){
                             listMon = monAnDAO.layTheoLoai(position,1);
                        }else {
                             listMon = monAnDAO.layTheoLoaiNV(position);
                        }
                        ds_mon_adapter = new Ds_mon_Adapter(getContext(), listMon);
                        rcv_dsm.setAdapter(ds_mon_adapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
    @Override
    public void onResume() {
        super.onResume();
        ArrayList<MonAn> listmon = monAnDAO.layTheoLoai(0,1);
        ds_mon_adapter = new Ds_mon_Adapter(getContext(), listmon);
        rcv_dsm.setAdapter(ds_mon_adapter);
    }
}