package hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.ThongKeDAO;
import hieucdph29636.fpoly.tuhubread.DTO.TopDoanhThuMonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.ThongKe_doanhThuMAdapter;

public class DoanhThuMonTheoNgayFragment extends Fragment {

    ThongKeDAO dao;
    ThongKe_doanhThuMAdapter adapter;
    RecyclerView rcv;
    Button btn_tongDThuMATN;
    TextInputEditText ed_ngayBD_TKMA,ed_ngayKT_TKMA;
    TextInputLayout edL_ngayBD_TKMA,edL_ngayKT_TKMA;
    String tuNgay,denNgay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doanh_thu_mon_theo_ngay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv=view.findViewById(R.id.rcv_DThu_mon_theoNgay);
        btn_tongDThuMATN = view.findViewById(R.id.btn_tongDThuMATN);
        ed_ngayBD_TKMA = view.findViewById(R.id.ed_ngayBD_TKMA);
        ed_ngayKT_TKMA = view.findViewById(R.id.ed_ed_ngayKT_TKMA);
        edL_ngayKT_TKMA = view.findViewById(R.id.edL_ed_ngayKT_TKMA);
        edL_ngayBD_TKMA = view.findViewById(R.id.edL_ed_ngayBD_TKMA);
        dao= new ThongKeDAO();
        ed_ngayBD_TKMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam=i;
                        int thang=i1;
                        int ngay=i2;
                        ed_ngayBD_TKMA.setText(ngay+"/"+(thang+1)+"/"+nam);
                        tuNgay = nam+"-"+(thang+1)+"-"+ngay;
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog.show();
            }
        });
        ed_ngayKT_TKMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar= Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam=i;
                        int thang=i1;
                        int ngay=i2;
                        ed_ngayKT_TKMA.setText(ngay+"/"+(thang+1)+"/"+nam);
                        denNgay = nam+"-"+(thang+1)+"-"+ngay;
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog.show();
            }
        });
        btn_tongDThuMATN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_ngayBD_TKMA.getText().toString().isEmpty()){
                    edL_ngayBD_TKMA.setError("Vui lòng chọn ngày!");
                    return;
                }else {
                    edL_ngayBD_TKMA.setErrorEnabled(false);
                }
                if (ed_ngayKT_TKMA.getText().toString().isEmpty()){
                    edL_ngayKT_TKMA.setError("Vui lòng chọn ngày!");
                    return;
                }else {
                    edL_ngayKT_TKMA.setErrorEnabled(false);
                }
                ArrayList<TopDoanhThuMonAn> list =dao.topDoanhThuMonAnTheoNgay(tuNgay,denNgay);
                adapter=new ThongKe_doanhThuMAdapter(getContext(),list);
                rcv.setAdapter(adapter);
            }
        });


    }
}