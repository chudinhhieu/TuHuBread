package hieucdph29636.fpoly.tuhubread.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import hieucdph29636.fpoly.tuhubread.R;

public class ManHinhChao extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        logo = findViewById(R.id.logo_mhc);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChao.this, DangNhapActivity.class));
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}