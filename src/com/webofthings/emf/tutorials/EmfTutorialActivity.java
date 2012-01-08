package com.webofthings.emf.tutorials;

import java.util.Arrays;
import java.util.LinkedList;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

/**
 * This is the tutorial about the basics of EMFs.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class EmfTutorialActivity extends AbstractTutorialActivity {
	LinkedList<InfoData> infoDataList;

	protected LinkedList<InfoData> getInfoData() {
		if (infoDataList == null) {
			infoDataList = new LinkedList<InfoData>();
			String moreInfo1 = getString(R.string.tut_1_more_info_1);
			String moreInfo2 = getString(R.string.tut_1_more_info_2);
			infoDataList.add(new InfoData(R.string.tut_1_a_text,
					R.drawable.tut_1_a, moreInfo1, moreInfo2));
			infoDataList.add(new InfoData(R.string.tut_1_b_text,
					R.drawable.tut_1_b, moreInfo1, moreInfo2));
			infoDataList.add(new InfoData(R.string.tut_1_c_text,
					R.drawable.tut_1_c, moreInfo1, moreInfo2));
			infoDataList.add(new InfoData(R.string.tut_1_d_text,
					R.drawable.tut_1_d, moreInfo1, moreInfo2));
			infoDataList.add(new InfoData(R.string.tut_1_e_text,
					R.drawable.tut_1_e, moreInfo1, moreInfo2));
			infoDataList.add(new InfoData(R.string.tut_1_f_text,
					R.drawable.tut_1_f, moreInfo1, moreInfo2));
		}
		return infoDataList;
	}
}