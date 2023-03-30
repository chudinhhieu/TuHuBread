package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.R;

public class Chi_Tiet_Khuyen_Mai extends AppCompatActivity {
    TextView tv_id_km , tv_code_km , tv_mota_km , tv_ngaybatdau_km , tv_ngayketthuc_km , tv_sotiengiam_km ;
    Button btn_chinhsua_km , btn_xoa_km;

    KhuyenMaiDAO khuyenMaiDAO;
    

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khuyen_mai);

        khuyenMaiDAO = new KhuyenMaiDAO(this);
        tv_id_km = findViewById(R.id.tv_id_km);
        tv_code_km = findViewById(R.id.tv_code_km);
        tv_mota_km = findViewById(R.id.tv_mota_km);
        tv_ngaybatdau_km = findViewById(R.id.tv_ngaybatdau_km);
        tv_ngayketthuc_km = findViewById(R.id.tv_ngayketthuc_km);
        tv_sotiengiam_km = findViewById(R.id.tv_sotiengiam_km);
        btn_chinhsua_km = findViewById(R.id.btn_chinhsua_km);
        btn_xoa_km = findViewById(R.id.btn_xoa_km);


        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("tv_id");
        int sotiengiam = bundle.getInt("tv_sotiengiam_km");
        String code = bundle.getString("tv_code_km"+"");
        String mota = bundle.getString("tv_mota_km"+"");
        String ngayBD = bundle.getString("tv_ngaybatdau_km"+"");
        String ngayKT = bundle.getString("tv_ngayketthuc_km"+"");


        tv_id_km.setText(id+"");

        tv_code_km.setText(code);
        tv_mota_km.setText(mota);
        tv_ngaybatdau_km.setText(ngayBD);
        tv_ngayketthuc_km.setText(ngayKT);
        tv_sotiengiam_km.setText(sotiengiam+"");
        KhuyenMai objkm = new KhuyenMai(id,code,mota,ngayBD,ngayKT,sotiengiam);
        btn_xoa_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(khuyenMaiDAO.deleteKMai(objkm)>0){
                    Toast.makeText(Chi_Tiet_Khuyen_Mai.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    khuyenMaiDAO.selectAll();
                    onBackPressed();

                }

            }
        });
        btn_chinhsua_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog mdialog = new Dialog(Chi_Tiet_Khuyen_Mai.this);
                mdialog.setContentView(R.layout.layout_dialog_btnsua_ctkm);
                TextInputEditText edCode,edMota,edNgayBD,edNgayKT,edSTgiam;
                MaterialButton btnCancel,btnUpdate;
                edCode = mdialog.findViewById(R.id.ed_dialog_update_code);
                edMota = mdialog.findViewById(R.id.ed_dialog_update_mota);
                edNgayBD = mdialog.findViewById(R.id.ed_dialog_update_ngaybatdau);
                edNgayKT = mdialog.findViewById(R.id.ed_dialog_update_ngayketthuc);
                edSTgiam = mdialog.findViewById(R.id.ed_dialog_update_sotiengiam);
                btnCancel = mdialog.findViewById(R.id.btn_dialog_update_cancel);
                btnUpdate = mdialog.findViewById(R.id.btn_dialog_update_update);

                edCode.setText(code);
                edMota.setText(mota);
                edNgayBD.setText(ngayBD);
                edNgayKT.setText(ngayKT);
                edSTgiam.setText(sotiengiam+"");
                KhuyenMai objkm = new KhuyenMai(id,edCode.getText().toString(),edMota.getText().toString(),edNgayBD.getText().toString(),edNgayKT.getText().toString(),Integer.parseInt(edSTgiam.getText().toString()));
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(khuyenMaiDAO.updateKMai(objkm)>0){
                            tv_id_km.setText(id+"");
                            tv_code_km.setText(edCode.getText().toString());
                            tv_mota_km.setText(edMota.getText().toString());
                            tv_ngaybatdau_km.setText(edNgayBD.getText().toString());
                            tv_ngayketthuc_km.setText(edNgayKT.getText().toString());
                            tv_sotiengiam_km.setText(edSTgiam.getText().toString()+"");
                            Toast.makeText(Chi_Tiet_Khuyen_Mai.this, "Thành công!", Toast.LENGTH_SHORT).show();
                            mdialog.dismiss();
                        }else{
                            Toast.makeText(Chi_Tiet_Khuyen_Mai.this, "Thất bại!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdialog.dismiss();
                    }
                });
                mdialog.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("tv_id");
        int sotiengiam = bundle.getInt("tv_sotiengiam_km");
        String code = bundle.getString("tv_code_km"+"");
        String mota = bundle.getString("tv_mota_km"+"");
        String ngayBD = bundle.getString("tv_ngaybatdau_km"+"");
        String ngayKT = bundle.getString("tv_ngayketthuc_km"+"");


        tv_id_km.setText(id+"");

        tv_code_km.setText(code);
        tv_mota_km.setText(mota);
        tv_ngaybatdau_km.setText(ngayBD);
        tv_ngayketthuc_km.setText(ngayKT);
        tv_sotiengiam_km.setText(sotiengiam+"");
    }
}