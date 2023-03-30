package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_Nhan_vien;
import hieucdph29636.fpoly.tuhubread.Activity.DatMonActivity;
import hieucdph29636.fpoly.tuhubread.Activity.Ds_nv_Activity;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;

public class Ds_mon_Adapter extends RecyclerView.Adapter<Ds_mon_Adapter.ViewHolder> {

    Context context;
    ArrayList<MonAn> dsma;

    public Ds_mon_Adapter(Context context, ArrayList<MonAn> dsma) {
        this.context = context;
        this.dsma = dsma;
    }

    @NonNull
    @Override
    public Ds_mon_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ds_mon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ds_mon_Adapter.ViewHolder holder, int position) {
        int index = position;
        holder.tv_item_tendm.setText(dsma.get(position).getTenMon()+"");
        holder.tv_item_thanhphandm.setText(dsma.get(position).getThanhPhan()+"");
        holder.tv_item_tiendm.setText(dsma.get(position).getGia()+"");
      //  holder.img_item_imgdm.setImageResource(dsma.get(position).getAnhMonAn());
        holder.linear_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DatMonActivity.class);
                Bundle bundle = new Bundle();

                // item here???

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.linear_dm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setMessage("Bạn có đồng ý ẩn món không?");

                builder.setTitle("Thông báo");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.linear_dm.setVisibility(View.GONE);

                    }
                });

                builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // khởi tạo thành dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;

            }

        });

    }

    @Override
    public int getItemCount() {
        return dsma.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_tendm,tv_item_thanhphandm,tv_item_tiendm;
        ImageView img_item_imgdm;
        LinearLayout linear_dm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_tendm = itemView.findViewById(R.id.tv_item_tendm);
            tv_item_thanhphandm = itemView.findViewById(R.id.tv_item_Thanhphandm);
            tv_item_tiendm = itemView.findViewById(R.id.tv_item_tiendm);
            img_item_imgdm = itemView.findViewById(R.id.img_item_imgdm);
            linear_dm = itemView.findViewById(R.id.linear_dm);

        }
    }


}
