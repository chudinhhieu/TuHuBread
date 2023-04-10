package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.DatMonActivity;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnYeuThichDAO;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.DTO.MonAnYeuThich;
import hieucdph29636.fpoly.tuhubread.R;

public class MonAnYeuThichAdapter extends RecyclerView.Adapter<MonAnYeuThichAdapter.ViewHolder> {
    View view ;
    Context context;

    ArrayList<MonAnYeuThich> listmayt;

    MonAnYeuThichDAO maytdao;

    ArrayList<MonAn> dsma;
    MonAnDAO dao;

    public MonAnYeuThichAdapter(Context context, ArrayList<MonAnYeuThich> listmayt) {
        this.context = context;
        this.listmayt = listmayt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        view = layoutInflater.inflate(R.layout.item_mayt,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao = new MonAnDAO();
        int index = position;
        MonAn monAn = dao.getById(listmayt.get(position).getId_MonAn());
        holder.tv_ten.setText(monAn.getTenMon());
        holder.tv_thanhPhan.setText(monAn.getThanhPhan());
        holder.tv_gia.setText(monAn.getGia()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getAnhMonAn(), 0, monAn.getAnhMonAn().length);
        holder.img.setImageBitmap(bitmap);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa '"+monAn.getTenMon()+"' không?");
                builder.setTitle("Thông báo");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maytdao = new MonAnYeuThichDAO();
                        maytdao.delete(listmayt.get(index).getId_MonAn());
                        listmayt = maytdao.layTheoLoaiTK(listmayt.get(index).getTaiKhoan());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DatMonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idMon",monAn.getId_MonAn());
                bundle.putString("tenMon",monAn.getTenMon());
                bundle.putString("thanhPhan",monAn.getThanhPhan());
                bundle.putInt("gia",monAn.getGia());
                bundle.putInt("trangThai",monAn.getTrangThai());
                bundle.putInt("id_loai",monAn.getId_LoaiDoAn());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listmayt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_ten , tv_thanhPhan , tv_gia ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_item_mayt);
            tv_ten = itemView.findViewById(R.id.tv_item_ten_mayt);
            tv_gia = itemView.findViewById(R.id.tv_item_tien_mayt);
            tv_thanhPhan = itemView.findViewById(R.id.tv_item_Thanhphan_mayt);
        }
    }
}
