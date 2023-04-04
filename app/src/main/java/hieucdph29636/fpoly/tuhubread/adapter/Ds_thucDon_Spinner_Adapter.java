package hieucdph29636.fpoly.tuhubread.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.LoaiMon;
import hieucdph29636.fpoly.tuhubread.DTO.Thuc_don;
import hieucdph29636.fpoly.tuhubread.R;

public class Ds_thucDon_Spinner_Adapter extends BaseAdapter {
    Context mContext;
    ArrayList<LoaiMon> list;

    public Ds_thucDon_Spinner_Adapter(Context mContext, ArrayList<LoaiMon> list) {
        this.mContext = mContext;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = ((Activity) mContext).getLayoutInflater();
        ViewOfItem viewOfItem = null;
        if (view == null){
            view = layoutInflater.inflate(R.layout.item_thucdon_selected, null);
            viewOfItem = new ViewOfItem();
            viewOfItem.tvTenLoaiSach = view.findViewById(R.id.tv_selected);
            view.setTag(viewOfItem);
        }else {
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.tvTenLoaiSach.setText(list.get(i).getTenLoai());
        return view;
    }

    public static class ViewOfItem {
        TextView tvTenLoaiSach;
    }
}
