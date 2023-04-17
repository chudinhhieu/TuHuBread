package hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import hieucdph29636.fpoly.tuhubread.DAO.ThongKeDAO;
import hieucdph29636.fpoly.tuhubread.R;


public class DoanhThuTheoNgayFragment extends Fragment {
    ThongKeDAO dao;
    String tuNgay,denNgay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doanh_thu_theo_ngay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText edtStart = view.findViewById(R.id.ed_ngayBD);
        TextInputEditText edtEnd = view.findViewById(R.id.ed_ngayKT);
        TextInputLayout edtLStart = view.findViewById(R.id.edL_ngayBD);
        TextInputLayout edtLEnd = view.findViewById(R.id.edL_ngayKT);
        Button btnThongKe = view.findViewById(R.id.btn_tongDThu);
        TextView txtKetQua = view.findViewById(R.id.txtKetQua);



        edtStart.setOnClickListener(new View.OnClickListener() {
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
                        edtStart.setText(ngay+"/"+(thang+1)+"/"+nam);
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
        edtEnd.setOnClickListener(new View.OnClickListener() {
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
                        edtEnd.setText(ngay+"/"+(thang+1)+"/"+nam);
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

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtStart.getText().toString().isEmpty()){
                    edtLStart.setError("Vui lòng chọn ngày!");
                    return;
                }else {
                    edtLStart.setErrorEnabled(false);
                }
                if (edtEnd.getText().toString().isEmpty()){
                    edtLEnd.setError("Vui lòng chọn ngày!");
                    return;
                }else {
                    edtLEnd.setErrorEnabled(false);
                }
                dao=new ThongKeDAO();
                txtKetQua.setText(dao.tongDoanhThuTheoNgay(tuNgay,denNgay) + " VND");
            }
        });

    }
}