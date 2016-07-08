package com.example.view;

import com.example.drawdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
/*
 *程序Logo界面 
 */
public class LoGoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.logopage);
		new MyThread().start();

	}
	//判断屏幕是否被点击
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			toMyTabActivity();
		}
		return super.onTouchEvent(event);
	}

	private boolean gone = false;

	private synchronized void toMyTabActivity() {
		if (!gone) {
			gone = true;
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	//等待线程，等待时间3000毫秒
	class MyThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			toMyTabActivity();
		}

	}
}
