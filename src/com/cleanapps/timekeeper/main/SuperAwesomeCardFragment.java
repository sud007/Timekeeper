package com.cleanapps.timekeeper.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;

public class SuperAwesomeCardFragment extends Fragment {

	private static final String ARG_POSITION = "position";

	private int position;

	public static SuperAwesomeCardFragment newInstance(int position) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		View v = new View(getActivity());
		v.setLayoutParams(params);

		// final int margin = (int) TypedValue.applyDimension(
		// TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
		// .getDisplayMetrics());

		switch (position) {
		case 0:
			v = inflater.inflate(R.layout.fragment_timer_screen, null);
			break;
		case 1:
			v = inflater.inflate(R.layout.fragment_monitor_screen, null);
			break;
		case 2:
			v = inflater.inflate(R.layout.fragment_graphs_screen, null);
			break;
		}

		return v;
	}
}