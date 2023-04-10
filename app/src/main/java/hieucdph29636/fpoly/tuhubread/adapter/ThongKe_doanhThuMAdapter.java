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
import hieucdph29636.fpoly.tuhubread.DTO.TopDoanhThuMonAn;
import hieucdph29636.fpoly.tuhubread.R;

public class ThongKe_doanhThuMAdapter extends RecyclerView.Adapter<ThongKe_doanhThuMAdapter.DoanhThuMonAnViewHolder> {
    Context context;
    ArrayList<TopDoanhThuMonAn> list;
    MonAnDAO dao;
    public ThongKe_doanhThuMAdapter(Context context, ArrayList<TopDoanhThuMonAn> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DoanhThuMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_topdoanhthumon,parent,false);
        DoanhThuMonAnViewHolder viewHolder=new DoanhThuMonAnViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhThuMonAnViewHolder holder, int position) {
        TopDoanhThuMonAn doanhThuMonAn=list.get(position);
        dao = new MonAnDAO();
        holder.tv_Ten_DoanhThuMonAn.setText(doanhThuMonAn.getTen());
        holder.tv_DoanhThuMonAn.setText(doanhThuMonAn.getDoanhThu()+"");
        byte[] anh = dao.layAnhTheoID(doanhThuMonAn.getId());
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0,anh.length);
        holder.img.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DoanhThuMonAnViewHolder extends RecyclerView.ViewHolder{
           TextView tv_Ten_DoanhThuMonAn,tv_DoanhThuMonAn;
           ImageView img;
        public DoanhThuMonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Ten_DoanhThuMonAn=itemView.findViewById(R.id.tv_ten_mon_tk);
            tv_DoanhThuMonAn=itemView.findViewById(R.id.tv_doanhthuMon);
            img = itemView.findViewById(R.id.img_mon_tk);
        }
    }
}
