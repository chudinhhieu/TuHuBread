package hieucdph29636.fpoly.tuhubread.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hieucdph29636.fpoly.tuhubread.MyDate;
import hieucdph29636.fpoly.tuhubread.R;

public class Ds_MonAn extends AppCompatActivity {
    private RecyclerView rcv_bm;
    private FloatingActionButton flbtn_add_mon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_mon_an);

        rcv_bm = findViewById(R.id.rcv_bm);
        flbtn_add_mon = findViewById(R.id.flbtn_add_mon);

    }
}