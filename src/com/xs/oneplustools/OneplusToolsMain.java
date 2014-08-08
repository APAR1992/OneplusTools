package com.xs.oneplustools;

import java.io.IOException;

import com.xs.oneplustools.R;
import com.xs.oneplustools.activity.AboutActivity;
import com.xs.oneplustools.activity.FlashRomActivity;
import com.xs.oneplustools.activity.GestureActivity;
import com.xs.oneplustools.activity.ModemActivity;
import com.xs.oneplustools.activity.PowerActivity;
import com.xs.oneplustools.activity.SoundActivity;
import com.xs.oneplustools.tools.AssetCopyer;
import com.xs.oneplustools.tools.RootCmd;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TabHost;
import android.widget.Toast;

//������Ҫ�̳�ActivityGroup
public class OneplusToolsMain extends ActivityGroup {
	private TabHost mTabHost;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preferenceֵ
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// ����
		setContentView(R.layout.activity_tab_host);

		// ��ӭ
		Toast.makeText(getApplicationContext(), R.string.welcome,
				Toast.LENGTH_SHORT).show();

		// �ڳ�������ʱ�������ݵ�SD��
		AssetCopyer asset = new AssetCopyer(getBaseContext());
		try {
			asset.copy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��������Ŀ¼
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Backup/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Flash/");

		// ����TabHost
		initTabs();
	}

	// ����Tab�б�
	private void initTabs() {
		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup(this.getLocalActivityManager());

		// ����
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_about")
				.setIndicator("����",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, AboutActivity.class)));

		// ��Դ
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_power")
				.setIndicator("��Դ",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, PowerActivity.class)));

		// ����
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_sound")
				.setIndicator("����",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, SoundActivity.class)));

		// ����
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_modem")
				.setIndicator("����",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, ModemActivity.class)));

		// ˢ��
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_flash")
				.setIndicator("ˢ��",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, FlashRomActivity.class)));
		
		//����
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_gesture")
				.setIndicator("����",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, GestureActivity.class)));

		// ��������ʱ��Tab
		mTabHost.setCurrentTab(0);
	}
}