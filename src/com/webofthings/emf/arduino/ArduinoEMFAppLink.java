/**
 * EMF-Uncovered 
 * Oct 6, 2011
 */
package com.webofthings.emf.arduino;

import android.os.Handler;

/**
 * This class represents the communication hub between the Android and the 
 * Arduino EMF Uncovered application.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 *
 */
public class ArduinoEMFAppLink extends ArduinoLink {
	private final String STOP_SENSING_CMD = "s";

	/**
	 * @param port
	 * @param callBack
	 */
	public ArduinoEMFAppLink(int port, Handler callBack) {
		super(port, callBack);
	}


public void activateSensor(String frequencyType)
  {
    super.sendToArduino(frequencyType);
    super.sendToArduino(frequencyType);
    super.sendToArduino(frequencyType);
    super.sendToArduino(frequencyType);
  }

  public void stopSensing() {
    super.sendToArduino(STOP_SENSING_CMD);
    super.sendToArduino(STOP_SENSING_CMD);
    super.sendToArduino(STOP_SENSING_CMD);
    super.sendToArduino(STOP_SENSING_CMD);
  }
		
}
