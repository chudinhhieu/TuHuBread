package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.DonHangChiTietActivity;
import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.R;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.MyViewHolder> {
    View view;
    private ArrayList<ChiTietDonHang> list;
    private Context context;
    private ChiTietDonHangDAO dao;
    public ChiTietDonHangAdapter(ArrayList<ChiTietDonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang_ctdh,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dao = new ChiTietDonHangDAO();
        String tenMon = dao.getTenMonFromChiTietDonHang(list.get(position).getId_id());
        holder.ten.setText(tenMon);
        holder.gia.setText(list.get(position).getGiaTien()+"");
        int Rau = list.get(position).getRau();
        int Ot = list.get(position).getOt();
        if (Rau==0){
            holder.rau.setText("Không rau");
        }
        if (Rau==1){
            holder.rau.setText("Có rau");
        }
        if (Ot==0){
            holder.rau.setText("Không ớt");
        }
        if (Ot==1){
            holder.rau.setText("Có ớt");
        }
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ten,rau,ot,gia,btn_sua,btn_xoa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_item_tenmon_ctdh);
            rau = itemView.findViewById(R.id.tv_item_rau_ctdh);
            ot = itemView.findViewById(R.id.tv_item_ot_ctdh);
            gia = itemView.findViewById(R.id.tv_item_gia_ctdh);
            btn_sua = itemView.findViewById(R.id.btn_chinhsua_item_ctdh);
            btn_xoa = itemView.findViewById(R.id.btn_xoa_item_ctdh);
        }
    }
}
