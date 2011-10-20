package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

public class FrequenciesTutorialActivity extends AbstractTutorialActivity
{
  LinkedList<InfoData> infoDataList;

  protected LinkedList<InfoData> getInfoData()
  {
    if (infoDataList == null) {
      infoDataList = new LinkedList<InfoData>();
      infoDataList.add(
        new InfoData(R.string.tut_2_a_text, 
        		R.drawable.tut_2_a));
      infoDataList.add(
        new InfoData(R.string.tut_2_b_text, 
        		R.drawable.tut_2_b));
      infoDataList.add(
        new InfoData(R.string.tut_2_c_text, 
        		R.drawable.tut_2_c));
      infoDataList.add(
        new InfoData(R.string.tut_2_d_text, 
        		R.drawable.tut_2_d));
    }
    return infoDataList;
  }
}