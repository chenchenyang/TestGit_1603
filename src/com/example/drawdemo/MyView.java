package com.example.drawdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/*
 * ʵ�ַ�ʽ��
 * ����������bitmap�ķ�ʽ��
 * �ײ�����bitmap��floorBitmap, surfaceBitmap;���bitmapΪ͸��ɫ������Ḳ�ǵ��ײ�bitmap��ͼ��
 * ��ǰ��ͼ�����ڱ��bitmap��surfaceBitmap������ı仭�ʣ��򽫵�ǰsurfaceBitmap�����ݻ��Ƶ��ײ�bitmap��floorBitmap
 * ���ѡ����Ƥ������Ҫ�ڵײ�bitmap�Ͻ��л��ƣ�
 * �鿴ԭͼƬ��Ҳ�ǽ�ͼƬ���Ƶ��ײ�bitmap
 */
public class MyView extends View {

	private DrawBS drawBS = null;
	private Point evevtPoint;
	private Bitmap floorBitmap, surfaceBitmap;// �ײ�����bitmap
	private Canvas floorCanvas, surfaceCanvas;// bitmap��Ӧ��canvas

	private boolean isEraser = false;

	Bitmap newbm;

	@SuppressLint("ParserError")
	public MyView(Context context) {
		super(context);

		// ��ʼ��drawBS����drawBSĬ��ΪDrawPath��
		drawBS = new DrawScrawl();
		evevtPoint = new Point();

		// �ײ�bitmap��canvas��
		floorBitmap = Bitmap.createBitmap(1980, 800, Bitmap.Config.ARGB_8888);
		floorCanvas = new Canvas(floorBitmap);

		// ����bitmap�����ڵײ�bitmap֮�ϣ����ڸ�ֵ���Ƶ�ǰ��������ͼ�Σ���Ҫ����Ϊ͸�������򸲸ǵײ�bitmap
		surfaceBitmap = Bitmap.createBitmap(1980, 800, Bitmap.Config.ARGB_8888);
		surfaceCanvas = new Canvas(surfaceBitmap);
		surfaceCanvas.drawColor(Color.TRANSPARENT);

	}

	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// ���ײ�bitmap��ͼ�λ��Ƶ�������

		canvas.drawBitmap(floorBitmap, 0, 0, null);

		// �ж�ѡ���ͼ���Ƿ�Ϊ��Ƥ
		if (isEraser) {
			int color1 = Color.BLUE;
			// �������Ƥ���û����ڵײ�bitmap�Ͻ��в�����
			/*
			 * ���ݵײ�Canvas������ ������Ӧ�Ļ�ͼ�����෽��,�ڵײ�bitmap��ʹ��floorCanvas���л�ͼ
			 */
			drawBS.onDraw(floorCanvas, color1);
			canvas.drawBitmap(surfaceBitmap, 0, 0, null);

		} else {
			int color = DrawBS.color;
			// ���������Ƥ�����û����ڱ��bitmap�Ͻ��в�����
			/*
			 * ���ݱ��Canvas������ ������Ӧ��ͼ�����෽��,�ڱ��bitmap��ʹ��surfaceCanvas���л�ͼ
			 */
			drawBS.onDraw(surfaceCanvas, color);
			canvas.drawBitmap(surfaceBitmap, 0, 0, null);
		}

	}

	// �����¼���������Ӧ�Ļ�ͼ��������в���
	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		evevtPoint.set((int) event.getX(), (int) event.getY());

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			drawBS.onTouchDown(evevtPoint);
			break;

		case MotionEvent.ACTION_MOVE:
			drawBS.onTouchMove(evevtPoint);
			/*
			 * �϶������в�ͣ�Ľ�bitmap����ɫ����Ϊ͸������ձ��bitmap�� ���������϶����̵Ĺ켣���ử����
			 */
			surfaceBitmap.eraseColor(Color.TRANSPARENT);
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			drawBS.onTouchUp(evevtPoint);
			// �������ѡ����ͼ�Σ�����Ҫ�����bitmap�ϵ�ͼ����Ƶ��ײ�bitmap�Ͻ��б���
			floorCanvas.drawBitmap(surfaceBitmap, 0, 0, null);
			break;
		default:
			break;
		}
		return true;
	}

	// ѡ��ͼ�Σ�ʵ������Ӧ����
	public void setDrawTool(int i) {
		switch (i) {
		case 0:
			drawBS = new DrawLine();
			break;
		case 1:
			drawBS = new DrawRectangle();
			break;
		case 2:
			drawBS = new DrawCircle();
			break;
		case 10:
			// �����Ҫ��Ƥ����ʵ�����������û��ʵĹ��췽��
			drawBS = new DrawPath(10);// ��Ƥ
			break;
		default:
			drawBS = new DrawScrawl();
			break;
		}

		// ���ѡ����Ƥ��isEraser = true
		if (i == 10) {
			isEraser = true;
		} else {
			isEraser = false;
		}
		// �������ѡ����ͼ�Σ�����Ҫ�����bitmap�ϵ�ͼ����Ƶ��ײ�bitmap�Ͻ��б���
		floorCanvas.drawBitmap(surfaceBitmap, 0, 0, null);
	}

	/*
	 * ��ɫѡ��
	 */
	public void setColorTool(int i) {
		switch (i) {
		case 0:
			// ��ɫ
			DrawBS.color = Color.BLACK;
			break;
		case 1:
			// ��ɫ
			DrawBS.color = Color.BLUE;
			break;
		case 2:
			// ��ɫ
			DrawBS.color = Color.RED;
			break;
		case 3:
			// ��ɫ
			DrawBS.color = Color.GREEN;
			break;
		}
	}

	// ��ͼƬ�����ڴ濨
	@SuppressLint("SdCardPath")
	public void savePicture(String draw_name, int alpha) {
		FileOutputStream fos = null;
		String type = null;

		if (alpha == 0) {// ��͸��
			type = ".jpg";
		} else {
			type = ".png";
		}
		try {
			String strPath = new String("/sdcard/HBImg/");
			File fPath = new File(strPath);
			if (!fPath.exists()) {
				fPath.mkdir();
			}

			File f = new File(strPath + draw_name.trim() + type);
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			fos = new FileOutputStream(f);
			Bitmap b = null;
			destroyDrawingCache();
			setDrawingCacheEnabled(true);

			buildDrawingCache();
			b = getDrawingCache();

			if (b != null) {
				b.compress(Bitmap.CompressFormat.PNG, 100, fos);
				if (!b.isRecycled())
					b.recycle();
				b = null;
				System.gc();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("send picture to dbserver", "�ر��ϴ�ͼƬ��������ʧ�ܣ�");
				}
			}
		}
	}

}
