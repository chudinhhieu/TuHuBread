package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe.DoanhThuFragment;
import hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe.DoanhThuMonFragment;
import hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe.DoanhThuMonTheoNgayFragment;
import hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe.DoanhThuTheoNgayFragment;
import hieucdph29636.fpoly.tuhubread.fragment.fragmentThongKe.MonAnBanChayFragment;

public class ActivityThongKe extends AppCompatActivity {
    Spinner spinner;
    ImageView btn_back_tk;
    DoanhThuTheoNgayFragment doanhThuTheoNgayFragment;
    DoanhThuMonTheoNgayFragment doanhThuMonTheoNgayFragment;
    MonAnBanChayFragment monAnBanChayFragment;
    DoanhThuMonFragment doanhThuMonFragment;
    DoanhThuFragment doanhThuFragment;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        spinner = findViewById(R.id.spn_tk);
        frameLayout= findViewById(R.id.frame_main);
        btn_back_tk= findViewById(R.id.btn_back_tk);
        doanhThuMonTheoNgayFragment = new DoanhThuMonTheoNgayFragment();
        doanhThuFragment = new DoanhThuFragment();
        doanhThuMonFragment = new DoanhThuMonFragment();
        monAnBanChayFragment = new MonAnBanChayFragment();
        doanhThuTheoNgayFragment = new DoanhThuTheoNgayFragment();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityThongKe.this,R.layout.item_spn_tk,getResources().getStringArray(R.array.fragmentTK));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btn_back_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        setFragment(doanhThuFragment);
                        break;
                    case 1:
                        setFragment(doanhThuTheoNgayFragment);
                        break;
                    case 2:
                        setFragment(monAnBanChayFragment);
                        break;
                    case 3:
                        setFragment(doanhThuMonFragment);
                        break;
                    case 4:
                        setFragment(doanhThuMonTheoNgayFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main,fragment);
        fragmentTransaction.commit();
    }
}