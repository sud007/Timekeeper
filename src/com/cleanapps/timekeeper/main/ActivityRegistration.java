package com.cleanapps.timekeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;
import com.cleanapps.timekeeper.components.ParallaxImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;

public class ActivityRegistration extends Activity implements OnClickListener,
ConnectionCallbacks, OnConnectionFailedListener {

	private Button gSignIn;
	private final String tag = ActivityRegistration.class.getSimpleName();
	
	private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";
 
    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;
 
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
 
    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;
 
    private boolean mSignInClicked;
 
    private ConnectionResult mConnectionResult;
 
    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private LinearLayout llProfileLayout;

	private ParallaxImageView mBackground;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_registration1);

		initView();

	}

	private void initView() {
		btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
		btnSignIn.setOnClickListener(this);
		gSignIn = (Button) findViewById(R.id.activity_registration_sign_in_button);
		gSignIn.setOnClickListener(this);
		mBackground = (ParallaxImageView) findViewById(R.id.background);
		mBackground.setForwardTiltOffset(.35f);
		mBackground.setParallaxIntensity(1.1f);
		mBackground.setImageDrawable(getResources().getDrawable(
				R.drawable.registration_bg));
		mBackground.registerSensorManager();

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// Initializing google plus api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

	}

	@Override
	public void onResume() {
		super.onResume();

		mBackground.registerSensorManager();
	}

	@Override
	public void onPause() {
		mBackground.unregisterSensorManager();
		super.onPause();
	}

	protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
 
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.activity_registration_sign_in_button:
			Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
			
			 Intent intent = new Intent(ActivityRegistration.this, TimekeeperMain.class);
			 ActivityRegistration.this.startActivity(intent);
			 ActivityRegistration.this.finish();

			break;
		case R.id.btn_sign_in:
			signInWithGplus();
			break;

		default:
			break;
		}

	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	    if (!result.hasResolution()) {
	        GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
	                0).show();
	        return;
	    }
	 
	    if (!mIntentInProgress) {
	        // Store the ConnectionResult for later usage
	        mConnectionResult = result;
	 
	        if (mSignInClicked) {
	            // The user has already clicked 'sign-in' so we attempt to
	            // resolve all
	            // errors until the user is signed in, or they cancel.
	            resolveSignInError();
	        }
	    }
	 
	}
	 
	@Override
	protected void onActivityResult(int requestCode, int responseCode,
	        Intent intent) {
	    if (requestCode == RC_SIGN_IN) {
	        if (responseCode != RESULT_OK) {
	            mSignInClicked = false;
	        }
	 
	        mIntentInProgress = false;
	 
	        if (!mGoogleApiClient.isConnecting()) {
	            mGoogleApiClient.connect();
	        }
	    }
	}
	 
	@Override
	public void onConnected(Bundle arg0) {
	    mSignInClicked = false;
	    Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
	 
	    // Get user's information
//	    getProfileInformation();
	 
	    // Update the UI after signin
	    updateUI(true);
	 
	}
	 
	@Override
	public void onConnectionSuspended(int arg0) {
	    mGoogleApiClient.connect();
	    updateUI(false);
	}
	 
	/**
	 * Updating the UI, showing/hiding buttons and profile layout
	 * */
	private void updateUI(boolean isSignedIn) {
	    if (isSignedIn) {
	        btnSignIn.setVisibility(View.GONE);
	        btnSignOut.setVisibility(View.VISIBLE);
	        btnRevokeAccess.setVisibility(View.VISIBLE);
	        llProfileLayout.setVisibility(View.VISIBLE);
	    } else {
	        btnSignIn.setVisibility(View.VISIBLE);
	        btnSignOut.setVisibility(View.GONE);
	        btnRevokeAccess.setVisibility(View.GONE);
	        llProfileLayout.setVisibility(View.GONE);
	    }
	}
	/**
	 * Sign-in into google
	 * */
	private void signInWithGplus() {
	    if (!mGoogleApiClient.isConnecting()) {
	        mSignInClicked = true;
	        resolveSignInError();
	    }
	}
	 
	/**
	 * Method to resolve any signin errors
	 * */
	private void resolveSignInError() {
	    if (mConnectionResult.hasResolution()) {
	        try {
	            mIntentInProgress = true;
	            mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
	        } catch (SendIntentException e) {
	            mIntentInProgress = false;
	            mGoogleApiClient.connect();
	        }
	    }
	}

}
