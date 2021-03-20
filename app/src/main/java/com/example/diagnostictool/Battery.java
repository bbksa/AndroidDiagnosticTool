package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import java.text.BreakIterator;
import java.text.DecimalFormatSymbols;

public class Battery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
    }

    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    private Context context;
    Intent batteryStatus = context.registerReceiver(null, ifilter);

    // Are we charging / charged?
    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL;

    // How are we charging?
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

    float batteryPct = level * 100 / (float)scale;

    private void batteryStatusExtractor() {

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = registerReceiver(null, ifilter);

        // Are we charging / charged?
        final int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        final boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        final boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        final boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        // What is battery charge level?
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        final float batteryPct = level * 100 / (float)scale;

        // How is battery health?
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

                BreakIterator battery_health_view = null;
                if(deviceHealth == BatteryManager.BATTERY_HEALTH_COLD){
                    battery_health_view.setText("BATTERY HEALTH : COLD");
                }

                if(deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD){
                    battery_health_view.setText("BATTERY HEALTH : DEAD");
                }

                if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD){
                    battery_health_view.setText("BATTERY HEALTH : HEALTHY");
                }

                if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){
                    battery_health_view.setText("BATTERY HEALTH : OVERHEATED");
                }

                if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
                    battery_health_view.setText("BATTERY HEALTH : OVER-VOLTAGE");
                }

                if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){
                    battery_health_view.setText("BATTERY HEALTH : HEALTH UNKNOWN");
                }

                if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
                    battery_health_view.setText("BATTERY HEALTH : UNSPECIFIC FAILURE");
                }
            }
        }, ifilter);

        // Showing up the stats in UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(status == BatteryManager.BATTERY_STATUS_CHARGING){
                    BreakIterator charging_status_field = null;
                    charging_status_field.setText("STATUS : CHARGING");
                    BreakIterator charging_type_field = null;
                    if (acCharge)
                        charging_type_field.setText("CHARGING TYPE : AC");
                    if (usbCharge)
                        charging_type_field.setText("CHARGING TYPE : DC (Over USB)");
                }

                BreakIterator charging_status_field = null;
                BreakIterator charging_type_field = null;
                if(status == BatteryManager.BATTERY_STATUS_DISCHARGING){
                    charging_status_field.setText("STATUS : DISCHARGING");
                    charging_type_field.setText("CHARGING TYPE : ---");
                }

                if (status == BatteryManager.BATTERY_STATUS_FULL){
                    charging_status_field.setText("STATUS : CHARGING (FULL)");
                    if (acCharge)
                        charging_type_field.setText("CHARGING TYPE : AC");
                    if (usbCharge)
                        charging_type_field.setText("CHARGING TYPE : DC (Over USB)");
                }

                if(status == BatteryManager.BATTERY_STATUS_UNKNOWN){
                    charging_status_field.setText("STATUS : UNKNOWN");
                    charging_type_field.setText("CHARGING TYPE : ---");
                }

                if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                    charging_status_field.setText("STATUS : NOT-CHARGING");
                    charging_type_field.setText("CHARGING TYPE : ---");
                }

                DecimalFormatSymbols battery_progress = null;
                battery_progress.setPercent((char) batteryPct);
                BreakIterator charging_percentage_view = null;
                charging_percentage_view.setText(batteryPct + "%");
            }
        });

        System.out.println("()()()() IS CHARGING : " + isCharging);
        System.out.println("()()()() AC : " + acCharge);
        System.out.println("()()()() DC : " + usbCharge);
    }

}