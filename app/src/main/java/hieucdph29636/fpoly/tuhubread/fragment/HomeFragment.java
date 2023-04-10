package hieucdph29636.fpoly.tuhubread.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhuyenMaiDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.PhotoSlideShow;
import hieucdph29636.fpoly.tuhubread.R;
import hieucdph29636.fpoly.tuhubread.adapter.KhuyenMaiHomeAdapter;
import hieucdph29636.fpoly.tuhubread.adapter.PhotoSlideShowAdapter;
import hieucdph29636.fpoly.tuhubread.adapter.SanPhamNoiBatAdapter;
import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoSlideShowAdapter adapterSlideShowAdapter;
    private List<PhotoSlideShow> listPhoto ;
    private RecyclerView rcv_spnb,rcv_km;
    private Timer timer;
    private MonAnDAO monAnDAO;
    private SanPhamNoiBatAdapter adapterSPNB;
    private KhuyenMaiDAO khuyenMaiDAO;
    private List<KhuyenMai> listKM;
    private KhuyenMaiHomeAdapter adapterKM;
    private KhachHangDAO khachHangDAO;
    private TextView tv_home1,tv_home2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        rcv_spnb = view.findViewById(R.id.rcv_spnb);
        rcv_km = view.findViewById(R.id.rcv_kmkh);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcv_spnb.setLayoutManager(layoutManager);
        rcv_km.setLayoutManager(layoutManager2);
        monAnDAO = new MonAnDAO();
        tv_home1 = view.findViewById(R.id.tv_home1);
        tv_home2 = view.findViewById(R.id.tv_home2);
        khuyenMaiDAO = new KhuyenMaiDAO();
        listKM = khuyenMaiDAO.getAll();
        khachHangDAO = new KhachHangDAO();
        adapterSPNB = new SanPhamNoiBatAdapter(monAnDAO.layTheoLoai(0,1),getContext());
        adapterKM = new KhuyenMaiHomeAdapter(listKM,getContext());
        rcv_spnb.setAdapter(adapterSPNB);
        rcv_km.setAdapter(adapterKM);
        listPhoto = getListPhoto();
        adapterSlideShowAdapter = new PhotoSlideShowAdapter(getContext(),listPhoto);
        viewPager.setAdapter(adapterSlideShowAdapter);
        circleIndicator.setViewPager(viewPager);
        adapterSlideShowAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        AutoSildePhoto();
        String hoTen= khachHangDAO.getHoTen(taiKhoan);
        tv_home1.setText("Sản phẩm nổi bật cho "+hoTen+"!");
        tv_home2.setText("Khuyến mãi dành cho "+hoTen+"!");
    }

    private List<PhotoSlideShow> getListPhoto() {
        List<PhotoSlideShow> listPhoto = new ArrayList<>();
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_1));
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_2));
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_3));
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_4));
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_5));
        listPhoto.add(new PhotoSlideShow(R.drawable.imgsl_6));
        return listPhoto;
    }
    private void AutoSildePhoto(){
        if (listPhoto==null||listPhoto.isEmpty()||viewPager==null){
            return;
        }
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int tongSlide = listPhoto.size()-1;
                        if (currentItem<tongSlide){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },5000,5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer = null;
        }
    }
}