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
	
	void setR(float r) { mRadius = r; }
	void setX(float x) { mX = x; }
	void setY(float y) { mY = y; }
	void setPaint(Paint p) { mPaint = p; }
	
	float getX() { return mX; }
	float getY() { return mY; }
	float getR() { return mRadius; }
	
	Paint getPaint() { return mPaint; }
}
