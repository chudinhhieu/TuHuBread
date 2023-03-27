package hieucdph29636.fpoly.tuhubread.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import hieucdph29636.fpoly.tuhubread.DTO.PhotoSlideShow;
import hieucdph29636.fpoly.tuhubread.R;

public class PhotoSlideShowAdapter extends PagerAdapter {
    private Context context;
    private List<PhotoSlideShow> list ;

    public PhotoSlideShowAdapter(Context context, List<PhotoSlideShow> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide_show,container,false);
        ImageView imageView = view.findViewById(R.id.img_silde);
        PhotoSlideShow photoSlideShow = list.get(position);
        if (photoSlideShow!=null){
            Glide.with(context).load(photoSlideShow.getId()).into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (list!= null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
