package com.example.any_sound_2_bt;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	AudioManager audioM = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioM = (AudioManager) getApplicationContext().getSystemService(getApplicationContext().AUDIO_SERVICE);
		
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
	}
	public void onToggleClicked(View view) {

		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			// TODO Auto-generated method stub
			audioM.setMode(audioM.MODE_IN_COMMUNICATION);
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
