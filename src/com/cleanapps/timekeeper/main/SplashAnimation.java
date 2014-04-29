package com.cleanapps.timekeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleanapps.timekeeper.R;


public class SplashAnimation extends Activity implements AnimationListener {

	ImageView imgLogo,imgTextv;
	TextView titleText;
	// Animation
	Animation animSequential,animAppear;
	
	private static String TAG = SplashAnimation.class.getName();
	 private static long SLEEP_TIME = 5; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sequential_animation);
		
		
		
		titleText=(TextView)findViewById(R.id.activity_splashanim_title_text);
		animSequential = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequential);
		animSequential.setAnimationListener(this);
		titleText.startAnimation(animSequential);
		/*BreakMe bm = new BreakMe();
		bm.start();*/
//		imgTextv = (ImageView) findViewById(R.id.imgLogo_text);
//		animAppear = AnimationUtils.loadAnimation(getApplicationContext(),
//				R.anim.fade_in);
//		animAppear.setAnimationListener(this);
//		imgTextv.setVisibility(1);
//		imgTextv.startAnimation(animAppear);
		
		/*Intent intent = new Intent(SequentialActivity.this, MainActivity.class);
	     SequentialActivity.this.startActivity(intent);
	     SequentialActivity.this.finish();*/
		IntentLauncher launcher = new IntentLauncher();
		  launcher.start();

	}
	private class IntentLauncher extends Thread {
		  @Override
		  /**
		   * Sleep for some time and than start new activity.
		   */
		  public void run() {
		     try {
		        // Sleeping
		        Thread.sleep(SLEEP_TIME*1000);
		     } catch (Exception e) {
		        Log.e(TAG, e.getMessage());
		     }

		     // Start main activity
		     Intent intent = new Intent(SplashAnimation.this, ActivityRegistration.class);
		     SplashAnimation.this.startActivity(intent);
		     SplashAnimation.this.finish();
		  }
		}
	
	

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		/*if (animation == animSequential) {
		}*/
		

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}
	 

}
