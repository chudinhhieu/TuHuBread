package hieucdph29636.fpoly.tuhubread.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hieucdph29636.fpoly.tuhubread.Activity.DangNhapActivity;
import hieucdph29636.fpoly.tuhubread.Activity.Ds_DonNapTien_Activity;
import hieucdph29636.fpoly.tuhubread.Activity.Ds_nv_Activity;
import hieucdph29636.fpoly.tuhubread.Activity.KhachHang_Activity;
import hieucdph29636.fpoly.tuhubread.Activity.KhuyenMaiActivity;
import hieucdph29636.fpoly.tuhubread.Activity.ViTienActivity;
import hieucdph29636.fpoly.tuhubread.R;

public class OtherFragment extends Fragment {
    RelativeLayout qlkh_other,vitien_other,qlnv_other,qlkm_other,tk_other,tttk_other,dangXuat_other;
    TextView tv_other_3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String quyen = sharedPreferences.getString("quyen","");
        qlkh_other = view.findViewById(R.id.qlkh_other);
        vitien_other = view.findViewById(R.id.vitien_other);
        qlnv_other = view.findViewById(R.id.qlnv_other);
        qlkm_other = view.findViewById(R.id.qlkm_other);
        tk_other = view.findViewById(R.id.tk_other);
        tttk_other = view.findViewById(R.id.tttk_other);
        dangXuat_other = view.findViewById(R.id.dangXuat_other);
        tv_other_3 = view.findViewById(R.id.tv_other_3);

        if (quyen.equalsIgnoreCase("khachhang")){
            qlkh_other.setVisibility(View.GONE);
            qlnv_other.setVisibility(View.GONE);
            qlkm_other.setVisibility(View.GONE);
            tk_other.setVisibility(View.GONE);
        }
        if (quyen.equalsIgnoreCase("nhanvien")){
            vitien_other.setVisibility(View.GONE);
        }
        dangXuat_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
            }
        });
        vitien_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViTienActivity.class));
            }
        });
        qlnv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Ds_nv_Activity.class));
            }
        });
        qlkh_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KhachHang_Activity.class));
            }
        });
        qlkm_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KhuyenMaiActivity.class));
            }
        });
    }

}