/**
 * ondes-visibles 
 * Oct 26, 2011
 */
package com.webofthings.emf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.webofthings.emf.utils.EMFUnits;

import android.os.Environment;
import android.util.Log;

/**
 * This class is used to store measurements in a file on the SD card.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 * 
 */
public class DataPersistantLogger {
	private static final String TUPLE_SEPARATOR = "\n";
	private String VALUE_SEPARATOR = " ";
	private File root;
	private File file;

	

	public DataPersistantLogger() {
		Calendar calendar = Calendar.getInstance();
		root = Environment.getExternalStorageDirectory();
		file = new File(root,  "OndesVisiblesLog_" + calendar.getTimeInMillis() + ".txt");
	}

	/**
	 * This method stores a value in the datalog on the SD card.
	 * @param value
	 */
	public void storeData(double value) {
			try {
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(fileWriter);
				out.write(Long.toString(Calendar.getInstance().getTimeInMillis()) + " ");
				out.write(value + VALUE_SEPARATOR);
				out.close();
				fileWriter.close();
			} catch (IOException e) {
				Log.e("Error", "Unable to store measurement. " +
						"Do you have access rights? " + e.getMessage());
			}
	}
	
	/**
	 * This method converts the analog input value for the HF sensor 
	 * into relevant EMF values and stores it in the datalog on the SD card.
	 * It stores the data tuples as:
	 * [Timestamp] [dBm] [volt] [rawValue]
	 * @param value a value as read by the analog input of the Arduino
	 */
	public void storeHFDataDetailed(double value) {
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write(Long.toString(Calendar.getInstance().getTimeInMillis()));
			out.write(VALUE_SEPARATOR);
			out.write(Double.toString(EMFUnits.getdBValue(value)));
			out.write(VALUE_SEPARATOR);
			out.write(Double.toString(EMFUnits.getVoltsForAnalogIn(value)));
			out.write(VALUE_SEPARATOR);
			out.write(Double.toString(value));
			out.write(TUPLE_SEPARATOR);
			out.close();
			fileWriter.close();
		} catch (IOException e) {
			Log.e("Error", "Unable to store measurement. " +
					"Do you have access rights? " + e.getMessage());
		}
	}
	
	/**
	 * This method converts the analog input value for the LF sensor 
	 * into relevant EMF values and stores it in the datalog on the SD card.
	 * It stores the data tuples as:
	 * [Timestamp] [volt] [rawValue]
	 * @param value a value as read by the analog input of the Arduino
	 */
	public void storeLFDataDetailed(double value) {
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write(Long.toString(Calendar.getInstance().getTimeInMillis()));
			out.write(VALUE_SEPARATOR);
			out.write(Double.toString(EMFUnits.getVoltsForAnalogIn(value)));
			out.write(VALUE_SEPARATOR);
			out.write(Double.toString(value));
			out.write(TUPLE_SEPARATOR);
			out.close();
			fileWriter.close();
		} catch (IOException e) {
			Log.e("Error", "Unable to store measurement. " +
					"Do you have access rights? " + e.getMessage());
		}
	}


}
