package com.cleanapps.timekeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;
import com.cleanapps.timekeeper.util.TimeKeeperUtilMethods;

public class FragmentTimerScreen extends Fragment {
	private Button id;
	private TextView timerView;
	private long logInDuration;
	private LoginTimeCounter timer;
	private Boolean isLoggedIn;
	private RelativeLayout rel;

	private final String tag = FragmentTimerScreen.class.getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_timer_screen, null);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		isLoggedIn = TimeKeeperUtilMethods.getLoginStatus(getActivity());
		Log.i(tag, isLoggedIn + "");
		Log.e(tag, "onViewCreated");
		timerView = (TextView) view
				.findViewById(R.id.fragment_timer_screen_login_time);
		rel = (RelativeLayout) view
				.findViewById(R.id.fragment_timer_screen_add_details_btn);
		
		rel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "X", Toast.LENGTH_SHORT).show();
				getActivity().startActivity(
						new Intent(getActivity(), ActivityDetails.class));

			}
		});
		id = (Button) view.findViewById(R.id.fragment_timer_screen_login_btn);
		id.setBackgroundDrawable(getActivity().getResources()
				.getDrawable(R.drawable.login_button_selector));
		id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				isLoggedIn = TimeKeeperUtilMethods
						.getLoginStatus(getActivity());
				if (!isLoggedIn) {

					long currentTimeInMillis = System.currentTimeMillis();
					String date = TimeKeeperUtilMethods.getDate();
					TimeKeeperUtilMethods.setLoginInfo(getActivity(), date,
							currentTimeInMillis);
					TimeKeeperUtilMethods.setLoginStatus(getActivity(), true);
					TimeKeeperUtilMethods.setTimerRunning(getActivity(), true);
					id.setBackgroundDrawable(getActivity().getResources()
							.getDrawable(R.drawable.logout_button_selector));
					id.setText(getActivity().getResources().getString(
							R.string.fragment_activity_button_logout));
					timer = new LoginTimeCounter(currentTimeInMillis, 1000);
					timer.Start();
					TimeKeeperUtilMethods.animateTextColor(timerView);
					rel.setVisibility(View.VISIBLE);
					// TimeKeeperUtilMethods.animateTextColor(timerView,getResources().getColor(R.color.green_login));

				} else {
					TimeKeeperUtilMethods.setLoginStatus(getActivity(), false);
					TimeKeeperUtilMethods.setTimerRunning(getActivity(), false);
					TimeKeeperUtilMethods.setLoginInfo(getActivity(), "", 0);
					timer.StopTimer();
					timerView.setTextColor(getResources().getColor(
							R.color.black));
					id.setBackgroundDrawable(getActivity().getResources()
							.getDrawable(R.drawable.login_button_selector));
					id.setText(getActivity().getResources().getString(
							R.string.fragment_activity_button_login));
					rel.setVisibility(View.GONE);

				}

			}
		});

		setView(view);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.e(tag, "onStop");
		super.onStop();
	}

	private void setView(View view) {

		int density = getResources().getDisplayMetrics().densityDpi;
		LayoutParams params = id.getLayoutParams();

		switch (density) {

		case DisplayMetrics.DENSITY_LOW:
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			break;
		case DisplayMetrics.DENSITY_HIGH:
			break;
		case DisplayMetrics.DENSITY_XHIGH:

			params.width = 240;
			params.height = 240;

			// Toast.makeText(getActivity(), "X", Toast.LENGTH_SHORT).show();

			break;
		case DisplayMetrics.DENSITY_XXHIGH:
			// Toast.makeText(getActivity(), "XX", Toast.LENGTH_SHORT).show();
			params.width = 320;
			params.height = 320;
			break;
		case DisplayMetrics.DENSITY_XXXHIGH:
			// Toast.makeText(getActivity(), "XXX", Toast.LENGTH_SHORT).show();
			id.setWidth(320);
			id.setHeight(320);
			break;

		}

		id.setLayoutParams(params);

		// if (isLoggedIn) {
		//
		// TimeKeeperUtilMethods.animateTextColor(timerView);
		//
		// if (TimeKeeperUtilMethods.getTimerRunning(getActivity()) == false) {
		//
		// long currentTimeInMillis = TimeKeeperUtilMethods
		// .getLoginTime(getActivity());
		// TimeKeeperUtilMethods.setLoginStatus(getActivity(), true);
		// timer = new LoginTimeCounter(currentTimeInMillis, 1000);
		// timer.Start();
		// }
		// }
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.e(tag, "onPause");
		super.onPause();
		if (TimeKeeperUtilMethods.getTimerRunning(getActivity()) == true) {

			timer.StopTimer();
			TimeKeeperUtilMethods.setTimerRunning(getActivity(), false);

		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.e(tag, "onStart");

		if (isLoggedIn) {

			TimeKeeperUtilMethods.animateTextColor(timerView);
			id.setBackgroundDrawable(getActivity().getResources().getDrawable(
					R.drawable.logout_button_selector));
			rel.setVisibility(View.VISIBLE);
			id.setText(getActivity().getResources().getString(
					R.string.fragment_activity_button_logout));
			if (TimeKeeperUtilMethods.getTimerRunning(getActivity()) == false) {

				long currentTimeInMillis = TimeKeeperUtilMethods
						.getLoginTime(getActivity());
				TimeKeeperUtilMethods.setLoginStatus(getActivity(), true);
				TimeKeeperUtilMethods.setTimerRunning(getActivity(), true);

				timer = new LoginTimeCounter(currentTimeInMillis, 1000);
				timer.Start();
			}
		}
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e(tag, "onCreate");
		super.onCreate(savedInstanceState);
	}

	// ###########################################################################
	/**
	 * class for counting the remaining time and display it to the user.
	 * 
	 * @author sudhanshusingh
	 * 
	 */
	public class LoginTimeCounter {
		private long startTime;
		private long countDownInterval;
		private boolean status;
		long logInDurationSeconds, subt, systemTime, systemTimeInMillis;
		String serverUptimeText;
		String daysT = "", displayTime = "";
		final Handler handler = new Handler();

		long daysLeft, hoursLeft, minutesLeft, secondsLeft;

		public LoginTimeCounter(long startTime, long pCountDownInterval) {
			this.startTime = startTime;
			this.countDownInterval = pCountDownInterval;
			status = false;
			Initialize();
		}

		public void StopTimer() {
			status = false;
			handler.removeCallbacksAndMessages(null);
			timerView.setText("00:00:00");
		}

		public void getCurrentTime() {

		}

		public void Start() {
			status = true;
		}

		public void Initialize() {

			systemTime = System.currentTimeMillis();
			systemTimeInMillis = systemTime;

			logInDuration = (long) systemTimeInMillis - startTime;

			final Runnable counter = new Runnable() {

				public void run() {

					logInDurationSeconds = logInDuration / 1000;

					hoursLeft = (Math.round(logInDurationSeconds) / 3600)
							- (daysLeft * 24);
					minutesLeft = (Math.round(logInDurationSeconds) / 60)
							- (daysLeft * 1440) - (hoursLeft * 60);
					secondsLeft = Math.round(logInDurationSeconds) % 60;

					displayTime = String.format("%02d:%02d:%02d", hoursLeft,
							minutesLeft, secondsLeft);

					if (status) {

						logInDuration += countDownInterval;
						timerView.setText(displayTime);
						handler.postDelayed(this, countDownInterval);
					} else {
						handler.postDelayed(this, countDownInterval);
					}
				}
			};

			handler.postDelayed(counter, countDownInterval);
		}
	}
}
