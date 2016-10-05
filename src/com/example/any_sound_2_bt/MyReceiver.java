package com.example.any_sound_2_bt;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	public static Boolean isBTConnected = false;
	@Override
	public void onReceive(Context context, Intent action) {
		// TODO Auto-generated method stub
		if (intent != null) {
			String action = intent.getAction();
			if (action != null) {
				//Toast.makeText(context, "Saurav!"+action, Toast.LENGTH_SHORT).show();
				if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
					Log.d("S@ur@v", "Acl Connected!");
					Toast.makeText(MainActivity.ctx, "ACL connected", Toast.LENGTH_SHORT).show();
					isBTConnected = true;
				} else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
					Log.d("S@ur@v", "Acl Disconnected!");
					Toast.makeText(MainActivity.ctx, "ACL Disconnected", Toast.LENGTH_SHORT).show();
					isBTConnected = false;
				}
			}
		}
	}

}
