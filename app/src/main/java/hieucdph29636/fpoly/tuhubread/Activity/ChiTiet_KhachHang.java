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
        tv_maKH=findViewById(R.id.tv_maKH);
        tv_nameKH=findViewById(R.id.tv_nameKH);
        tv_taiKhoanKH=findViewById(R.id.tv_taiKhoanKH);
        tv_dateKH=findViewById(R.id.tv_dateKH);
        tv_passKH=findViewById(R.id.tv_passKH);
        tv_sdtKH=findViewById(R.id.tv_sdtKH);
        btn_editKH = findViewById(R.id.btn_editKH);
        btn_deleteKH = findViewById(R.id.btn_deleteKH);
        tv_addressKH=findViewById(R.id.tv_AddressKH);
        tv_soDuTK_KH=findViewById(R.id.tv_soDuTK_KH);
        khachHangDAO=new KhachHangDAO(getApplicationContext());
        btn_editKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_deleteKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Bạn có muốn xóa không?");
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        khachHangDAO.deleteKhachHang(objKH);
//                        khachHangDAO.close();
//                        listKH.remove(objKH);
//                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }
}