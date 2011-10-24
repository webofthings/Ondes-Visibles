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
 * This class is used to display the references to the two brochures
 * that come with Ondes Visibles!
 * 1 is brochure: Electrosmog au quotidien
 * 2 is brochure: L'electrosmog dans l'environment 
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class MoreInfoActivity extends Activity {
	private String moreInfo1;
    private String moreInfo2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_info);
		setTitle(getResources().getString(R.string.more_info_title));
	}
	
	protected void onResume() {
		super.onResume();
		Bundle params = getIntent().getExtras();
		moreInfo1 = params.getString(Params.MORE_INFO_1_KEY);
		moreInfo2 = params.getString(Params.MORE_INFO_2_KEY);
		TextView tv = (TextView) findViewById(R.id.more_info);
		tv.setText(prepareMoreInfoString());	
	}
	
	protected String prepareMoreInfoString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getString(R.string.more_info_description));
		buf.append(wrapInSpaces(moreInfo1));
		buf.append(getString(R.string.more_info_flyer_1));
		buf.append(wrapInSpaces(moreInfo2));
		buf.append(getString(R.string.more_info_flyer_2));
		return buf.toString();
	}
	
	/**
	 * @return the moreInfo1
	 */
	protected String getMoreInfo1() {
		return moreInfo1;
	}

	/**
	 * @return the moreInfo2
	 */
	protected String getMoreInfo2() {
		return moreInfo2;
	}
	
	protected String wrapInSpaces(String value) {
		return " " + value + " ";
	}
	
}
