package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DTO.TopMonBanChay;
import hieucdph29636.fpoly.tuhubread.R;

public class ThongkeAdapter extends RecyclerView.Adapter<ThongkeAdapter.ViewHolder>{
    Context context;
    ArrayList<TopMonBanChay> list;

    public ThongkeAdapter(Context context, ArrayList<TopMonBanChay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_topmon,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         TopMonBanChay top=list.get(position);
         holder.tv_TenTop.setText(top.getTen());
         holder.tv_soLuongTop.setText(top.getSoLuong()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TenTop,tv_soLuongTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TenTop=itemView.findViewById(R.id.tv_TenTop);
            tv_soLuongTop=itemView.findViewById(R.id.tv_soLuongTop);
        }
    }
}
