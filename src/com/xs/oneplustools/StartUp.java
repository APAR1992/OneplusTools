package com.xs.oneplustools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//���������࣬�������ƿ����ָ�����

public class StartUp extends BroadcastReceiver {
	
	public void onReceive(Context paramContext, Intent paramIntent) {
		GestureActivity.RestoreDoubleTapToWake(paramContext);
		GestureActivity.RestoreCameraGesture(paramContext);
		GestureActivity.RestoreMusicGesture(paramContext);
	}

}
