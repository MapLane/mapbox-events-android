package com.mapbox.services.android.core.location;

public enum LocationEnginePriority {
  NO_POWER(0),
  LOW_POWER(1),
  BALANCED_POWER_ACCURACY(2),
  HIGH_ACCURACY(3);

  public final int val;

  LocationEnginePriority(int val) {
    this.val = val;
  }
}
