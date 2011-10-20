package com.webofthings.emf.tutorials;

import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

public class PreventionTutorialActivity extends AbstractTutorialActivity
{
  LinkedList<InfoData> infoDataList;

  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    ImageButton expBtn = new ImageButton(this);
    expBtn.setImageDrawable(getResources().getDrawable(R.drawable.exp_small));
    expBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        startActivity(new Intent("com.webofthings.emf.FIELDSELECTIONACTIVITY"));
      }
    });
    super.getButtonsView().addView(expBtn);
  }

  protected LinkedList<InfoData> getInfoData()
  {
    if (infoDataList == null) {
      infoDataList = new LinkedList<InfoData>();
      infoDataList.add(
        new InfoData(R.string.tut_4_a_text, 
        		R.drawable.tut_4_a));
      infoDataList.add(
        new InfoData(R.string.tut_4_b_text, 
        		R.drawable.tut_4_b));
      infoDataList.add(
        new InfoData(R.string.tut_4_c_text, 
        		R.drawable.tut_4_c));
      infoDataList.add(
        new InfoData(R.string.tut_4_d_text, 
        		R.drawable.tut_4_d));
      infoDataList.add(
        new InfoData(R.string.tut_4_e_text, 
        		R.drawable.tut_4_e));
    }
    return infoDataList;
  }
}