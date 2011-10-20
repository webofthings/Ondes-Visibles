/**
 * EMF-Uncovered 
 * Sep 30, 2011
 */
package com.webofthings.emf.arduino;

import java.util.Random;

import android.os.Handler;
import android.util.Log;

/**
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 *
 */

public class ArduinoLinkSimulator extends ArduinoEMFAppLink
  implements Runnable
{
  private Random random;
  private int maxSensorValue;
  private long sleepTime;
  private boolean isSensorActive;

  public ArduinoLinkSimulator(int port, Handler callBack, int maxSensorValue, int Hz)
  {
    super(port, callBack);
    this.random = new Random();
    this.maxSensorValue = maxSensorValue;
    this.sleepTime = (1000L / Hz);
    this.isSensorActive = false;
  }

  public void startLink()
  {
    super.setLinkEstablished(true);
    Thread thread = new Thread(this);
    thread.start();
  }

  public boolean isLinkEstablished()
  {
    return super.isLinkEstablished();
  }

  public void stopLink()
  {
    super.setLinkEstablished(false);
    this.isSensorActive = false;
  }

  public void run()
  {
    while (isLinkEstablished()) {
      if (this.isSensorActive)
        super.getCallBack().sendMessage(
          super.createMsgForCallback(this.random
          .nextInt(this.maxSensorValue)));
      try
      {
        Thread.sleep(this.sleepTime);
      } catch (InterruptedException e) {
        Log.e("Simulator", 
          "Exception while sleeping between to auto-generated values.", 
          e);
      }
    }
  }

  public void activateSensor(String frequencyType)
  {
    this.isSensorActive = true;
  }

  public void stopSensing() {
    this.isSensorActive = false;
  }
}
