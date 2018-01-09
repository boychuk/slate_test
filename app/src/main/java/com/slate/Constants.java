package com.slate;

public class Constants {

    public static class Geometry {
        public static double MinLatitude = -90.0;
        public static double MaxLatitude = 90.0;
        public static double MinLongitude = -180.0;
        public static double MaxLongitude = 180.0;
        public static double MinRadius = 0.01; // kilometers
        public static double MaxRadius = 20.0; // kilometers
    }

    public final static String BROADCAST_ACTION = "BROADCAST_ACTION";
    public final static String BROADCAST_TASK_ENTER = "BROADCAST_TASK_ENTER";

    public static final String KEY_GEOFENCE_NAME = "KEY_GEOFENCE_NAME";
    public static final String KEY_GEOFENCE_RADIUS = "KEY_GEOFENCE_RADIUS";
    public static final String KEY_GEOFENCE_LAT = "GEOFENCE LATITUDE";
    public static final String KEY_GEOFENCE_LON = "GEOFENCE LONGITUDE";
    public static  final int REQ_PERMISSION = 999;

}


