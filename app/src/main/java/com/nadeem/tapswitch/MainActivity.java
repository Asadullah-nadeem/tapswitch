package com.nadeem.tapswitch;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView mainImage;
    private int iconIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImage = findViewById(R.id.mainImage);

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAppIcon();
            }
        });
    }

    private void switchAppIcon() {
        PackageManager pm = getPackageManager();

        disableComponent(pm, "com.nadeem.tapswitch.icon1");
        disableComponent(pm, "com.nadeem.tapswitch.icon2");
        disableComponent(pm, "com.nadeem.tapswitch.icon3");

        String nextAlias = "com.nadeem.tapswitch.Icon" + iconIndex;
        enableComponent(pm, nextAlias);

        iconIndex = (iconIndex % 3) + 1;
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
