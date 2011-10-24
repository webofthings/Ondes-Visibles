/**
 * ondes-visibles 
 * Oct 24, 2011
 */
package com.webofthings.emf;

import com.webofthings.emf.utils.Params;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This is the about dialog, showing information about the authors of the application.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class AboutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.experiment_details);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		TextView tv = (TextView) findViewById(R.id.experiment_details_textview);
		setTitle(getString(R.string.about_activity_title));
		tv.setText(getString(R.string.about));
	}
}
