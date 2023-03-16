package hieucdph29636.fpoly.tuhubread.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hieucdph29636.fpoly.tuhubread.fragment.CartFragment;
import hieucdph29636.fpoly.tuhubread.fragment.FoodFragment;
import hieucdph29636.fpoly.tuhubread.fragment.HomeFragment;
import hieucdph29636.fpoly.tuhubread.fragment.OtherFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new FoodFragment();
            case 2:
                return new CartFragment();
            default:
                return new OtherFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
