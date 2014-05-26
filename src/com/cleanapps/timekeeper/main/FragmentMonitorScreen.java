package com.cleanapps.timekeeper.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;
import com.google.android.gms.internal.go;
import com.squareup.timessquare.CalendarPickerView;

public class FragmentMonitorScreen extends Fragment {

	private static final String TAG = FragmentMonitorScreen.class
			.getSimpleName();
	private AlertDialog theDialog;
	private CalendarPickerView dialogView;
	private TextView calenderText;
	LinearLayout nullView;
	LinearLayout fullView;

	// private CalendarPickerView calendar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_monitor_screen, null);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		nullView = (LinearLayout) view
				.findViewById(R.id.fragment_monitor_screen_empty_container);
		fullView = (LinearLayout) view
				.findViewById(R.id.fragment_monitor_screen_data_container);
		fullView.setVisibility(View.GONE);
		initCalenderPicker(view);
	}

	private void initCalenderPicker(View v) {
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);
		calenderText = (TextView) v
				.findViewById(R.id.fragment_monitor_screen_calnder_text);
		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);
		// calendar = (CalendarPickerView) v.findViewById(R.id.calendar_view);
		// calendar.init(lastYear.getTime(), nextYear.getTime()) //
		// .inMode(SelectionMode.SINGLE) //
		// .withSelectedDate(new Date());
		final Button goBtn = (Button) v
				.findViewById(R.id.fragment_monitor_screen_calender_button);
		goBtn.setVisibility(View.GONE);
		goBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fullView.setVisibility(View.VISIBLE);
				nullView.setVisibility(View.GONE);
			}
		});

		calenderText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				dialogView = (CalendarPickerView) getActivity()
						.getLayoutInflater().inflate(
								R.layout.calender_picker_dialog, null, false);
				dialogView.init(lastYear.getTime(), nextYear.getTime()) //
						.withSelectedDate(new Date());
				theDialog = new AlertDialog.Builder(getActivity())
						.setTitle("Select Date")
						.setView(dialogView)
						.setNegativeButton("Dismiss",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialogInterface,
											int i) {
										dialogInterface.dismiss();
									}
								})
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Log.d(TAG, "Selected time in millis: "
												+ dialogView.getSelectedDate()
														.getTime());
										long milliSeconds = dialogView
												.getSelectedDate().getTime();
										SimpleDateFormat formatter = new SimpleDateFormat(
												"dd/MM/yyyy");

										Calendar calendar = Calendar
												.getInstance();
										calendar.setTimeInMillis(milliSeconds);
										System.out.println();
										String queryDate = formatter
												.format(calendar.getTime());
										Toast.makeText(getActivity(),
												queryDate, Toast.LENGTH_SHORT)
												.show();
										calenderText.setText(queryDate);
										
										goBtn.setVisibility(View.VISIBLE);
									}
								}).create();
				theDialog
						.setOnShowListener(new DialogInterface.OnShowListener() {
							@Override
							public void onShow(DialogInterface dialogInterface) {
								Log.d(TAG, "onShow: fix the dimens!");
								dialogView.fixDialogDimens();
							}
						});
				theDialog.show();
			}
		});

	}

}
