package hieucdph29636.fpoly.tuhubread.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hieucdph29636.fpoly.tuhubread.R;

public class Chu_Activity extends AppCompatActivity {
    Button btn_dsnv,btn_dsdnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu);
        //todo: nếu muốn màn hình chủ giống khách or something? hãy sửa lại phần layout dòng trên

        btn_dsnv = findViewById(R.id.btn_dsnv);
        btn_dsdnt = findViewById(R.id.btn_dsdnt);

        btn_dsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chu_Activity.this,Ds_nv_Activity.class);
                startActivity(intent);
            }
        });

        btn_dsdnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chu_Activity.this,Ds_DonNapTien_Activity.class);
                startActivity(intent);
            }
        });

    }
    }