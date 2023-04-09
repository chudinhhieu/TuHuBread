package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DanhGiaDAO;
import hieucdph29636.fpoly.tuhubread.DAO.KhachHangDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DanhGia;
import hieucdph29636.fpoly.tuhubread.R;

public class Hien_DanhGia_mon_Adapter extends RecyclerView.Adapter<Hien_DanhGia_mon_Adapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<DanhGia> list;
    DanhGiaDAO danhGiaDAO;
    KhachHangDAO khachHangDAO;
    int Diem=0;
    public Hien_DanhGia_mon_Adapter(Context context, ArrayList<DanhGia> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
         view = inflater.inflate(R.layout.layout_item_danhgia,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        int index = position;
        DanhGia danhGia = list.get(position);
        danhGiaDAO=new DanhGiaDAO();
        khachHangDAO=new KhachHangDAO();
        holder.tv_HoTEN.setText(khachHangDAO.getHoTen(taiKhoan));
        holder.tv_binhluan.setText(danhGia.getBinhLuan());
        switch (danhGia.getDiem()){
            case 1:
                holder.img_sao1.setImageResource(R.drawable.ic_baseline_star_24);
                break;
            case 2:
                holder.img_sao1.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao2.setImageResource(R.drawable.ic_baseline_star_24);
                break;
            case 3:
                holder.img_sao1.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao2.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao3.setImageResource(R.drawable.ic_baseline_star_24);
                break;
            case 4:
                holder.img_sao1.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao2.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao3.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao4.setImageResource(R.drawable.ic_baseline_star_24);
                break;
            case 5:
                holder.img_sao1.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao2.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao3.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao4.setImageResource(R.drawable.ic_baseline_star_24);
                holder.img_sao5.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_HoTEN,tv_binhluan;
        ImageView img_sao1,img_sao2,img_sao3,img_sao4,img_sao5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             tv_HoTEN=itemView.findViewById(R.id.tv_HoTen);
             tv_binhluan= itemView.findViewById(R.id.tv_BinhLuan);
             img_sao1=itemView.findViewById(R.id.img_sao1);
            img_sao2=itemView.findViewById(R.id.img_sao2);
            img_sao3=itemView.findViewById(R.id.img_sao3);
            img_sao4=itemView.findViewById(R.id.img_sao4);
            img_sao5=itemView.findViewById(R.id.img_sao5);


        }
    }


}
