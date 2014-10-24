package org.vkedco.mobappdev.dancing_circles;

/*
 ******************************************************** 
 * DancingCirclesApp.java is a custom application class.
 * The application creates four circle objects on the
 * PainterView's canvas and uses the threads to randomly
 * move the circles on the canvas.
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com.
 ********************************************************
 */

import android.app.Application;

public class DancingCirclesApp extends Application {
	static enum CIRCLE_COLOR {
		RED, BLUE, MAGENTA, GRAY
	};
	
	protected PainterView mPainterView = null;
	
	public DancingCirclesApp() {}
	
	void setPainterView(PainterView pw) {
		mPainterView = pw;	
	}
	
	PainterView getPainterView() {
		return mPainterView;
	}
}
