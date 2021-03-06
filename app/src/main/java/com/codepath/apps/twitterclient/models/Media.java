package com.codepath.apps.twitterclient.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

// https://dev.twitter.com/overview/api/entities-in-twitter-objects#media
@Table(name = "Media")
@Parcel(analyze = {Media.class})
public class Media extends Model implements JSONSerializable {
  @Column(name = "MediaId")
  public long id;

  @Column(name = "MediaUrl")
  public String mediaUrl;

  @Column(name = "Url")
  public String url;

  @Column(name = "Type")
  public String type;

  @Column(name = "Width")
  public int width;

  @Column(name = "Height")
  public int height;

  @Column(name = "ResizeStr")
  public String resizeStr;

  public Media() {
    super();
  }

  @Override
  public void configureFromJSON(JSONObject jsonObject) throws JSONException {
    id = jsonObject.getLong("id");
    mediaUrl = jsonObject.getString("media_url");
    url = jsonObject.getString("url");

    JSONObject sizesObj = jsonObject.getJSONObject("sizes");
    if (sizesObj != null) {
      JSONObject smallObj = sizesObj.getJSONObject("large");
      if (smallObj != null) {
        width = smallObj.getInt("w");
        height = smallObj.getInt("h");
        resizeStr = smallObj.getString("resize");
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("id=").append(id).append("\n");
    str.append("url=").append(url).append("\n");
    str.append("mediaUrl=").append(mediaUrl).append("\n");
    str.append("width=").append(width).append("\n");
    str.append("height=").append(height).append("\n");
    str.append("resizeStr=").append(resizeStr);

    return str.toString();
  }
}
