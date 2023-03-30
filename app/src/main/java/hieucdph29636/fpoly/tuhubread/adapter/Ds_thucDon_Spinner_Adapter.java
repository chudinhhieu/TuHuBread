package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.Thuc_don;
import hieucdph29636.fpoly.tuhubread.R;

public class Ds_thucDon_Spinner_Adapter extends ArrayAdapter<Thuc_don> {
    public Ds_thucDon_Spinner_Adapter(@NonNull Context context, int resource, @NonNull List<Thuc_don> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thucdon_selected,parent,false);
        TextView tv_selected = convertView.findViewById(R.id.tv_selected);

        Thuc_don thuc_don = this.getItem(position);
        if (thuc_don != null){
            tv_selected.setText(thuc_don.getName());
        }
        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuc_don,parent,false);
        TextView tv_thucdon = convertView.findViewById(R.id.tv_thucdon);

        Thuc_don thuc_don = this.getItem(position);
        if (thuc_don != null){
            tv_thucdon.setText(thuc_don.getName());
        }
        return convertView;
    }
}
