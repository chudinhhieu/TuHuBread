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

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_KhachHang;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        int k_hang=position;
        holder.tv_ten_kh.setText(listKH.get(position).getHoTen());
        holder.tv_sdt_kh.setText(listKH.get(position).getSoDienThoai());
        holder.chitietKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ChiTiet_KhachHang.class);
                Bundle bundle=new Bundle();
                bundle.putString("tv_nameKH",listKH.get(k_hang).getHoTen());
                bundle.putString("tv_sdtKH",listKH.get(k_hang).getSoDienThoai());
                bundle.putString("tv_taiKhoanKH",listKH.get(k_hang).getTaiKhoan());
                bundle.putString("tv_passKH",listKH.get(k_hang).getMatKhau());
                bundle.putString("tv_dateKH",listKH.get(k_hang).getNgaySinh());
                bundle.putString("tv_Address",listKH.get(k_hang).getDiaChi());
                bundle.putInt("tv_soDuTK_KH",listKH.get(k_hang).getSoDuTaiKhoan());

                i.putExtras(bundle);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listKH.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ten_kh,tv_sdt_kh;
        LinearLayout chitietKH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ten_kh = itemView.findViewById(R.id.tvTen_KH);
            tv_sdt_kh = itemView.findViewById(R.id.tv_Sdt_kH);
            chitietKH=itemView.findViewById(R.id.chitietKH);
        }
    }
}
