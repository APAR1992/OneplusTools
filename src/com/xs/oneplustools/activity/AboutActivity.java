package com.xs.oneplustools.activity;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import com.xs.oneplustools.R;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;

public class AboutActivity extends PreferenceActivity {
	
	private static final String CHECKFORUPDATE = "check_for_update";
	
	private Preference mCheckForUpdate;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_about);
		
		mCheckForUpdate = (Preference) findPreference(CHECKFORUPDATE);
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == mCheckForUpdate){
			UmengUpdateAgent.setUpdateAutoPopup(false);//��ֹ�Զ�����������ʾ�ĶԻ���
			UmengUpdateAgent.setUpdateOnlyWifi(false);//���������绷���о�������
			UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
			    @Override
			    public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
			        switch (updateStatus) {
			        case UpdateStatus.Yes:
			        	Toast.makeText(getApplicationContext(), "��鵽�°汾", Toast.LENGTH_SHORT).show();
			            UmengUpdateAgent.showUpdateDialog(getApplicationContext(), updateInfo);
			            break;
			        case UpdateStatus.No:
			            Toast.makeText(getApplicationContext(), "û�м�鵽����", Toast.LENGTH_SHORT).show();
			            break;
			        case UpdateStatus.Timeout:
			            Toast.makeText(getApplicationContext(), "���糬ʱ", Toast.LENGTH_SHORT).show();
			            break;
			        }
			    }
			});
			UmengUpdateAgent.update(getApplicationContext());
		}
		return false;
	}
}