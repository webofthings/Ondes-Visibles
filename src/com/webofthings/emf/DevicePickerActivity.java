package com.webofthings.emf;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.webofthings.emf.utils.Params;

/**
 * This Activity is used to pick a device to experiment with.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class DevicePickerActivity extends ListActivity
{
  private Params params;

  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.field_selection);
  }

  protected void onResume()
  {
    super.onResume();
    params = new Params(getIntent(), this);
    String[] devices;
    if (params.isLF())
      devices = getResources().getStringArray(R.array.lf_devices_array);
    else {
      devices = getResources().getStringArray(R.array.hf_devices_array);
    }
    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, devices));
  }

  public void onListItemClick(ListView parent, View v, int position, long id)
  {
    Intent intent = new Intent(this, ChartActivity.class);
    intent.putExtras(Params.prepareExtras(params.getFrequency(), position));
    startActivity(intent);
  }
}