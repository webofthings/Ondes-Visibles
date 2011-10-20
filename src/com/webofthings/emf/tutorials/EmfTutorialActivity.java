package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

public class EmfTutorialActivity extends AbstractTutorialActivity {
	LinkedList<InfoData> infoDataList;

	protected LinkedList<InfoData> getInfoData() {
		if (infoDataList == null) {
			infoDataList = new LinkedList<InfoData>();
			infoDataList.add(new InfoData(R.string.tut_1_a_text,
					R.drawable.tut_1_a));
			infoDataList.add(new InfoData(R.string.tut_1_b_text,
					R.drawable.tut_1_b));
			infoDataList.add(new InfoData(R.string.tut_1_c_text,
					R.drawable.tut_1_c));
			infoDataList.add(new InfoData(R.string.tut_1_d_text,
					R.drawable.tut_1_d));
			infoDataList.add(new InfoData(R.string.tut_1_e_text,
					R.drawable.tut_1_e));
			infoDataList.add(new InfoData(R.string.tut_1_f_text,
					R.drawable.tut_1_f));
		}
		return infoDataList;
	}
}