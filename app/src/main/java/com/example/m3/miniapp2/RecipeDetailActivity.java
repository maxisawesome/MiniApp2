package com.example.m3.miniapp2;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by m3 on 3/18/18.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    private Context mContext;
    private ImageView recipeImageView;
    private TextView titleTextView;
    private TextView servingsTextView;
    private TextView timeTextView;
    private Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_detail);
        mContext = this;


        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Notification.Builder builder = new Notification.Builder(mContext);
                builder.setStyle(new Notification.BigTextStyle(builder)
                        .bigText("Instructions for this recipe may be found here.")
                        .setBigContentTitle("Big title"))
                        .setContentTitle("Title")
                        .setContentText("Summary")
                        .setSmallIcon(android.R.drawable.sym_def_app_icon);

                final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(0, builder.build());
            }
        });
    }
}
