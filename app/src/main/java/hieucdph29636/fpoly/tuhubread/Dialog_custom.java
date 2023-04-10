package hieucdph29636.fpoly.tuhubread;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;

import hieucdph29636.fpoly.tuhubread.Activity.DatMonActivity;

public class Dialog_custom {
    Context context;

    public Dialog_custom(Context context) {
        this.context = context;
    }

    public void sendDialog(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanhcong);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1000);
    }
}
