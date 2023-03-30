package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.Activity.Chi_Tiet_Khuyen_Mai;
import hieucdph29636.fpoly.tuhubread.Activity.KhuyenMaiActivity;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.R;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder> {
    Context context ;
    List<KhuyenMai> km ;

    public KhuyenMaiAdapter(Context context, List<KhuyenMai> km) {
        this.context = context;
        this.km = km;
    }

    @NonNull
    @Override
    public KhuyenMaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_km,parent,false);
        return new KhuyenMaiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = position;
        holder.tv_mota_km.setText(km.get(position).getMoTaKM());
        holder.tv_sotiengiam_km.setText(km.get(position).getSoTienGiam()+"");

        holder.linear_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chi_Tiet_Khuyen_Mai.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tv_id",km.get(index).getId_KhuyenMai());
                bundle.putString("tv_code_km",km.get(index).getCode());
                bundle.putString("tv_mota_km",km.get(index).getMoTaKM());
                bundle.putString("tv_ngaybatdau_km",km.get(index).getNgayBatDau());
                bundle.putString("tv_ngayketthuc_km",km.get(index).getNgayKetThuc());
                bundle.putInt("tv_sotiengiam_km",km.get(index).getSoTienGiam());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return km.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_mota_km , tv_sotiengiam_km ;
        LinearLayout linear_km ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_mota_km = itemView.findViewById(R.id.tv_mota_km);
            tv_sotiengiam_km = itemView.findViewById(R.id.tv_sotiengiam_km);
            linear_km = itemView.findViewById(R.id.linear_km);
        }

}
}
