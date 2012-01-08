package com.webofthings.emf.utils;

public class InfoData
{
  private int textId;
  private int imageId;
  private String moreInfo[];

  /**
   * This is a bean class modelling information data.
   * @author <a href="http://www.guinard.org">Dominique Guinard</a>
   */
  public InfoData(int textId, int imageId, String moreInfo1, String moreInfo2)
  {
    this.textId = textId;
    this.imageId = imageId;
    moreInfo = new String[2];
    moreInfo[0] = moreInfo1;
    moreInfo[1] = moreInfo2;
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
  
  public String[] getMoreInfo() {
	  return this.moreInfo;
  }
  
  public void setMoreInfo1(String moreInfo1, String moreInfo2) {
	  moreInfo[0] = moreInfo1;
	  moreInfo[1] = moreInfo2;
  }
}