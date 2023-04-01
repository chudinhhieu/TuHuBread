package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.content.Intent;
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
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;

public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.MyViewHolder> {
    private ArrayList<MonAn> list;
    private Context context;

    public SanPhamNoiBatAdapter(ArrayList<MonAn> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void  setData(ArrayList<MonAn> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamnoibat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MonAn monAn = list.get(position);
        if (monAn== null){
            return;
        }
        holder.tv_ten_spnb.setText(monAn.getTenMon());
        holder.tv_gia_spnb.setText(monAn.getGia()+"");
        holder.btn_mua_spnb.setCardBackgroundColor(ContextCompat.getColor(context, R.color.main2));
        holder.btn_mua_spnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("idMon",monAn.getId_MonAn());
                bundle.putString("tenMon",monAn.getTenMon());
                bundle.putString("thanhPhan",monAn.getThanhPhan());
                bundle.putInt("gia",monAn.getGia());
                bundle.putInt("trangThai",monAn.getTrangThai());
                bundle.putInt("id_loai",monAn.getId_LoaiDoAn());
                Intent intent = new Intent(context, DatMonActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return 6;
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_spnb;
        private TextView tv_ten_spnb,tv_gia_spnb;
        private CardView btn_mua_spnb;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_spnb = itemView.findViewById(R.id.img_spnb);
            tv_ten_spnb = itemView.findViewById(R.id.tv_ten_spnb);
            tv_gia_spnb = itemView.findViewById(R.id.tv_gia_spnb);
            btn_mua_spnb = itemView.findViewById(R.id.btn_mua_spnb);

        }
    }
}
