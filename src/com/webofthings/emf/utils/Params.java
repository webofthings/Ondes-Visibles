package com.webofthings.emf.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.webofthings.emf.R;

public class Params
{
  public static final String LF_COMMAND = "l";
  public static final String HF_COMMAND = "h";
  public static boolean SIMULATION_MODE = false;
  private static final String FREQUENCY_KEY = "frequency";
  private static final String DEVICE_KEY = "deviceId";
  private static final String CONCLUSION_KEY = "isConclusion";
  private Bundle params;
  private Context ctx;
  private int numberOfLFDevices = 0;
  private int numberOfHFDevices = 0;

  public static Bundle prepareExtras(String frequency, int deviceId) {
    Bundle params = new Bundle();
    params.putString(FREQUENCY_KEY, frequency);
    params.putInt(DEVICE_KEY, deviceId);
    return params;
  }

  public static Bundle prepareExtras(String frequency, int deviceId, boolean isConclusion)
  {
    Bundle params = new Bundle();
    params.putString(FREQUENCY_KEY, frequency);
    params.putInt(DEVICE_KEY, deviceId);
    params.putBoolean(CONCLUSION_KEY, isConclusion);
    return params;
  }

  public Params(Bundle params)
  {
    this.params = params;
  }

  public Params(Intent intent, Context ctx)
  {
    this.ctx = ctx;
    params = intent.getExtras();
    numberOfHFDevices = ctx.getResources().getStringArray(R.array.hf_devices_array).length;
    numberOfLFDevices = ctx.getResources().getStringArray(R.array.lf_devices_array).length;
  }

  public boolean isLF()
  {
    return params.getString("frequency").equalsIgnoreCase("l");
  }

  public String getFrequency()
  {
    return params.getString("frequency");
  }

  public int getDeviceId()
  {
    int id = params.getInt("deviceId");
    if (isDevice(id)) {
      return id;
    }
    return -1;
  }
  
  public boolean isDevice() {
	  return isDevice(params.getInt("deviceId"));
  }

  private boolean isDevice(int id)
  { 
	if (id < 0) return false;
    if (isLF()) {
      if ((id+1) >= numberOfLFDevices) {
        return false;
      }
    }
    else if ((id+1) >= numberOfHFDevices) {
      return false;
    }
    return true;
  }

  public boolean isConclusion()
  {
    return params.getBoolean("isConclusion");
  }

  public static String getHumanReadableFrequency(Params param, Context ctx)
  {
    if (param.isLF()) {
      return ctx.getResources().getString(R.string.lf_human_readable);
    }
    return ctx.getResources().getString(R.string.hf_human_readable);
  }

  public String getHumanReadableFrequency()
  {
    if (isLF()) {
      return ctx.getResources().getString(R.string.lf_human_readable);
    }
    return ctx.getResources().getString(R.string.hf_human_readable);
  }
}