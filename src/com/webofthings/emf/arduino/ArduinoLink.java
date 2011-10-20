/**
 * EMF-Uncovered 
 * Sep 30, 2011
 */
package com.webofthings.emf.arduino;

import java.io.IOException;

import org.microbridge.server.AbstractServerListener;
import org.microbridge.server.Client;
import org.microbridge.server.Server;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * This class is in charge of establishing the communication with an Arduino
 * board through the Microbridge library (using the Android Debug Bridge).
 * 
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 * 
 */
public class ArduinoLink
{
  private int port;
  private Handler callBack;
  private Server server;
  private boolean linkEstablished;

  public ArduinoLink(int port, Handler callBack)
  {
    this.port = port;
    this.callBack = callBack;
    this.linkEstablished = false;
  }

  public void startLink()
  {
    if (!this.linkEstablished) {
      try {
        this.server = new Server(this.port);
        this.server.start();
        this.linkEstablished = true;
      } catch (IOException e) {
        Log.e("ArduinoLink", 
          "Unable to start Microbridge TCP server link to the Arduino board.", 
          e);
      }
    }
    this.server.addListener(new AbstractServerListener()
    {
      public void onReceive(Client client, byte[] data)
      {
        int sensorValue = data[0] & 0xFF | (data[1] & 0xFF) << 8;
        callBack.sendMessage(createMsgForCallback(sensorValue));
      }
    });
  }

  protected Message createMsgForCallback(int value)
  {
    Message msg = new Message();
    msg.obj = Integer.toString(value);
    return msg;
  }

  public boolean isLinkEstablished()
  {
    return this.linkEstablished;
  }

  protected void setLinkEstablished(boolean linkEstablished)
  {
    this.linkEstablished = linkEstablished;
  }

  public void stopLink()
  {
    if (this.server.isRunning()) {
      this.server.stop();
    }
    this.linkEstablished = false;
  }

  protected Handler getCallBack()
  {
    return this.callBack;
  }

  public void sendToArduino(String value)
  {
    try
    {
      this.server.send(value.getBytes());
    } catch (IOException e) {
      Log.e("ArduinoLink", 
        "Unable to send a value to the Arduino board.", e);
    }
  }
}
