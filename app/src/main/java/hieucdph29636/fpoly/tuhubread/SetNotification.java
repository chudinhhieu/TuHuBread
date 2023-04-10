package hieucdph29636.fpoly.tuhubread;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class SetNotification {
    private static final int NOTIFICATION_ID = 1;
    Context context;

    public SetNotification(Context context) {
        this.context = context;
    }

    public void sendNotification(String noiDung) {
        // Tạo kênh thông báo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_2);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setContentTitle("Thông báo đơn hàng")
                .setContentText(noiDung)
                .setSmallIcon(R.drawable.img_item_donhang)
                .setLargeIcon(largeIcon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
