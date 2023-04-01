package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.R;

public class ChiTiet_KhachHang extends AppCompatActivity {
    TextView tv_maKH,tv_nameKH,tv_taiKhoanKH,tv_dateKH,tv_passKH,tv_addressKH,tv_soDuTK_KH,tv_sdtKH;
    Button btn_deleteKH,btn_editKH;
    KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> listKH;
    KhachHang objKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khach_hang);
        tv_nameKH=findViewById(R.id.tv_nameKH);
        tv_taiKhoanKH=findViewById(R.id.tv_taiKhoanKH);
        tv_dateKH=findViewById(R.id.tv_dateKH);
        tv_passKH=findViewById(R.id.tv_passKH);
        tv_sdtKH=findViewById(R.id.tv_sdtKH);
        tv_addressKH=findViewById(R.id.tv_AddressKH);
        tv_soDuTK_KH=findViewById(R.id.tv_soDuTK_KH);
        Bundle bundle = getIntent().getExtras();
        String taiKhoan = bundle.getString("tv_taiKhoanKH");
        String matKhau = bundle.getString("tv_passKH");
        String nhaplaimatkahu = bundle.getString("tv_dateKH");
        String hoten =  bundle.getString("tv_nameKH");
        String ngaysinh = bundle.getString("tv_dateKH");
        String sdt = bundle.getString("tv_sdtKH");
        String diachi = bundle.getString("tv_Address");
        String sodu =  bundle.getInt("tv_soDuTK_KH")+"";

        tv_nameKH.setText(hoten);
        tv_taiKhoanKH.setText(taiKhoan);
        tv_dateKH.setText(ngaysinh);
        tv_passKH.setText(matKhau);
        tv_sdtKH.setText(sdt);
        tv_addressKH.setText(diachi);
        tv_soDuTK_KH.setText(sodu);
        khachHangDAO=new KhachHangDAO(this);

    }
}