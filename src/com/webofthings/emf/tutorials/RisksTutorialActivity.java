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
      infoDataList.add(
        new InfoData(R.string.tut_3_a_text, 
        		R.drawable.tut_3_a));
      infoDataList.add(
        new InfoData(R.string.tut_3_b_text, 
        		R.drawable.tut_3_b));
      infoDataList.add(
        new InfoData(R.string.tut_3_c_text, 
        		R.drawable.tut_3_c));
      infoDataList.add(
        new InfoData(R.string.tut_3_d_text, 
        		R.drawable.tut_3_d));
      infoDataList.add(
        new InfoData(R.string.tut_3_e_text, 
        		R.drawable.tut_3_e));
    }
    return infoDataList;
  }
}