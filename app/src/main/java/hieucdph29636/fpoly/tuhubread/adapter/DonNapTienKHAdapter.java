package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_Don_nap_tien;
import hieucdph29636.fpoly.tuhubread.Activity.DonHangChiTietActivity;
import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;

public class DonNapTienKHAdapter extends RecyclerView.Adapter<DonNapTienKHAdapter.MyViewHolder> {
    View view;
    KhachHangDAO dao;
    private ArrayList<DonNapTien> list;
    private Context context;
    public DonNapTienKHAdapter(ArrayList<DonNapTien> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vi_tien,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonNapTien donNapTien = list.get(position);
        dao = new KhachHangDAO();
        SharedPreferences sharedPreferences = context.getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String quyen = sharedPreferences.getString("quyen","");
       int trangThai = donNapTien.getTrangThai();
       switch (trangThai){
           case 0:
               holder.trangThai.setText("Chờ xác nhận");
               break;
           case 1:
               holder.trangThai.setText("Thành công");
               break;
           case 2:
               holder.trangThai.setText("Hủy");
               break;
       }
       holder.time.setText(donNapTien.getThoiGianTao());
       if (quyen.equalsIgnoreCase("khachhang")){
           holder.mota.setText(donNapTien.getMota());
       }else {
           holder.mota.setText(dao.getHoTen(donNapTien.getTaiKhoan()));
       }
        holder.soTien.setText(donNapTien.getTienNap()+"");
        Intent intent = new Intent(context, ChiTiet_Don_nap_tien.class);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DonNapTien",donNapTien);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mota,time,soTien,trangThai;
        Button btn_xacNhan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mota = itemView.findViewById(R.id.tv_motadnt);
            time = itemView.findViewById(R.id.tv_timednt);
            soTien = itemView.findViewById(R.id.tv_tiendnt);
            trangThai = itemView.findViewById(R.id.tv_trangThaidnt);
            btn_xacNhan = itemView.findViewById(R.id.btn_xacNhanNapTien);
        }
    }
}
