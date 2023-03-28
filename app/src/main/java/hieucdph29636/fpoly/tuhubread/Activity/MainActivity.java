package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    private TextView tv_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
                        tv_toolbar.setText("Hiếu ơi,Bánh mì đi!");
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_food).setChecked(true);
                        tv_toolbar.setText("Danh sách");
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        tv_toolbar.setText("Đơn hàng");
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_other).setChecked(true);
                        tv_toolbar.setText("Khác");
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