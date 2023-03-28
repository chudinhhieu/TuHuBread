package hieucdph29636.fpoly.tuhubread.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hieucdph29636.fpoly.tuhubread.Activity.Ds_DonNapTien_Activity;
import hieucdph29636.fpoly.tuhubread.Activity.Ds_nv_Activity;
import hieucdph29636.fpoly.tuhubread.R;

public class OtherFragment extends Fragment {

    RelativeLayout qlnv_other,vitien_other;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other,container,false);
        return view;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        qlnv_other = view.findViewById(R.id.qlnv_other);
        vitien_other = view.findViewById(R.id.vitien_other);

        qlnv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Ds_nv_Activity.class);
                startActivity(intent);
            }
        });

        vitien_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Ds_DonNapTien_Activity.class);
                startActivity(intent);
            }
        });


    }
}