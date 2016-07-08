package com.example.view;

import com.example.drawdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
/*
 *����Logo���� 
 */
public class LoGoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.logopage);
		new MyThread().start();

	}
	//�ж���Ļ�Ƿ񱻵��
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
	//�ȴ��̣߳��ȴ�ʱ��3000����
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
