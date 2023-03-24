package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_Don_nap_tien;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.R;

public class DonNapTien_Adapter extends RecyclerView.Adapter<DonNapTien_Adapter.ViewHolder1> {

    Context context;
    ArrayList<DonNapTien> ds_dnt;

    public DonNapTien_Adapter(Context context, ArrayList<DonNapTien> ds_dnt) {
        this.context = context;
        this.ds_dnt = ds_dnt;
    }

    @NonNull
    @Override
    public DonNapTien_Adapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ds_don_nap_tien,parent,false);
        return new DonNapTien_Adapter.ViewHolder1(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull DonNapTien_Adapter.ViewHolder1 holder, int position) {
        int index = position;
        holder.tv_demo_dnt.setText(ds_dnt.get(index).getid_DonNapTien());

        holder.linear_dnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTiet_Don_nap_tien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tv_id_dn",ds_dnt.get(index).getid_DonNapTien());
                bundle.putInt("tv_id_kh_dn",ds_dnt.get(index).getId_khachHang());
                bundle.putString("tv_tg_dn",ds_dnt.get(index).getThoiGianTao());
                bundle.putInt("tv_ver_dn",ds_dnt.get(index).getTrangThai());
                bundle.putInt("tv_money_dn",ds_dnt.get(index).getTienNap());
               // bundle.putByte("tv_anh_dnt",ds_dnt.get(index).getAnhHoaDon()[]));

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_dnt.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView tv_demo_dnt;
        LinearLayout linear_dnt;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
          linear_dnt = itemView.findViewById(R.id.linear_dnt);
          tv_demo_dnt = itemView.findViewById(R.id.tv_demo_dnt);
        }
    }

}
