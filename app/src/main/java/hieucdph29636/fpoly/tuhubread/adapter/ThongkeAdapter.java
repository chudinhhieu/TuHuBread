package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.TopMonBanChay;
import hieucdph29636.fpoly.tuhubread.R;

public class ThongkeAdapter extends RecyclerView.Adapter<ThongkeAdapter.ViewHolder>{
    Context context;
    ArrayList<TopMonBanChay> list;
    MonAnDAO dao;

    public ThongkeAdapter(Context context, ArrayList<TopMonBanChay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_topdoanhthumon,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         TopMonBanChay top=list.get(position);
        dao = new MonAnDAO();
        holder.tv_Ten_DoanhThuMonAn.setText(top.getTen());
        holder.tv_DoanhThuMonAn.setText(top.getSoLuong()+"");
        byte[] anh = dao.layAnhTheoID(top.getId_monAn());
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0,anh.length);
        holder.img.setImageBitmap(bitmap);    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Ten_DoanhThuMonAn,tv_DoanhThuMonAn;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Ten_DoanhThuMonAn=itemView.findViewById(R.id.tv_ten_mon_tk);
            tv_DoanhThuMonAn=itemView.findViewById(R.id.tv_doanhthuMon);
            img = itemView.findViewById(R.id.img_mon_tk);
        }
    }
}
