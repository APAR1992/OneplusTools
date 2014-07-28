package com.xs.oneplustools;

import java.util.Timer;
import java.util.TimerTask;

import com.xs.oneplustools.R;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

/*
 * 开机画面/
 */
public class PowerStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_power_on);

		Toast.makeText(getApplicationContext(), "程序正在准备数据中...",
				Toast.LENGTH_SHORT).show();

		new Timer().schedule(new MyTimerTask(), 1000);

	}

	private class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			startMainFunction();
		}
	}

	private void startMainFunction() {
		Intent intent = new Intent(this, OneplusToolsMainActivity.class);
		this.startActivity(intent);
		this.finish();
	}

}