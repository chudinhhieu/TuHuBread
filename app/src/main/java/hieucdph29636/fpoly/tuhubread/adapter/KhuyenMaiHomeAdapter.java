package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.graphics.Color;
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
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.DTO.KhuyenMai;
import hieucdph29636.fpoly.tuhubread.R;

public class KhuyenMaiHomeAdapter extends RecyclerView.Adapter<KhuyenMaiHomeAdapter.MyViewHolder> {
    private List<KhuyenMai> list;
    private Context context;

    public KhuyenMaiHomeAdapter(List<KhuyenMai> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void  setData(ArrayList<KhuyenMai> list){
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
        KhuyenMai khuyenMai = list.get(position);
        if (khuyenMai== null){
            return;
        }
        holder.tv_ten_spnb.setText(khuyenMai.getMoTaKM());
        holder.img_spnb.setImageResource(R.drawable.km);
        holder.btn_mua_spnb.setCardBackgroundColor(ContextCompat.getColor(context, R.color.main));
        holder.tv_gia_spnb.setVisibility(View.GONE);
        holder.tv_btn_spnb.setText("Áp dụng");
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_spnb;
        private TextView tv_ten_spnb,tv_gia_spnb,tv_btn_spnb;
        private CardView btn_mua_spnb;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_spnb = itemView.findViewById(R.id.img_spnb);
            tv_ten_spnb = itemView.findViewById(R.id.tv_ten_spnb);
            tv_gia_spnb = itemView.findViewById(R.id.tv_gia_spnb);
            btn_mua_spnb = itemView.findViewById(R.id.btn_mua_spnb);
            tv_btn_spnb = itemView.findViewById(R.id.tv_btn_spnb);
        }
    }
}
