package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hieucdph29636.fpoly.tuhubread.Activity.ChiTiet_Nhan_vien;
import hieucdph29636.fpoly.tuhubread.Activity.DatMonActivity;
import hieucdph29636.fpoly.tuhubread.Activity.Ds_nv_Activity;
import hieucdph29636.fpoly.tuhubread.Activity.SuaMonAnActivity;
import hieucdph29636.fpoly.tuhubread.Activity.ThemMonAnActivity;
import hieucdph29636.fpoly.tuhubread.Activity.ViTienActivity;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DAO.NhanVienDAO;
import hieucdph29636.fpoly.tuhubread.DTO.DonNapTien;
import hieucdph29636.fpoly.tuhubread.DTO.MonAn;
import hieucdph29636.fpoly.tuhubread.R;

public class Ds_mon_Adapter extends RecyclerView.Adapter<Ds_mon_Adapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<MonAn> dsma;
    MonAnDAO dao;
    NhanVienDAO nhanVienDAO;
    public Ds_mon_Adapter(Context context, ArrayList<MonAn> dsma) {
        this.context = context;
        this.dsma = dsma;
    }

    @NonNull
    @Override
    public Ds_mon_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
         view = inflater.inflate(R.layout.item_ds_mon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ds_mon_Adapter.ViewHolder holder, int position) {
        nhanVienDAO = new NhanVienDAO();
        SharedPreferences sharedPreferences = context.getSharedPreferences("luuDangNhap", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("TK","");
        String quyen = sharedPreferences.getString("quyen","");
        int index = position;
        MonAn monAn = dsma.get(position);
        dao = new MonAnDAO();
        holder.tv_item_tendm.setText(dsma.get(position).getTenMon()+"");
        holder.tv_item_thanhphandm.setText(dsma.get(position).getThanhPhan()+"");
        holder.tv_item_tiendm.setText(dsma.get(position).getGia()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getAnhMonAn(), 0, monAn.getAnhMonAn().length);
        holder.img_item_imgdm.setImageBitmap(bitmap);
        if (quyen.equalsIgnoreCase("nhanvien")&&(nhanVienDAO.getQuyen(taiKhoan)==0||nhanVienDAO.getQuyen(taiKhoan)==1)){
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_sua_xoa);
                    LinearLayout sua,xoa;
                    sua = dialog.findViewById(R.id.dialog_edit);
                    xoa = dialog.findViewById(R.id.dialog_delete);
                    xoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Bạn có muốn xóa '"+monAn.getTenMon()+"' không?");
                            builder.setTitle("Thông báo");
                            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dao.delete(monAn.getId_MonAn());
                                    dsma = dao.layTheoLoaiNV(0);
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
                        }
                    });
                    sua.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent = new Intent(context, SuaMonAnActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("idMon",monAn.getId_MonAn());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });
                    dialog.show();

                    return true;
                }
            });
        }
        holder.linear_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return dsma.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_tendm,tv_item_thanhphandm,tv_item_tiendm;
        ImageView img_item_imgdm;
        RelativeLayout linear_dm;

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
