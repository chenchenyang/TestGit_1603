package com.example.view;

import com.example.drawdemo.MyView;
import com.example.drawdemo.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	LinearLayout LinearLayout;
	MyView myView;
	private int r = (int) System.currentTimeMillis(); // 时间随机变量
	/** 定义菜单id */
	private static final int M_CHANGE_CLEAR = Menu.FIRST;
	private static final int M_CHANGE_PAINTSETING = Menu.FIRST + 1;
	private static final int M_CHANGE_PAINT = Menu.FIRST + 2;
	private static final int M_CHANGE_GRAPH = Menu.FIRST + 3;
	private static final int M_CHANGE_RUBBER = Menu.FIRST + 4;
	private static final int M_CHANGE_SAVE = Menu.FIRST + 5;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);

		myView = new MyView(getApplicationContext());
		myView.setBackgroundColor(Color.TRANSPARENT);

		// 将view加入到布局中
		LinearLayout = (LinearLayout) findViewById(R.id.linearlayout01);
		LinearLayout.removeAllViews();
		LinearLayout.addView(myView);

	}

	/** 创建Menu菜单重写onCreateOptionsMenu方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建menu群组id
		int idGroup1 = 0;
		// 创建menu顺序id
		int orderMenuItem1 = Menu.NONE;
		int orderMenuItem2 = Menu.NONE + 1;
		int orderMenuItem3 = Menu.NONE + 2;
		int orderMenuItem4 = Menu.NONE + 3;
		int orderMenuItem5 = Menu.NONE + 4;
		int orderMenuItem6 = Menu.NONE + 5;
		/** 建立menu菜单 */
		menu.add(idGroup1, M_CHANGE_CLEAR, orderMenuItem1, R.string.clearname);
		menu.add(idGroup1, M_CHANGE_PAINTSETING, orderMenuItem2,
				R.string.openFile);
		menu.add(idGroup1, M_CHANGE_PAINT, orderMenuItem3,
				R.string.printsetting);
		menu.add(idGroup1, M_CHANGE_GRAPH, orderMenuItem4, R.string.graphname);
		menu.add(idGroup1, M_CHANGE_RUBBER, orderMenuItem5, R.string.rubbername);
		menu.add(idGroup1, M_CHANGE_SAVE, orderMenuItem6, R.string.saveFile);

		return super.onCreateOptionsMenu(menu);
	}

	/** 选择Menu菜单重写onOptionsItemSelected方法 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();// 获得menu菜单的id
		switch (id) {
		case M_CHANGE_CLEAR: // 清屏按钮
			LinearLayout.removeAllViews();
			myView = new MyView(getApplicationContext());
			LinearLayout.addView(myView);
			break;
		case M_CHANGE_PAINTSETING:// 画笔颜色
			selectColor();
			break;
		case M_CHANGE_PAINT:// 画笔按钮
			myView.setDrawTool(6);
			break;
		case M_CHANGE_GRAPH:// 图形选择
			selectShape();
			break;
		case M_CHANGE_RUBBER:// 橡皮擦按钮
			myView.setDrawTool(10);
			break;
		case M_CHANGE_SAVE:// 保存
			myView.savePicture(getString(R.string.Doodledroid) + r, 0);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * 选择形状
	 */
	public void selectShape() {
		final String[] mItems = { "直线", "矩形", "圆形", "涂鸦" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle(R.string.shape_setting);

		builder.setItems(mItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// 调用方法setDrawTool（）进行相应的实例化
				myView.setDrawTool(which);
			}

		}).setIcon(R.drawable.ic_launcher);

		builder.create().show();
	}

	/*
	 * 选择形状
	 */
	public void selectColor() {
		final String[] mItems = { "黑色", "蓝色", "红色", "绿色" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle(R.string.color_setting);

		builder.setItems(mItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// 调用方法setDrawTool（）进行相应的实例化
				myView.setColorTool(which);
			}

		}).setIcon(R.drawable.ic_launcher);

		builder.create().show();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
}
