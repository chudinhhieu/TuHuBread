package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DonNapTienDAO;
import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.DonNapTien_Adapter;
import hieucdph29636.fpoly.tuhubread.adapter.NhanVienAdapter;
import hieucdph29636.fpoly.tuhubread.adapter.ViewPagerAdapter;

public class Chu_Activity extends AppCompatActivity {
    private RecyclerView rcv;

//    NhanVienDAO nhanVienDAO;
//    ArrayList<NhanVien> dsnv ;
//    NhanVienAdapter nhanVienAdapter;

    DonNapTienDAO donNapTienDAO;
    ArrayList<DonNapTien> dsdnt;
    DonNapTien_Adapter donNapTien_adapter;

    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu);
        //todo: nếu muốn màn hình chủ giống khách or something? hãy sửa lại phần layout dòng trên

           rcv = findViewById(R.id.rcv);
//            nhanVienDAO = new NhanVienDAO(this);
//            dsnv = nhanVienDAO.getAll_nv(this);
//            nhanVienAdapter = new NhanVienAdapter(this,dsnv);
//            rcv.setAdapter(nhanVienAdapter);


        donNapTienDAO = new DonNapTienDAO(this);
        dsdnt = donNapTienDAO.getAll_donNapTien(this);
        donNapTien_adapter = new DonNapTien_Adapter(this,dsdnt);
        rcv.setAdapter(donNapTien_adapter);




           // viewPager = findViewById(R.id.view_pager1);
            bottomNavigationView = findViewById(R.id.bottom_nav1);
//            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//            viewPager.setAdapter(adapter);

//        nhanVienDAO.getAll_nv(this);
        //    dsnv = NhanVienDAO.getAll_nv(Chu_Activity.this);

//            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//
//
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    switch (position){
//                        case 0:
//                            bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
//                            break;
//                        case 1:
//                            bottomNavigationView.getMenu().findItem(R.id.action_food).setChecked(true);
//                            break;
//                        case 2:
//                            bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
//                            break;
//                        case 3:
//                            bottomNavigationView.getMenu().findItem(R.id.action_other).setChecked(true);
//                            break;
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.action_home:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.action_food:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.action_cart:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.action_other:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                    return true;
                }
            });
        }

    }