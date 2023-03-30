package hieucdph29636.fpoly.tuhubread.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTien_Adapter;

public class Ds_DonNapTien_Activity extends AppCompatActivity {
    private RecyclerView rcv_dnt;


    private DonNapTienDAO donNapTienDAO;
    private ArrayList<DonNapTien> dsdnt;
    private DonNapTien_Adapter donNapTien_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_don_nap_tien);

        rcv_dnt = findViewById(R.id.rcv_dnt);

        donNapTienDAO = new DonNapTienDAO(this);
        dsdnt = donNapTienDAO.getAll_donNapTien(this);
        donNapTien_adapter = new DonNapTien_Adapter(this,dsdnt);
        rcv_dnt.setAdapter(donNapTien_adapter);




    }


    @Override
    protected void onRestart() {
        super.onRestart();

        dsdnt = donNapTienDAO.getAll_donNapTien(this);
        donNapTien_adapter = new DonNapTien_Adapter(this,dsdnt);
        rcv_dnt.setAdapter(donNapTien_adapter);



    }
}