package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.DAO.DanhGiaDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DanhGia;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;

public class DanhGia_mon_Adapter extends RecyclerView.Adapter<DanhGia_mon_Adapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<MonAn> dsma;
    MonAnDAO dao;
    DanhGiaDAO danhGiaDAO;
    int Diem=0;
    public DanhGia_mon_Adapter(Context context, ArrayList<MonAn> dsma) {
        this.context = context;
        this.dsma = dsma;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
         view = inflater.inflate(R.layout.item_ds_mon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        int index = position;
        MonAn monAn = dsma.get(position);
        dao = new MonAnDAO();
        danhGiaDAO=new DanhGiaDAO();
        holder.tv_item_tendm.setText(dsma.get(position).getTenMon()+"");
        holder.tv_item_thanhphandm.setText(dsma.get(position).getThanhPhan()+"");
        holder.tv_item_tiendm.setText(dsma.get(position).getGia()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getAnhMonAn(), 0, monAn.getAnhMonAn().length);
        holder.img_item_imgdm.setImageBitmap(bitmap);
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Dialog dialog = new Dialog(context);
                 dialog.setContentView(R.layout.dialog_danhgia);
                 TextView tv_themAnh = dialog.findViewById(R.id.tv_themAnh);
                 TextInputLayout edL_binhLuan = dialog.findViewById(R.id.edL_binhLuan);
                 TextInputEditText ed_binhLuan = dialog.findViewById(R.id.ed_binhLuan);
                 Button btn_xacNhan = dialog.findViewById(R.id.btn_xacNhan_Dgia);
                 ImageView img_star1 = dialog.findViewById(R.id.img_star1);
                 ImageView img_star2 = dialog.findViewById(R.id.img_star2);
                 ImageView img_star3 = dialog.findViewById(R.id.img_star3);
                 ImageView img_star4 = dialog.findViewById(R.id.img_star4);
                 ImageView img_star5 = dialog.findViewById(R.id.img_star5);
                 img_star1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Diem=1;
                     }
                 });
                 img_star2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Diem=2;
                     }
                 });
                 img_star3.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Diem=3;
                     }
                 });
                 img_star4.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Diem=4;
                     }
                 });
                 img_star5.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Diem=5;
                     }
                 });
                 btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         DanhGia danhGia=new DanhGia();
                         danhGia.setId_monAn(dsma.get(index).getId_MonAn());
                         danhGia.setTaiKhoan(taiKhoan);
                         danhGia.setBinhLuan(ed_binhLuan.getText().toString());
                         danhGia.setDiem(Diem);
                         if (danhGiaDAO.insert_danhgia(danhGia)){
                             Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                         }else{
                             Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                         }


                     }
                 });
                 dialog.show();
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
