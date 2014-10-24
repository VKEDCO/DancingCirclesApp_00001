package org.vkedco.mobappdev.dancing_circles;


/*
***************************************************
* PainterView.java - custom view of DancingCirclesAct.java
*
* bugs to vladimir dot kulyukin at gmail dot com
***************************************************
*/

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PainterView extends View {
	private Paint mBackgroundPaint = null;
	private Paint mForeCircleRedPaint = null;
	private Paint mForeCircleBluePaint = null;
	private Paint mForeCircleMagentaPaint = null;
	private Paint mForeCircleGrayPaint = null;
	private ArrayList<Circle> mCircles = null;
	private Circle mRedCircle = null;
	private Circle mBlueCircle = null;
	private Circle mMagentaCircle = null;
	private Circle mGrayCircle = null;
	
	public PainterView(Context context, AttributeSet atrs) {
		super(context, atrs);

		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(Color.GREEN);
		
		mForeCircleRedPaint = new Paint();
		mForeCircleRedPaint.setColor(Color.RED);
		mForeCircleRedPaint.setAntiAlias(true);
		
		mForeCircleBluePaint = new Paint();
		mForeCircleBluePaint.setColor(Color.BLUE);
		mForeCircleBluePaint.setAntiAlias(true);
		
		mForeCircleMagentaPaint = new Paint();
		mForeCircleMagentaPaint.setColor(Color.MAGENTA);
		mForeCircleMagentaPaint.setAntiAlias(true);
		
		mForeCircleGrayPaint = new Paint();
		mForeCircleGrayPaint.setColor(Color.DKGRAY);
		mForeCircleGrayPaint.setAntiAlias(true);
		
		createCircles();
	}
	
	private void createCircles() {		
		mRedCircle     = new Circle(30,  30,  20, mForeCircleRedPaint);
		mBlueCircle    = new Circle(450, 30,  20, mForeCircleBluePaint);
		mMagentaCircle = new Circle(30,  690, 20, mForeCircleMagentaPaint);
		mGrayCircle    = new Circle(450, 690, 20, mForeCircleGrayPaint);
		
		mCircles = new ArrayList<Circle>();
		
		mCircles.add(mRedCircle);
		mCircles.add(mBlueCircle);
		mCircles.add(mMagentaCircle);
		mCircles.add(mGrayCircle);	
	}
	
	// This method redraws the entire canvas
	public void draw(Canvas canvas) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// 1. draw background rectangle that covers the entire
		// canvas
		canvas.drawRect(0, 0, width, height, mBackgroundPaint);
		// 2. draw red circles on canvas
		drawCirclesOnCanvas(canvas);
		// 3. force redraw
		invalidate();
	}
	
	// draw all Circles in mCricles
	private void drawCirclesOnCanvas(Canvas canvas) {
		for(Circle c : mCircles) {
			canvas.drawCircle(c.getX(), c.getY(), c.getR(), c.getPaint());
		}
	}
	
	public Circle getRedCircle() {
		return mRedCircle;
	}
	
	public Circle getBlueCircle() {
		return mBlueCircle;
	}
	
	public Circle getMagentaCircle() {
		return mMagentaCircle;
	}
	
	public Circle getGrayCircle() {
		return mGrayCircle;
	}
	
	public void randomizeRedCircle() {
		randomizeCircle(mRedCircle);
	}
	
	public void randomizeBlueCircle() {
		randomizeCircle(mBlueCircle);
	}
	
	public void randomizeMagentaCircle() {
		randomizeCircle(mMagentaCircle);
	}
	
	public void randomizeGrayCircle() {
		randomizeCircle(mGrayCircle);
	}
	
	private void randomizeCircle(Circle c) {
		Random rand = new Random();
		float x = (float)(30 + rand.nextInt(500));
		float y = (float)(30 + rand.nextInt(600));
		c.setX(x);
		c.setY(y);
	}
	
	
}

