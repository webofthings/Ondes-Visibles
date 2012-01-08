package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

/**
 * This is the tutorial about simple means to prevent side-effects from exposure.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class PreventionTutorialActivity extends AbstractTutorialActivity
{
  LinkedList<InfoData> infoDataList;

  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    ImageButton expBtn = (ImageButton)findViewById(R.id.home_button);
    expBtn.setImageDrawable(getResources().getDrawable(R.drawable.exp_small));
    expBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        startActivity(new Intent("com.webofthings.emf.FIELDSELECTIONACTIVITY"));
      }
    });
  }

  protected LinkedList<InfoData> getInfoData()
  {
    if (infoDataList == null) {
      infoDataList = new LinkedList<InfoData>();
      String moreInfo1 = getString(R.string.tut_4_more_info_1);
      String moreInfo2 = getString(R.string.tut_4_more_info_2);
      infoDataList.add(
        new InfoData(R.string.tut_4_a_text, 
        		R.drawable.tut_4_a, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_4_b_text, 
        		R.drawable.tut_4_b, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_4_c_text, 
        		R.drawable.tut_4_c, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_4_d_text, 
        		R.drawable.tut_4_d, moreInfo1, moreInfo2));
      infoDataList.add(
        new InfoData(R.string.tut_4_e_text, 
        		R.drawable.tut_4_e, moreInfo1, moreInfo2));
    }
    return infoDataList;
  }
}