package com.example.any_sound_2_bt;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	AudioManager audioM = null;
    BluetoothAdapter btAdapter;   
	public static Context ctx;
	BluetoothManager bMgr = null;
	private Set<BluetoothDevice> devices;
	private MyReceiver receiver;
	IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
	IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
	ToggleButton tb1 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ctx = getApplicationContext();
		this.registerReceiver(receiver, filter1);
		this.registerReceiver(receiver, filter2);
		tb1 = (ToggleButton)findViewById(R.id.toggleButton1); 
		audioM = (AudioManager) getApplicationContext().getSystemService(getApplicationContext().AUDIO_SERVICE);
		bMgr =  (BluetoothManager) getApplicationContext().getSystemService(getApplicationContext().BLUETOOTH_SERVICE);

		ImageView img = (ImageView)findViewById(R.id.image1);
		img.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("https://github.com/sauravpradhan/"));
				startActivity(intent);
			}
		});
		   btAdapter = BluetoothAdapter.getDefaultAdapter();
		   devices = btAdapter.getBondedDevices();  
           //Toast.makeText(getApplicationContext(),"No Of BT devices paired is:"+devices.size(),Toast.LENGTH_SHORT).show();
	}
	public void onToggleClicked(View view) {

		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			if((MyReceiver.isBTConnected == true) || (devices.size() > 0))
			{
				// TODO Auto-generated method stub
				audioM.setMode(audioM.MODE_IN_COMMUNICATION);
				audioM.setBluetoothScoOn(true);
				audioM.startBluetoothSco();
				audioM.setSpeakerphoneOn(false);
				Log.d("S@ur@v","Toggle Button On!");
			}
			else
			{
				tb1.setChecked(false);
				Toast.makeText(getApplicationContext(), "BT is not connected, Pls pair your device and restart the app again!", Toast.LENGTH_LONG).show();
			}

		} else {
			audioM.setMode(audioM.MODE_NORMAL);
			audioM.setBluetoothScoOn(false);
			audioM.stopBluetoothSco();
			audioM.setSpeakerphoneOn(true);
			Log.d("S@ur@v","Toggle Button Off!");

		}

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		audioM.setMode(AudioManager.MODE_NORMAL);
		audioM.setSpeakerphoneOn(true);
		super.onDestroy();
	}

}
