package com.webofthings.emf.tutorials;

import java.util.LinkedList;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webofthings.emf.R;
import com.webofthings.emf.utils.InfoData;

public abstract class AbstractTutorialActivity extends Activity
{
  private ListIterator<InfoData> dataIterator;

  protected abstract LinkedList<InfoData> getInfoData();

  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.info);

    dataIterator = getInfoData().listIterator();

    showNextInfo();

    ImageButton nextBtn = (ImageButton)findViewById(R.id.next_button);
    nextBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        showNextInfo();
      }
    });
    ImageButton prevBtn = (ImageButton)findViewById(R.id.previous_button);
    prevBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        showPreviousInfo();
      }
    });
    ImageButton homeBtn = (ImageButton)findViewById(R.id.home_button);
    homeBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        startActivity(new Intent("com.webofthings.emf.TUTORIALPICKERACTIVITY"));
      }
    });
  }

  protected void showNextInfo()
  {
    InfoData currentInfo;
    if (dataIterator.hasNext()) {
      currentInfo = (InfoData)dataIterator.next();
    } else {
      dataIterator = getInfoData().listIterator();
      currentInfo = (InfoData)dataIterator.next();
    }

    doShowInfo(currentInfo);
  }

  protected void showPreviousInfo()
  {
    InfoData currentInfo;
    if (dataIterator.hasPrevious()) {
      currentInfo = (InfoData)dataIterator.previous();
    } else {
      currentInfo = (InfoData)getInfoData().getLast();
      dataIterator = getInfoData().listIterator(getInfoData().size() - 1);
    }

    doShowInfo(currentInfo);
  }

  protected void doShowInfo(InfoData currentInfo) {
    TextView bodyText = (TextView)
      findViewById(R.id.info_body);
    bodyText.setText(getString(currentInfo.getTextId()));

    ImageView image = (ImageView)findViewById(R.id.info_image);
    image.setImageDrawable(getResources().getDrawable(currentInfo.getImageId()));
  }

  protected LinearLayout getButtonsView() {
    return (LinearLayout)findViewById(R.id.linear_layout_buttons);
  }
}