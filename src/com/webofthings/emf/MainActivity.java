package com.webofthings.emf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.webofthings.emf.utils.Params;

public class MainActivity extends Activity
{
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ImageButton introBtn = (ImageButton)findViewById(R.id.button_intro);
    introBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        startActivity(new Intent("com.webofthings.emf.TUTORIALPICKERACTIVITY"));
      }
    });
    ImageButton expBtn = (ImageButton)findViewById(R.id.button_experiments);
    expBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        startActivity(new Intent("com.webofthings.emf.FIELDSELECTIONACTIVITY"));
      }
    });
    ImageButton settingsBtn = (ImageButton)findViewById(R.id.button_settings);
    settingsBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        MainActivity.this.invokeSettings();
      }
    });
    ImageButton aboutBtn = (ImageButton)findViewById(R.id.button_about);
    aboutBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        MainActivity.this.showAbout();
      } } );
  }

  private void showAbout() {
	  startActivity(new Intent(this, AboutActivity.class));
  }

  private void invokeSettings() {
    Toast.makeText(this, getResources().getString(R.string.lang_warning), 0).show();
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
    startActivity(intent);
  }

  public boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    MenuItem debug = menu.add(0, 0, 0, getString(R.string.toggle_debug));
    debug.setIcon(R.drawable.white_flag);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId()) {
    case 0:
      if (Params.SIMULATION_MODE) {
        Params.SIMULATION_MODE = false;
        Toast.makeText(this, getString(R.string.simulation_off), Toast.LENGTH_LONG).show();
      } else {
        Params.SIMULATION_MODE = true;
        Toast.makeText(this, getString(R.string.simulation_on), Toast.LENGTH_LONG).show();
      }

      return true;
    }
    return false;
  }
}