package com.example.sriteja.allinone;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class PhoneStatusActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_status);
        int androidVersion = Build.VERSION.SDK_INT;
        mContext = getApplicationContext();
        TextView textView = findViewById(R.id.textView2);
        if (androidVersion == Build.VERSION_CODES.O_MR1) {
            textView.setText(getString(R.string.version_oreo));
        }

        textView = findViewById(R.id.textView3);

        if (isPhonePluggedIn()) {
            textView.setText(getString(R.string.battery_charging));
        } else {
            textView.setText(getString(R.string.battery_discharging));
        }

    }
    public boolean isPhonePluggedIn(){
        boolean charging = false;

        final Intent batteryIntent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean batteryCharge = status==BatteryManager.BATTERY_STATUS_CHARGING;

        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if (batteryCharge) charging=true;
        if (usbCharge) charging=true;
        if (acCharge) charging=true;

        return charging;
    }
}


