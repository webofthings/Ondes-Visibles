/**
 * ondes-visibles 
 * Oct 28, 2011
 */
package com.webofthings.emf.utils;

/**
 * This class is used to convert values read from the Arduino analog input
 * into values with EMF related units.
 * Warning: these values are only valid if read by the sensors as described
 * in the Ondes Visibles! project (e.g., by an LT5534 for the HF field).
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 *
 */
public class EMFUnits {
	/* Maximum nominal RF input power for the LT5534 (HF sensor) */
	private static int DB_MAX = -2; 
	/* Minimal nominal RF input power for the LT5534 (HF sensor) */
	private static int DB_MIN = -60;   
	/* 1 read by the analog input of the Arduino corresponds to this value in volts */
	private static double ANALOG_INPUT_VOLTS = 0.004882813;
	

	/**
	 * This method converts the value read by the LT5534 sensor on the Arduino
	 * (through an Analog input) into a value in dBm.
	 * @param value read by the LT5534 sensor on the Arduino
	 * @return the dBm value
	 */
	public static double getdBValue(double value) {
		return 0 - ((double)(DB_MAX - DB_MIN) / (double) 1024 * value);
	}
	
	/**
	 * This method converts a value read by an analog input on the Arduino 
	 * into a voltage.
	 * @param value the value read by the analog input sensor
	 * @return the value in volts
	 */
	public static double getVoltsForAnalogIn(double value) {
		return value * ANALOG_INPUT_VOLTS;
	}
	
}
