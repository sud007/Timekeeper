package com.cleanapps.timekeeper.components;

	
	import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

	public class MorphedTextView extends TextView {


	    public MorphedTextView(Context context) {
	      super(context);
	      Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf"); 
	      this.setTypeface(face); 
	    }

	    public MorphedTextView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	     Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf"); 
	  this.setTypeface(face); 
	    }

	    public MorphedTextView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	     Typeface face=Typeface.createFromAsset(context.getAssets(), "Sweetly_Broken.ttf"); 
	  this.setTypeface(face); 
	    }

	    
	    protected void onDraw (Canvas canvas) {
	        super.onDraw(canvas);
	        
	       
	    }

	}

