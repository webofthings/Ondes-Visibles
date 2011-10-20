package com.webofthings.emf;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.webofthings.emf.tutorials.EmfTutorialActivity;
import com.webofthings.emf.tutorials.FrequenciesTutorialActivity;
import com.webofthings.emf.tutorials.PreventionTutorialActivity;
import com.webofthings.emf.tutorials.RisksTutorialActivity;

public class TutorialPickerActivity extends ListActivity
{
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.field_selection);
  }

  protected void onResume()
  {
    super.onResume();
    setListAdapter(
      new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tutorials)));
  }

  public void onListItemClick(ListView parent, View v, int position, long id) {
    switch (position) {
    case 0:
      startActivity(new Intent(this, EmfTutorialActivity.class));
      break;
    case 1:
      startActivity(new Intent(this, FrequenciesTutorialActivity.class));
      break;
    case 2:
      startActivity(new Intent(this, RisksTutorialActivity.class));
      break;
    case 3:
      startActivity(new Intent(this, PreventionTutorialActivity.class));
      break;
    default:
      startActivity(new Intent(this, EmfTutorialActivity.class));
    }
  }
}