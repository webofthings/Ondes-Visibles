package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

/**
 * This is the tutorial about the basics frequencies in EMFs.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class FrequenciesTutorialActivity extends AbstractTutorialActivity
{
  LinkedList<InfoData> infoDataList;

  protected LinkedList<InfoData> getInfoData()
  {
    if (infoDataList == null) {
      infoDataList = new LinkedList<InfoData>();
      String moreInfo1 = getString(R.string.tut_2_more_info_1);
      String moreInfo2 = getString(R.string.tut_2_more_info_2);
      infoDataList.add(
        new InfoData(R.string.tut_2_a_text, 
        		R.drawable.tut_2_a, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_2_b_text, 
        		R.drawable.tut_2_b, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_2_c_text, 
        		R.drawable.tut_2_c, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_2_d_text, 
        		R.drawable.tut_2_d, moreInfo1, moreInfo2));
    }
    return infoDataList;
  }
}