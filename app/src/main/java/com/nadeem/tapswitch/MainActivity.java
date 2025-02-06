package com.nadeem.tapswitch;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int iconIndex = 1;
    private final String[] icons = {".icon1", ".icon2", ".icon3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mainImage = findViewById(R.id.mainImage);
        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAppIcon();
            }
        });
    }

    private void switchAppIcon() {
        PackageManager pm = getPackageManager();
        for (String icon : icons) {
            disableComponent(pm, "com.nadeem.tapswitch" + icon);
        }enableComponent(pm, "com.nadeem.tapswitch" + icons[iconIndex]);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        iconIndex = (iconIndex + 1) % icons.length;
    }

    private void enableComponent(PackageManager pm, String componentName) {
        pm.setComponentEnabledSetting(
                new ComponentName(this, componentName),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
        );
    }

    private void disableComponent(PackageManager pm, String componentName) {
        pm.setComponentEnabledSetting(
                new ComponentName(this, componentName),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
    }
}
