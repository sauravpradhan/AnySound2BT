package com.example.any_sound_2_bt;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	AudioManager audioM = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioM = (AudioManager) getApplicationContext().getSystemService(getApplicationContext().AUDIO_SERVICE);
	}
	public void onToggleClicked(View view) {

		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			// TODO Auto-generated method stub
			audioM.setMode(audioM.MODE_IN_CALL);
			audioM.setBluetoothScoOn(true);
			audioM.startBluetoothSco();
			audioM.setSpeakerphoneOn(false);
			Log.d("BT","Toggle Button On!");

		} else {
			audioM.setMode(audioM.MODE_NORMAL);
			audioM.setBluetoothScoOn(false);
			audioM.stopBluetoothSco();
			audioM.setSpeakerphoneOn(true);
			Log.d("BT","Toggle Button Off!");

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
