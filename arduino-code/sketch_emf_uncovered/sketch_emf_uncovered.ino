#include <SPI.h>
#include <Adb.h>

#define samples 300
// Set to 1 to get debug messages in the Serial Monitor
#define debug 1
// Set to 0 if you do not have an Android device/bridge and want to use
// the Serial Monitor only
#define withAndroid 0

// Written by Dominique Guinard as part of the project "ondes-visibles"
// for cudrefin02.ch and webofthings.com
// info: http://code.google.com/p/ondes-visibles/

// Setting the sensor pins.
int lfAnalogIn = 0;
int hfAnalogIn = 5;

int noiseLf = 0;
// noise for the HF sensor, according to LT5534 specifications: +/- 0.2 Volts
int noiseHf = 40;
int currentNoise;

// Ack message to send to Android once a message has been received
// e.g., the sensor activation message.
boolean sense = false;

// Setting the output pins
int ledPin = 12;

// Used to store the pin # of the currently activated sensor (e.g., 5 for lf,
// 0 for hf)
int currentSensor;

// Output of the RF sensor in the absence of a clear signal 
// (1 unit = .0049 volts)
int vccOut = 100;

// Adb connection.
Connection * connection;

// Elapsed time for ADC sampling.
long lastTime;

// Event handler for the shell connection. 
void adbEventHandler(Connection * connection, adb_eventType event, uint16_t length, uint8_t * data)
{
  // We listen for incomming mode switches (BF / HF) 
  if (event == ADB_CONNECTION_RECEIVE)
  {
    if (debug) Serial.println(data[0]);
    if (data[0] == 'l')  {
        if (debug) Serial.println("Switching to LF!");
        currentSensor = lfAnalogIn;
        currentNoise = noiseLf;
        sense = true;
    }
    if (data[0] == 'h') {
        if (debug) Serial.println("Switching to HF!");
        currentSensor = hfAnalogIn;
        currentNoise = noiseHf;
        sense = true;
    }
    if (data[0] == 's') {
        if (debug) Serial.println("Stopping sensing data!");
        sense = false;
    }
  }
}

void setup()
{  
  // Initialise serial port
  Serial.begin(57600);

  currentSensor = lfAnalogIn;
  // To manually test the hf sensor, uncomment this:
  // currentSensor = hfAnalogIn;
  currentNoise = noiseLf;
  
  // Note start time
  lastTime = millis();  

  // Initialise the ADB subsystem.  
  if (withAndroid) ADB::init();

  // Open an ADB stream to the phone's shell. Auto-reconnect
  if (withAndroid) connection = ADB::addConnection("tcp:4568", true, adbEventHandler);

  // Activate the sensor in case we don't have an Android device to do it...
  if (!withAndroid) sense = true; 
  
}

// Given a value read from the analogRead, the function returns the voltage.
float getVoltageForValue(int value) {
  return value * 0.004882813;
}

int getAvgSensorValue(int sensorPin) {
   int val = 0;
   int array1[samples];
   unsigned long averaging = 0;
  
   // Averaging the value
   for(int i = 0; i < samples; i++){              
     array1[i] = analogRead(sensorPin);       
     averaging += array1[i];     
   }   
   val = averaging / samples; 
   return val;
   
}

int getHFSensorValue(int sensorPin) {
    // Value read from sensor
    int analogIn = 0;
    // Vcc signal strenght after a samples cycle    
    int analogValue = 0; 
    // RF signal strenght in dB    
    long dbValue = 0;
    // Loop counter
    int cnt = 0;
    long msInit = 0;
    long msStop = 0;
    int numSamples = 100;
    
    msInit = micros();
    
    for (cnt=0;cnt<numSamples;cnt++) {
      analogIn = analogRead(sensorPin);
      analogIn = 1024 - analogIn; 
      analogValue += analogIn;
    }
    
    msStop = micros();
    analogValue /= numSamples;  
    return analogValue;
}


// Gets the peak observation for a round of samples.
int getPeakSensorValue(int sensorPin) {
     int analogValue = 0;
     for(int i = 0; i < samples; i++){   
       int analogIn = analogRead(sensorPin);
       if (analogIn > analogValue) analogValue=analogIn;
       delayMicroseconds(100);
     }
     return analogValue;
   }

// Corrects the value according to the observed noise for each sensor
int cancelNoise(int value) {
    if(value - currentNoise >= 0) {
      return value - currentNoise;
    } else {
      return value;
    }
}

void loop()
{    
      if ((millis() - lastTime) > 100 && sense)
      {
        //Serial.println("In the loop!");
        //int data = analogRead(currentSensor);
        int value = getPeakSensorValue(currentSensor);
        value = cancelNoise(value);

        if (debug) Serial.println(getVoltageForValue(value));
        
        //connection->write(2, (uint8_t*)&value);
        if (debug) Serial.println(value);
        
        lastTime = millis();
      }
      // Poll the link to Android
      if (withAndroid) ADB::poll();
}
