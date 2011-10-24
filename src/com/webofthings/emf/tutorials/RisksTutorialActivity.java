package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

public class RisksTutorialActivity extends AbstractTutorialActivity
{
  LinkedList<InfoData> infoDataList;

  protected LinkedList<InfoData> getInfoData()
  {
    if (infoDataList == null) {
      infoDataList = new LinkedList<InfoData>();
      String moreInfo1 = getString(R.string.tut_3_more_info_1);
      String moreInfo2 = getString(R.string.tut_3_more_info_2);
      infoDataList.add(
        new InfoData(R.string.tut_3_a_text, 
        		R.drawable.tut_3_a, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_3_b_text, 
        		R.drawable.tut_3_b, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_3_c_text, 
        		R.drawable.tut_3_c, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_3_d_text, 
        		R.drawable.tut_3_d, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_3_e_text, 
        		R.drawable.tut_3_e, moreInfo1, moreInfo2));
    }
    return infoDataList;
  }
}