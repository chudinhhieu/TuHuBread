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

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_Nhan_vien;
import hieucdph29636.fpoly.tuhubread.DTO.NhanVien;
import hieucdph29636.fpoly.tuhubread.R;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder>{

    Context context;
    ArrayList<NhanVien> dsnv;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> dsnv) {
        this.context = context;
        this.dsnv = dsnv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ds_nv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = position;
        holder.tv_ten_nv.setText(dsnv.get(position).getHoTen());
        holder.tv_sdt_nv.setText(dsnv.get(position).getSoDienThoai());
        holder.linear_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTiet_Nhan_vien.class);
                Bundle bundle = new Bundle();
                bundle.putString("tv_ten_nv",dsnv.get(index).getHoTen());
                bundle.putString("tv_sdt_nv",dsnv.get(index).getSoDienThoai());
                bundle.putString("tv_user_nv",dsnv.get(index).getTaiKhoan());
                bundle.putString("tv_pass_nv",dsnv.get(index).getMatKhau());
                bundle.putString("tv_ngaysinh_nv",dsnv.get(index).getNgaySinh());
                bundle.putInt("tv_quyen_nv",dsnv.get(index).getQuenNhanVien());

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsnv.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_ten_nv,tv_sdt_nv;
    LinearLayout linear_nv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ten_nv = itemView.findViewById(R.id.tv_ten_nv);
            tv_sdt_nv = itemView.findViewById(R.id.tv_sdt_nv);
            linear_nv = itemView.findViewById(R.id.linear_nv);
        }
    }
}
