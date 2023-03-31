package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.DatMonActivity;
import hieucdph29636.fpoly.tuhubread.Activity.DonHangChiTietActivity;
import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    View view;
    private ArrayList<DonHang> list;
    private Context context;
    private ChiTietDonHangDAO chiTietDonHangDAO;
    public DonHangAdapter(ArrayList<DonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String quyen = sharedPreferences.getString("quyen","");
        chiTietDonHangDAO =new ChiTietDonHangDAO(context);
        int index = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DonHangChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_dh",list.get(index).getId_DonHang());
                bundle.putInt("trangThai_dh",list.get(index).getTrangThai());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btn_danhgia.setVisibility(View.GONE);
        int trangThai = list.get(position).getTrangThai();
        switch (trangThai){
            case 0:
                holder.trangthai.setText("Chờ thanh toán");
                break;
                case 1:
                holder.trangthai.setText("Chờ xác nhận");
                break;
                case 2:
                holder.trangthai.setText("Đang làm");
                break;
                case 3:
                holder.trangthai.setText("Đang giao hàng");
                    break;
                case 4:
                holder.trangthai.setText("Thành công");
                    if (quyen.equalsIgnoreCase("khachhang")){
                        holder.btn_danhgia.setVisibility(View.VISIBLE);
                    }
                    break;
                case 5:
                holder.trangthai.setText("Hủy");
                break;
        }
        if (chiTietDonHangDAO.getSoLuongChiTiet(list.get(position).getId_DonHang())>1){
            holder.tenMon.setText(chiTietDonHangDAO.getTenMonAn(list.get(position).getId_DonHang())+" + "+(chiTietDonHangDAO.getSoLuongChiTiet(list.get(position).getId_DonHang())-1)+" món ăn khác");
        }else {
            holder.tenMon.setText(chiTietDonHangDAO.getTenMonAn(list.get(position).getId_DonHang()));
        }
        holder.gia.setText(chiTietDonHangDAO.tinhTongGiaTienTheoIdDonHang(list.get(position).getId_DonHang())+"");
        holder.thoigian.setText(list.get(position).getThoiGianTao());
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView trangthai,tenMon,thoigian,gia;
        CardView btn_danhgia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trangthai = itemView.findViewById(R.id.tv_trangThaidh);
            tenMon = itemView.findViewById(R.id.tv_tenmondh);
            thoigian = itemView.findViewById(R.id.timedh);
            gia = itemView.findViewById(R.id.tv_giadh);
            btn_danhgia = itemView.findViewById(R.id.btn_danhGiadh);
        }
    }
}
