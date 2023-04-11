package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.Ds_mon_Adapter;
import hieucdph29636.fpoly.tuhubread.adapter.ViewPagerAdapter;
import hieucdph29636.fpoly.tuhubread.fragment.FoodFragment;
import hieucdph29636.fpoly.tuhubread.fragment.OtherFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ImageView btn_ds_mayt;
    BottomNavigationView bottomNavigationView;
    private TextView tv_toolbar;
    KhachHangDAO khachHangDAO;
    DonHangDAO donHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        btn_ds_mayt = findViewById(R.id.btn_ds_mayt);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        khachHangDAO = new KhachHangDAO();
        donHangDAO = new DonHangDAO();
        SharedPreferences sharedPreferences = getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        String hoTen= khachHangDAO.getHoTen(taiKhoan);
        if (quyen.equalsIgnoreCase("khachhang")){
            tv_toolbar.setText(hoTen+" ơi,Bánh mì đi!");
            btn_ds_mayt.setVisibility(View.VISIBLE);
        }
        if (quyen.equalsIgnoreCase("nhanvien")){
            tv_toolbar.setText("Danh sách");
            btn_ds_mayt.setVisibility(View.GONE);
        }
        btn_ds_mayt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MonAnYeuThichActivity.class));
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.getQuyen(quyen);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        if (quyen.equalsIgnoreCase("khachhang")){
                            tv_toolbar.setText(hoTen+" ơi,Bánh mì đi!");
                            btn_ds_mayt.setVisibility(View.VISIBLE);
                        }
                        if (quyen.equalsIgnoreCase("nhanvien")){
                            tv_toolbar.setText("Danh sách");
                            btn_ds_mayt.setVisibility(View.GONE);
                            bottomNavigationView.getMenu().findItem(R.id.action_home).setTitle("Danh sách");
                            bottomNavigationView.getMenu().findItem(R.id.action_home).setIcon(R.drawable.icon_food_nav);
                        }
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_food).setChecked(true);
                        if (quyen.equalsIgnoreCase("khachhang")){
                            tv_toolbar.setText("Danh sách");
                            btn_ds_mayt.setVisibility(View.VISIBLE);
                        }
                        if (quyen.equalsIgnoreCase("nhanvien")){
                            btn_ds_mayt.setVisibility(View.GONE);
                            tv_toolbar.setText("Đơn hàng hiện tại");
                            bottomNavigationView.getMenu().findItem(R.id.action_food).setTitle("Đơn hàng");
                            bottomNavigationView.getMenu().findItem(R.id.action_food).setIcon(R.drawable.icon_cart_nav);
                        }
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        if (quyen.equalsIgnoreCase("khachhang")){
                            tv_toolbar.setText("Đơn hàng");
                            btn_ds_mayt.setVisibility(View.VISIBLE);
                        }
                        if (quyen.equalsIgnoreCase("nhanvien")){
                            btn_ds_mayt.setVisibility(View.GONE);
                            tv_toolbar.setText("Đơn nạp tiền");
                            bottomNavigationView.getMenu().findItem(R.id.action_cart).setTitle("Lịch sử");
                            bottomNavigationView.getMenu().findItem(R.id.action_cart).setIcon(R.drawable.icon_cart_nav);

                        }
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_other).setChecked(true);
                        tv_toolbar.setText("Khác");
                        btn_ds_mayt.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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