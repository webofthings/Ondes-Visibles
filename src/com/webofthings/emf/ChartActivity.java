package com.webofthings.emf;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.webofthings.emf.arduino.ArduinoEMFAppLink;
import com.webofthings.emf.arduino.ArduinoLinkSimulator;
import com.webofthings.emf.utils.Params;

public class ChartActivity extends Activity
{
  private static final double INITIAL_X_MAX = 50.0D;
  private static final double INITIAL_Y_MAX = 40.0D;
  private static final int DATA_SET_MAX_SIZE = 500;
  
  //private static final int NOISE = 40;
  private GraphicalView mChartView;
  private ArduinoEMFAppLink arduino;
  private Handler callBack;
  private XYSeries series;
  private double xTick = 0.0D;
  private double lastMinX = 0.0D;
  private XYMultipleSeriesRenderer renderer;
  private Params params;
  private int lineColor;
  private int surfaceColor;
  private Vibrator vibrator;
  private MenuItem startStopMnu;
  private MenuItem vibrateMnu;
  private boolean sensingActive;
  private boolean vibratorActive;

  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.chart);

    vibrator = (Vibrator) getSystemService("vibrator");
    vibratorActive = true;

    params = new Params(getIntent(), this);
    showIntroduction();
  }

  protected void onResume()
  {
    super.onResume();
    sensingActive = false;

    params = new Params(getIntent(), this);

    if (params.isLF()) {
      lineColor = Color.parseColor("#f9b200");
      surfaceColor = Color.parseColor("#b5123c");
    } else {
      lineColor = Color.parseColor("#b5123c");
      surfaceColor = Color.parseColor("#f9b200");
    }

    if (mChartView == null) {
      LinearLayout layout = (LinearLayout)findViewById(R.id.chartLayout);
      mChartView = ChartFactory.getLineChartView(this, getDataSet(), 
        getRenderer());
      layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    } else {
      mChartView.repaint();
    }

    callBack = new Handler()
    {
      public void handleMessage(Message msg) {
        double y = Double.parseDouble((String)msg.obj);
        if (mChartView != null) {
          ChartActivity.this.refreshChart(y);
          ChartActivity.this.hapticFeedback(y);
        }
      }
    };
    if (Params.SIMULATION_MODE)
      arduino = new ArduinoLinkSimulator(4568, callBack, 20, 2);
    else {
      arduino = new ArduinoEMFAppLink(4568, callBack);
    }
    arduino.startLink();
  }

  private void hapticFeedback(double value)
  {
    if (vibratorActive)
      vibrator.vibrate((long)value * 10);
  }

  protected void onPause()
  {
    super.onPause();
    stopSensing();
    arduino.stopLink();
  }

  protected void onStop()
  {
    super.onStop();
    stopSensing();
    arduino.stopLink();
  }

  private void refreshChart(double lastValue)
  {
    if (series.getItemCount() > DATA_SET_MAX_SIZE) {
      series.clear();
      getRenderer().setXAxisMax(INITIAL_X_MAX);
      getRenderer().setXAxisMin(0.0D);
      lastMinX = 0.0D;
      xTick = 0.0D;
    }

    if (xTick > getRenderer().getXAxisMax()) {
      getRenderer().setXAxisMax(xTick);
      getRenderer().setXAxisMin(++lastMinX);
    }

    if (lastValue > getRenderer().getYAxisMax()) {
      getRenderer().setYAxisMax(lastValue);
    }
    series.add(xTick++, lastValue);

    mChartView.repaint();
  }

  private XYMultipleSeriesDataset getDataSet() {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    series = new XYSeries(getResources().getString(R.string.field));
    dataset.addSeries(series);
    return dataset;
  }

  private XYMultipleSeriesRenderer getRenderer() {
    if (renderer == null) {
      renderer = new XYMultipleSeriesRenderer();
      renderer.setAxisTitleTextSize(16);
      renderer.setChartTitleTextSize(20);
      renderer.setLabelsTextSize(15);
      renderer.setLegendTextSize(15);
      renderer.setPointSize(2);
      renderer.setMargins(new int[] { 10, 15, 10, 0 });
      renderer.setAxesColor(Color.DKGRAY);
      renderer.setLabelsColor(Color.LTGRAY);
      renderer.setXAxisMin(0);
      renderer.setXAxisMax(INITIAL_X_MAX);
      renderer.setYAxisMin(0);
      renderer.setYAxisMax(INITIAL_Y_MAX);
      renderer.setPanEnabled(true, true);

      XYSeriesRenderer r = new XYSeriesRenderer();
      r.setPointStyle(PointStyle.CIRCLE);
      r.setColor(lineColor);
      r.setFillPoints(true);
      r.setFillBelowLine(true);
      r.setFillBelowLineColor(surfaceColor);

      renderer.addSeriesRenderer(r);
    }
    return renderer;
  }

  public boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    MenuItem showExpMnu = menu.add(0, 0, 0, 
      getString(R.string.show_experiment));
    showExpMnu.setIcon(R.drawable.exp_small);

    MenuItem showExpConclMnu = menu.add(0, 1, 0, 
      getString(R.string.show_experiment_conclusion));
    showExpConclMnu.setIcon(R.drawable.conclusion);

    startStopMnu = menu.add(0, 2, 0, getString(R.string.start_experiment));
    startStopMnu.setIcon(R.drawable.record);

    vibrateMnu = menu.add(0, 3, 0, getString(R.string.vibrator));
    vibrateMnu.setIcon(R.drawable.vibrator_off);

    MenuItem homeMnu = menu.add(0, 4, 0, getString(R.string.home));
    homeMnu.setIcon(R.drawable.home);
    return true;
  }

  private void showIntroduction() {
    Intent intent1 = new Intent(this, ExperimentActivity.class);
    intent1.putExtras(Params.prepareExtras(params.getFrequency(), 
      params.getDeviceId(), false));
    startActivity(intent1);
  }

  private void showConclusion() {
    Intent intent = new Intent(this, ExperimentActivity.class);
    intent.putExtras(Params.prepareExtras(params.getFrequency(), 
      params.getDeviceId(), true));
    startActivity(intent);
  }

  private void startSensing()
  {
    arduino.activateSensor(params.getFrequency());
    sensingActive = true;

    if (startStopMnu != null) {
      startStopMnu.setIcon(R.drawable.stop);
      startStopMnu.setTitle(getString(R.string.stop_experiment));
    }
  }

  private void stopSensing()
  {
    arduino.stopSensing();
    sensingActive = false;

    if (startStopMnu != null) {
      startStopMnu.setIcon(R.drawable.record);
      startStopMnu.setTitle(getString(R.string.start_experiment));
    }
  }

  private void toggleSensing() {
    if (sensingActive)
      stopSensing();
    else
      startSensing();
  }

  private void toggleVibrator()
  {
    if (vibratorActive)
    {
      vibrateMnu.setIcon(R.drawable.vibrator_on);
      vibratorActive = false;
    }
    else {
        vibrateMnu.setIcon(R.drawable.vibrator_off);
      vibratorActive = true;
    }
  }

  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId()) {
    case 0:
      showIntroduction();
      return true;
    case 1:
      showConclusion();
      return true;
    case 2:
      toggleSensing();
      return true;
    case 3:
      toggleVibrator();
      return true;
    case 4:
      startActivity(new Intent(this, MainActivity.class));
      return true;
    }
    return false;
  }
}