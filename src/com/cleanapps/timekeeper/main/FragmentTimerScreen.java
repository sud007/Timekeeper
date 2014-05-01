package com.cleanapps.timekeeper.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;

public class FragmentTimerScreen extends Fragment {
	private Button id;
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

		id = (Button) view.findViewById(R.id.dididid);
		id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Toast.makeText(getActivity(), "yaykbkj", Toast.LENGTH_SHORT).show();
				Log.i(tag, "oh");
			}
		});
	}

}
