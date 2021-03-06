package com.mapbox.android.core.location;


import android.content.Context;

class LostLocationEngineFactory implements LocationEngineSupplier {

  private static final String LOST_LOCATION_SERVICES = "com.mapzen.android.lost.api.LocationServices";
  private final ClasspathChecker classpathChecker;

  LostLocationEngineFactory(ClasspathChecker classpathChecker) {
    this.classpathChecker = classpathChecker;
  }

  @Override
  public LocationEngine supply(Context context) {
    return LostLocationEngine.getLocationEngine(context);
  }

  @Override
  public boolean hasDependencyOnClasspath() {
    return classpathChecker.hasDependencyOnClasspath(LOST_LOCATION_SERVICES);
  }
}
