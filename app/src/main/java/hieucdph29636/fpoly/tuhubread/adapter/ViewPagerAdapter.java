package hieucdph29636.fpoly.tuhubread.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hieucdph29636.fpoly.tuhubread.fragment.CartFragment;
import hieucdph29636.fpoly.tuhubread.fragment.FoodFragment;
import hieucdph29636.fpoly.tuhubread.fragment.HomeFragment;
import hieucdph29636.fpoly.tuhubread.fragment.QuanLyNapTienFragment;
import hieucdph29636.fpoly.tuhubread.fragment.OtherFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    String quyen="";
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (quyen.equalsIgnoreCase("nhanvien")){
                    return new FoodFragment();
                }
                if (quyen.equalsIgnoreCase("khachhang")){
                    return new HomeFragment();
                }
            case 1:
                if (quyen.equalsIgnoreCase("khachhang")){
                    return new FoodFragment();
                }
                if (quyen.equalsIgnoreCase("nhanvien")) {
                    return new CartFragment();
                }
            case 2:
                if (quyen.equalsIgnoreCase("khachhang")) {
                    return new CartFragment();
                }
                if (quyen.equalsIgnoreCase("nhanvien")) {
                    return new QuanLyNapTienFragment();
                }
            default:
                return new OtherFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
    public void getQuyen(String quyen){
        this.quyen = quyen;
    }
}
