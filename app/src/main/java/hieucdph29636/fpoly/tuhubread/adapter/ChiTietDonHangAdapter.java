package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.tuhubread.Activity.DonHangChiTietActivity;
import hieucdph29636.fpoly.tuhubread.DAO.ChiTietDonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.DonHangDAO;
import hieucdph29636.fpoly.tuhubread.DAO.MonAnDAO;
import hieucdph29636.fpoly.tuhubread.DTO.ChiTietDonHang;
import hieucdph29636.fpoly.tuhubread.DTO.DonHang;
import hieucdph29636.fpoly.tuhubread.R;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.MyViewHolder> {
    View view;
    private ArrayList<ChiTietDonHang> list;
    private Context context;
    private MonAnDAO monAnDAO;
    private ChiTietDonHangDAO dao;
    private DonHangDAO donHangDAO;
    int soluong;
    public ChiTietDonHangAdapter(ArrayList<ChiTietDonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang_ctdh,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dao = new ChiTietDonHangDAO();
        int index = position;
        donHangDAO = new DonHangDAO();
        monAnDAO = new MonAnDAO();
        int ttdh = donHangDAO.getByID(list.get(position).getId_donHang()).get(0).getTrangThai();
        if (ttdh>0){
            holder.btn_sua.setVisibility(View.GONE);
            holder.btn_xoa.setVisibility(View.GONE);
        }
        int sl = list.get(position).getSoLuong();
        String tenMon = dao.getTenMonFromChiTietDonHang(list.get(position).getId_id());
        holder.ten.setText(tenMon);
        holder.gia.setText(list.get(position).getGiaTien()+"");
        holder.tv_item_sl_ctdh.setText("x"+sl);
        int Rau = list.get(position).getRau();
        int Ot = list.get(position).getOt();
        if (Rau==0){
            holder.rau.setText("Không rau");
        }
        if (Rau==1){
            holder.rau.setText("Có rau");
        }
        if (Ot==0){
            holder.ot.setText("Không ớt");
        }
        if (Ot==1){
            holder.ot.setText("Có ớt");
        }
        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong = sl;
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_sua_mon);
                RadioButton rdoRau,rdoKRau,rdoOt,rdoKOt;
                ImageView cong,tru;
                TextView tv_soluong;
                Button xacNhan,huy;
                rdoRau = dialog.findViewById(R.id.rdo_coRauSua);
                rdoKRau = dialog.findViewById(R.id.rdo_khongRauSua);
                rdoOt = dialog.findViewById(R.id.rdo_coOtSua);
                rdoKOt = dialog.findViewById(R.id.rdo_khongOtSua);
                cong = dialog.findViewById(R.id.btn_congSL_sua);
                tru = dialog.findViewById(R.id.btn_truSL_sua);
                tv_soluong = dialog.findViewById(R.id.tv_soluong_datMon_sua);
                xacNhan = dialog.findViewById(R.id.btn_xn_sua_dh);
                huy = dialog.findViewById(R.id.btn_huysua_dh);

                if (Rau==0){
                    rdoKRau.setChecked(true);
                }
                if (Rau==1){
                    rdoRau.setChecked(true);
                }
                if (Ot==0){
                    rdoKOt.setChecked(true);
                }
                if (Ot==1){
                    rdoOt.setChecked(true);
                }
                tv_soluong.setText(sl+"");
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (soluong==10){
                            return;
                        }
                        soluong = Integer.parseInt(tv_soluong.getText().toString());
                        soluong++;
                        tv_soluong.setText(soluong+"");
                    }
                });
                tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (soluong==1){
                            return;
                        }
                        soluong = Integer.parseInt(tv_soluong.getText().toString());
                        soluong--;
                        tv_soluong.setText(soluong+"");
                    }
                });
                xacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int coRau=0;
                        int coOt=0;
                        int giaMon = monAnDAO.getById(list.get(index).getId_monAn()).getGia();
                        if (rdoRau.isChecked()){
                            coRau =1;
                        }else {
                            coRau=0;
                        }
                        if (rdoOt.isChecked()){
                            coOt =1;
                        }else {
                            coOt=0;
                        }
                        dao.update(soluong,coRau,coOt,soluong*giaMon,list.get(index).getId_id());
                        list = (ArrayList<ChiTietDonHang>) dao.getAll(list.get(index).getId_donHang());
                        Toast.makeText(context, "Thành công!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa món '"+tenMon+"' không?");
                builder.setTitle("Thông báo");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(list.get(index).getId_id());
                        list = (ArrayList<ChiTietDonHang>) dao.getAll(list.get(index).getId_donHang());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thành công!", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public int getItemCount() {
        if(list!= null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ten,rau,ot,gia,btn_sua,btn_xoa,tv_item_sl_ctdh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_item_tenmon_ctdh);
            rau = itemView.findViewById(R.id.tv_item_rau_ctdh);
            ot = itemView.findViewById(R.id.tv_item_ot_ctdh);
            gia = itemView.findViewById(R.id.tv_item_gia_ctdh);
            tv_item_sl_ctdh = itemView.findViewById(R.id.tv_item_sl_ctdh);
            btn_sua = itemView.findViewById(R.id.btn_chinhsua_item_ctdh);
            btn_xoa = itemView.findViewById(R.id.btn_xoa_item_ctdh);
        }
    }
}
