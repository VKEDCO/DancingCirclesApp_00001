package org.vkedco.mobappdev.dancing_circles;

import android.graphics.Paint;

/*************************************************
 * Circle class defines Circle objects drawn
 * by PainterView defined in PainterView.java.  
 *
 * The user can drag and drop circles all
 * over canvas (e.g., place them on the board).
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com
 *************************************************
 */

public class Circle {
	float mRadius = 20;
	float mX = 30;
	float mY = 30;
	Paint mPaint = null;
	
	public Circle(float x, float y, float r, Paint p) {
		mX = x;
		mY = y;
		mRadius = r;
		mPaint = p;
	}
	
	public void setR(float r) { mRadius = r; }
	public void setX(float x) { mX = x; }
	public void setY(float y) { mY = y; }
	public void setPaint(Paint p) { mPaint = p; }
	
	public float getX() { return mX; }
	public float getY() { return mY; }
	public float getR() { return mRadius; }
	
	public Paint getPaint() { return mPaint; }
}
