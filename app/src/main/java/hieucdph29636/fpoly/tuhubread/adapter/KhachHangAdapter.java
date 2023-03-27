package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.KhachHang_Activity;
import hieucdph29636.fpoly.tuhubread.DTO.KhachHang;
import hieucdph29636.fpoly.tuhubread.R;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    Context context;
    ArrayList<KhachHang> listKH;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKH) {
        this.context = context;
        this.listKH = listKH;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        int k_hang=position;
        holder.tv_ten_kh.setText(listKH.get(position).getHoTen());
        holder.tv_sdt_kh.setText(listKH.get(position).getSoDienThoai());
    }

    @Override
    public int getItemCount() {
        return listKH.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ten_kh,tv_sdt_kh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ten_kh = itemView.findViewById(R.id.tvTen_KH);
            tv_sdt_kh = itemView.findViewById(R.id.tv_Sdt_kH);
        }
    }
}
