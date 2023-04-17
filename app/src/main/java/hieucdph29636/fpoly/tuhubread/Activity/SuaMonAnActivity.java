package hieucdph29636.fpoly.tuhubread.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.LoaiMonDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.LoaiMon;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_thucDon_Spinner_Adapter;

public class SuaMonAnActivity extends AppCompatActivity {
    TextInputLayout lmonAn,lthanhPhan,lgia;
    TextInputEditText emonAn,ethanhphan,egia;
    TextView tv_btn_suama;
    RadioButton rdoBan,rdoNgung;
    Spinner spinner;
    ImageView img_back,img_anhMon;
    CardView btn_xacNhan;
    Ds_thucDon_Spinner_Adapter adapterSPN;
    LoaiMonDAO daoLoaiMon;
    ArrayList<LoaiMon> listLoaiMon;
    MonAn monAn = new MonAn();
    MonAnDAO monAnDAO ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        lmonAn = findViewById(R.id.edL_tenMon);
        tv_btn_suama = findViewById(R.id.tv_btn_suama);
        lthanhPhan = findViewById(R.id.edL_thanhPhan);
        lgia = findViewById(R.id.edL_giaMon);
        emonAn = findViewById(R.id.ed_tenMon);
        ethanhphan = findViewById(R.id.ed_thanhPhan);
        egia = findViewById(R.id.ed_giaMon);
        rdoBan = findViewById(R.id.rdoBan);
        rdoNgung = findViewById(R.id.rdoNgungBan);
        spinner = findViewById(R.id.spn_loaiMon);
        img_anhMon = findViewById(R.id.img_themAnhMonAn);
        img_back = findViewById(R.id.btn_back_themMon);
        btn_xacNhan = findViewById(R.id.btn_XacNhanThemMon);
        daoLoaiMon = new LoaiMonDAO();
        monAnDAO = new MonAnDAO();
        listLoaiMon = daoLoaiMon.getAll();
        adapterSPN = new Ds_thucDon_Spinner_Adapter(this,listLoaiMon);
        spinner.setAdapter(adapterSPN);
        Bundle bundle  = getIntent().getExtras();
        int idSua = bundle.getInt("idMon");
        MonAn monAn = monAnDAO.getById(idSua);
        spinner.setSelection(monAn.getId_LoaiDoAn());
        emonAn.setText(monAn.getTenMon());
        ethanhphan.setText(monAn.getThanhPhan());
        tv_btn_suama.setText("Sửa món ăn");
        egia.setText(monAn.getGia()+"");

        if (monAn.getTrangThai()==0){
            rdoNgung.setChecked(true);
        }else {
            rdoBan.setChecked(true);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getAnhMonAn(), 0, monAn.getAnhMonAn().length);
        img_anhMon.setImageBitmap(bitmap);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monAn.setId_LoaiDoAn(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        img_anhMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        btn_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = emonAn.getText().toString();
                String gia = egia.getText().toString();
                String thanhPhan = ethanhphan.getText().toString();
                // check tên món ăn thành phần, gia
                if (ten.isEmpty()){
                    lmonAn.setError("Chưa nhập tên");
                    return;
                }
                else {
                    lmonAn.setErrorEnabled(false);
                }
                if (thanhPhan.isEmpty()){
                    lthanhPhan.setError("Chưa nhập loại thành phần");
                    return;
                }else{
                    lthanhPhan.setErrorEnabled(false);
                }
                if (egia.getText().toString().isEmpty()){
                    lgia.setError("Chưa nhập giá");
                    return;
                } else {
                    lgia.setErrorEnabled(false);
                }
                try {
                    if(monAn.getAnhMonAn().length == 0){
                        Toast.makeText(SuaMonAnActivity.this, "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){
                    Toast.makeText(SuaMonAnActivity.this, "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!rdoBan.isChecked() && !rdoNgung.isChecked()){
                    Toast.makeText(SuaMonAnActivity.this, "Vui lòng chọn trạng thái", Toast.LENGTH_SHORT).show();
                    return;
                }
                // ..............
                if (rdoBan.isChecked()){
                    monAn.setTrangThai(1);
                }
                if (rdoNgung.isChecked()){
                    monAn.setTrangThai(0);
                }
                monAn.setThanhPhan(thanhPhan);
                monAn.setGia(Integer.parseInt(gia));
                monAn.setTenMon(ten);
                monAn.setId_MonAn(idSua);
                if (monAn.getAnhMonAn()==null){
                    return;
                }
                if(monAnDAO.update(monAn)){
                    Toast.makeText(SuaMonAnActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            img_anhMon.setImageURI(imageUri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                monAn.setAnhMonAn(bytes);
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}