/**
 * ondes-visibles 
 * Oct 24, 2011
 */
package com.webofthings.emf;

/**
 * This class is used to display the references to the two brochures
 * that come with Ondes Visibles! depending on the currently selected device.
 * 1 is brochure: Electrosmog au quotidien
 * 2 is brochure: L'electrosmog dans l'environment 
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class MoreInfoDeviceActivity extends MoreInfoActivity {
	
	@Override
	protected String prepareMoreInfoString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getString(R.string.more_info_description_device));
		buf.append(super.wrapInSpaces(super.getMoreInfo1()));
		buf.append(getString(R.string.more_info_flyer_1));
		buf.append(super.wrapInSpaces(super.getMoreInfo2()));
		buf.append(getString(R.string.more_info_flyer_2));
		return buf.toString();
	}
}
