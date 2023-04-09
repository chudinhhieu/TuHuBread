package hieucdph29636.fpoly.tuhubread.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.KhuyenMaiAdapter;

public class KhuyenMaiActivity extends AppCompatActivity {
    TextView tv_mota_km , tv_sotiengiam_km ;
    Button btn_chinhsua_km ;
    KhuyenMaiAdapter khuyenMaiAdapter;
    List<KhuyenMai> list ;
    KhuyenMaiDAO khuyenMaiDAO ;
    RecyclerView rcv_khuyenMai;
    FloatingActionButton btn_add_km;
    MyDate date;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khuyen_mai);
        rcv_khuyenMai = findViewById(R.id.rcv_khuyenMai);
        btn_add_km = findViewById(R.id.btn_add_km);
        khuyenMaiDAO = new KhuyenMaiDAO();
        tv_mota_km = findViewById(R.id.tv_mota_km);
        tv_sotiengiam_km = findViewById(R.id.tv_sotiengiam_km);
        btn_chinhsua_km = findViewById(R.id.btn_chinhsua_km);


        list = khuyenMaiDAO.getAll();
        khuyenMaiAdapter = new KhuyenMaiAdapter(this,list);
        rcv_khuyenMai.setAdapter(khuyenMaiAdapter);

        btn_add_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog mdDialog = new Dialog(KhuyenMaiActivity.this);
                mdDialog.setContentView(R.layout.layout_dialog_btnthem_ctkm);
                mdDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                TextInputEditText edCode,edMota,edNgayBD,edNgayKT,edSTgiam;
                MaterialButton btnCancel,btnAdd;
                edCode = mdDialog.findViewById(R.id.ed_dialog_add_code);
                edMota = mdDialog.findViewById(R.id.ed_dialog_add_mota);
                edNgayBD = mdDialog.findViewById(R.id.ed_dialog_add_ngaybatdau);
                edNgayKT = mdDialog.findViewById(R.id.ed_dialog_add_ngayketthuc);
                edSTgiam = mdDialog.findViewById(R.id.ed_dialog_add_sotiengiam);
                btnCancel = mdDialog.findViewById(R.id.btn_dialog_add_cancel);
                btnAdd = mdDialog.findViewById(R.id.btn_dialog_add_add);


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KhuyenMai objkm = new KhuyenMai(edCode.getText().toString(),edMota.getText().toString(),edNgayBD.getText().toString(),edNgayKT.getText().toString(),Integer.parseInt(edSTgiam.getText().toString()));

                        if(khuyenMaiDAO.insert(objkm)){
                            Toast.makeText(KhuyenMaiActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                            mdDialog.dismiss();
                        }else{
                            Toast.makeText(KhuyenMaiActivity.this, "Thất bại!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                edNgayBD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(KhuyenMaiActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                edNgayBD.setText(date.toStringVn(calendar.getTime()));
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });
                edNgayKT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(KhuyenMaiActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar.set(i, i1, i2);
                                edNgayKT.setText(date.toStringVn(calendar.getTime()));
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdDialog.dismiss();
                    }
                });
                mdDialog.show();
            }
        });
    }



}

