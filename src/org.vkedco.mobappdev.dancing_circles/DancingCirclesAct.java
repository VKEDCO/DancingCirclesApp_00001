package org.vkedco.mobappdev.dancing_circles;

/*
 ******************************************************** 
 * Main activity of the DancingCirclesApp.
 * The application creates four circle objects on the
 * PainterView's canvas and uses the threads to randomly
 * move the circles on the canvas.
 * 
 * The activity defines an options menu with three items:
 * Synch Thread Dance, Unsynch Thread Dance, Stop the Dance. 
 * 
 * When the user presses Synch Dance, the activity starts four
 * worker threads with Runnables that synchronize
 * on the PainterView object in DancingCirclesApp. Thus,
 * the configuration of the circles on the canvas of the
 * PainterView changes one circle at a time. It should be
 * noted that one circle thread may usurp the CPU cycles
 * so that only one circle will be moved for a time.
 * 
 * When the user selects Unsynch Dance, the activity starts
 * four worker threads with Runnables that do not synchronize
 * on the PainterView object in DancingCirclesApp. Thus,
 * the configuration of the circles on the canvas of the
 * PainterView changes four circles at a time.
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com.
 ********************************************************
 */

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;

public class DancingCirclesAct extends Activity {

	DancingCirclesApp mApp = null;
	Thread mRedCircleThread = null;
	Thread mBlueCircleThread = null;
	Thread mMagentaCircleThread = null;
	Thread mGrayCircleThread = null;
	final static int SLEEP_INTERVAL = 1000;
	final static Random mRandom = new Random();

	// syncrhonized runnable
	class SynchronizedPainterRunnable implements Runnable {
		DancingCirclesApp.CIRCLE_COLOR mColor = null;
		boolean mThreadRunning = false;

		public SynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR c) {
			mColor = c;
		}

		@Override
		public void run() {
			mThreadRunning = true;
			while (mThreadRunning) {
				synchronized (mApp) {
					switch (mColor) {
					case RED:
						mApp.getPainterView().randomizeRedCircle();
						break;
					case BLUE:
						mApp.getPainterView().randomizeBlueCircle();
						break;
					case MAGENTA:
						mApp.getPainterView().randomizeMagentaCircle();
						break;
					case GRAY:
						mApp.getPainterView().randomizeGrayCircle();
						break;
					default:
						break;
					}

					try {
						// Have the thread sleep
						Thread.sleep(DancingCirclesAct.mRandom.nextInt(500) + 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
						mThreadRunning = false;
					}
				}
				// Give other threads a chance to run
				Thread.currentThread().yield();
			}
		}
	}

	class UnsynchronizedPainterRunnable implements Runnable {
		DancingCirclesApp.CIRCLE_COLOR mColor = null;
		boolean mThreadRunning = false;

		public UnsynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR c) {
			mColor = c;
		}

		@Override
		public void run() {
			mThreadRunning = true;
			while (mThreadRunning) {
				// no synchronization here: no synchronized block.
				switch (mColor) {
				case RED:
					mApp.getPainterView().randomizeRedCircle();
					break;
				case BLUE:
					mApp.getPainterView().randomizeBlueCircle();
					break;
				case MAGENTA:
					mApp.getPainterView().randomizeMagentaCircle();
					break;
				case GRAY:
					mApp.getPainterView().randomizeGrayCircle();
					break;
				default:
					break;
				}

				try {
					// Have the thread sleep
					Thread.sleep(DancingCirclesAct.mRandom.nextInt(500) + 500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					mThreadRunning = false;
				}
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dancing_circles);
		PainterView pw = (PainterView) this.findViewById(R.id.pntr);
		mApp = (DancingCirclesApp) getApplication();
		mApp.setPainterView(pw);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_dancing_circles, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch ( item.getItemId() ) {
		case R.id.start_synch_threads:
			interruptThreads();
			startSynchronizedThreads();
			break;
		case R.id.start_unsynch_threads:
			interruptThreads();
			startUnsynchronizedThreads();
			break;
		case R.id.stop:
			interruptThreads();
			break;
		}
		return true;
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		interruptThreads();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		interruptThreads();
	}

	private void interruptThreads() {
		if (mRedCircleThread != null) {
			if (mRedCircleThread.isAlive()) {
				try {
					mRedCircleThread.interrupt();
				} catch (Exception e) {
					mRedCircleThread = null;
				}
			} else {
				mRedCircleThread = null;
			}
		}

		if (mBlueCircleThread != null) {
			if (mBlueCircleThread.isAlive()) {
				try {
					mBlueCircleThread.interrupt();
				} catch (Exception e) {
					mBlueCircleThread = null;
				}
			} else {
				mBlueCircleThread = null;
			}
		}

		if (mMagentaCircleThread != null) {
			if (mMagentaCircleThread.isAlive()) {
				try {
					mMagentaCircleThread.interrupt();
				} catch (Exception e) {
					mMagentaCircleThread = null;
				}
			} else {
				mMagentaCircleThread = null;
			}
		}

		if (mGrayCircleThread != null) {
			if (mGrayCircleThread.isAlive()) {
				try {
					mGrayCircleThread.interrupt();
				} catch (Exception e) {
					mGrayCircleThread = null;
				}
			} else {
				mGrayCircleThread = null;
			}
		}
	}

	private void startSynchronizedThreads() {
		mRedCircleThread = new Thread(new SynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.RED));
		mBlueCircleThread = new Thread(new SynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.BLUE));
		mMagentaCircleThread = new Thread(new SynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.MAGENTA));
		mGrayCircleThread = new Thread(new SynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.GRAY));
		mRedCircleThread.start();
		mBlueCircleThread.start();
		mMagentaCircleThread.start();
		mGrayCircleThread.start();
	}
	
	private void startUnsynchronizedThreads() {
		mRedCircleThread = new Thread(new UnsynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.RED));
		mBlueCircleThread = new Thread(new UnsynchronizedPainterRunnable(DancingCirclesApp.CIRCLE_COLOR.BLUE));
		mMagentaCircleThread = new Thread(new UnsynchronizedPainterRunnable(
				DancingCirclesApp.CIRCLE_COLOR.MAGENTA));
		mGrayCircleThread = new Thread(new UnsynchronizedPainterRunnable(
				DancingCirclesApp.CIRCLE_COLOR.GRAY));
		mRedCircleThread.start();
		mBlueCircleThread.start();
		mMagentaCircleThread.start();
		mGrayCircleThread.start();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  // ignore orientation/keyboard change
	  super.onConfigurationChanged(newConfig);
	}
}
