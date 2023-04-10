package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.MonAnYeuThichDAO;
import hieucdph29636.fpoly.tuhubread.DTO.MonAnYeuThich;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.MonAnYeuThichAdapter;

public class MonAnYeuThichActivity extends AppCompatActivity {
    RecyclerView rcv ;
    ImageView btn_back_mayt;
    MonAnYeuThichAdapter adapter;

    ArrayList<MonAnYeuThich> list;
    MonAnYeuThichDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an_yeu_thich);
        rcv = findViewById(R.id.rcv_mayt);
        btn_back_mayt = findViewById(R.id.btn_back_mayt);
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        dao = new MonAnYeuThichDAO();
        list = dao.layTheoLoaiTK(taiKhoan);
        adapter = new MonAnYeuThichAdapter(MonAnYeuThichActivity.this,list);
        rcv.setAdapter(adapter);
        btn_back_mayt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}