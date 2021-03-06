package com.mapbox.android.telemetry;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class MapLoadEvent extends Event implements Parcelable {
  private static final String MAP_LOAD = "map.load";
  private static final String OPERATING_SYSTEM = "Android - " + Build.VERSION.RELEASE;

  @SerializedName("event")
  private final String event;
  @SerializedName("created")
  private String created;
  @SerializedName("userId")
  private String userId;
  @SerializedName("model")
  private String model = null;
  @SerializedName("operatingSystem")
  private String operatingSystem = null;
  @SerializedName("resolution")
  private Float resolution = null;
  @SerializedName("accessibilityFontScale")
  private Float accessibilityFontScale = null;
  @SerializedName("orientation")
  private String orientation = null;
  @SerializedName("batteryLevel")
  private Integer batteryLevel = null;
  @SerializedName("pluggedIn")
  private Boolean pluggedIn = null;
  @SerializedName("carrier")
  private String carrier = null;
  @SerializedName("cellularNetworkType")
  private String cellularNetworkType = null;
  @SerializedName("wifi")
  private Boolean wifi = null;

  MapLoadEvent() {
    this.event = MAP_LOAD;
    this.model = Build.MODEL;
    this.operatingSystem = OPERATING_SYSTEM;
    this.created = TelemetryUtils.obtainCurrentDate();
  }

  @Override
  Type obtainType() {
    return Type.MAP_LOAD;
  }

  void setUserId(String userId) {
    this.userId = userId;
  }

  void setResolution(float resolution) {
    this.resolution = resolution;
  }

  void setAccessibilityFontScale(float accessibilityFontScale) {
    this.accessibilityFontScale = accessibilityFontScale;
  }

  void setOrientation(String orientation) {
    this.orientation = orientation;
  }

  void setBatteryLevel(int batteryLevel) {
    this.batteryLevel = batteryLevel;
  }

  void setPluggedIn(boolean pluggedIn) {
    this.pluggedIn = pluggedIn;
  }

  void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  void setCellularNetworkType(String cellularNetworkType) {
    this.cellularNetworkType = cellularNetworkType;
  }

  void setWifi(boolean wifi) {
    this.wifi = wifi;
  }

  private MapLoadEvent(Parcel in) {
    event = in.readString();
    created = in.readString();
    userId = in.readString();
    model = in.readString();
    operatingSystem = in.readString();
    resolution = in.readByte() == 0x00 ? null : in.readFloat();
    accessibilityFontScale = in.readByte() == 0x00 ? null : in.readFloat();
    orientation = in.readString();
    batteryLevel = in.readByte() == 0x00 ? null : in.readInt();
    byte pluggedInVal = in.readByte();
    pluggedIn = pluggedInVal == 0x02 ? null : pluggedInVal != 0x00;
    carrier = in.readString();
    cellularNetworkType = in.readString();
    byte wifiVal = in.readByte();
    wifi = wifiVal == 0x02 ? null : wifiVal != 0x00;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(event);
    dest.writeString(created);
    dest.writeString(userId);
    dest.writeString(model);
    dest.writeString(operatingSystem);
    if (resolution == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeFloat(resolution);
    }
    if (accessibilityFontScale == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeFloat(accessibilityFontScale);
    }
    dest.writeString(orientation);
    if (batteryLevel == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(batteryLevel);
    }
    if (pluggedIn == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (pluggedIn ? 0x01 : 0x00));
    }
    dest.writeString(carrier);
    dest.writeString(cellularNetworkType);
    if (wifi == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (wifi ? 0x01 : 0x00));
    }
  }

  @SuppressWarnings("unused")
  public static final Creator<MapLoadEvent> CREATOR = new Creator<MapLoadEvent>() {
    @Override
    public MapLoadEvent createFromParcel(Parcel in) {
      return new MapLoadEvent(in);
    }

    @Override
    public MapLoadEvent[] newArray(int size) {
      return new MapLoadEvent[size];
    }
  };
}
