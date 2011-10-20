package com.webofthings.emf.utils;

public class InfoData
{
  private int textId;
  private int imageId;

  public InfoData(int textId, int imageId)
  {
    this.textId = textId;
    this.imageId = imageId;
  }

  public int getTextId()
  {
    return textId;
  }

  public void setTextId(int textId)
  {
    this.textId = textId;
  }

  public int getImageId()
  {
    return imageId;
  }

  public void setImageId(int imageId)
  {
    this.imageId = imageId;
  }
}