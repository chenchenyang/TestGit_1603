package com.example.drawdemo;

import android.graphics.Canvas;
import android.graphics.Point;
/*
 * ��Բ
 * 
 */
public class DrawCircle extends DrawBS {

	private Point rDotsPoint;//Բ��
	private int radius = 0;//�뾶
	private Double dtance = 0.0;//��ǰ�㵽Բ�ĵľ���
	
	public DrawCircle() {
		// TODO Auto-generated constructor stub
		rDotsPoint = new Point();
	}
	  
	
	
	public void onTouchDown(Point point) {
		downPoint.set(point.x, point.y);

		if (radius != 0) {
			//���㵱ǰ������ĵ㵽Բ�ĵľ���
			dtance = Math.sqrt((downPoint.x - rDotsPoint.x)
					* (downPoint.x - rDotsPoint.x)
					+ (downPoint.y - rDotsPoint.y)
					* (downPoint.y - rDotsPoint.y));
			// �������뾶��20�ͼ�20����Χ�ڣ�����Ϊ�û������Բ��
			if (dtance >= radius - 20 && dtance <= radius + 20) {
				downState = 1;
			//�������С�ڰ뾶������Ϊ�û������Բ��
			} else if (dtance < radius) {
				downState = -1;
			// ��ǰ����ĵ���԰��
			} else if (dtance > radius) {
				downState = 0;
			}
		} else {
			downState = 0;
		}
	}   
	  
	public void onTouchMove(Point point) {

			rDotsPoint.set(downPoint.x, downPoint.y);
			radius = (int) Math.sqrt((point.x - rDotsPoint.x)
					* (point.x - rDotsPoint.x)
					+ (point.y - rDotsPoint.y)
					* (point.y - rDotsPoint.y));
	}   
	  
	
	public void onDraw(Canvas canvas, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		canvas.drawCircle(rDotsPoint.x, rDotsPoint.y, radius, paint);// ��Բ
	}
	  

}
