Slate Task: Creating and Monitoring Geofence
=================================

The goal of this assignment is to create an Android application that will detect if the device is located inside of a geofence area.

Geofence area is defined as a combination of some geographic point, radius, and specific Wifi network name. A device is considered to be inside of the geofence area if the device is connected to the specified WiFi network or remains geographically inside the defined circle.

Note that if device coordinates are reported outside of the zone, but the device still connected to the specific Wifi network, then the device is treated as being inside the geofence area.

Application activity should provide controls to configure the geofence area and display current status: inside OR outside.

Once you have completed this task, host your source code on GitHub and a README file in the root with instructions. Keep in mind we prefer some commits history in the repo vs single commit. Tests are welcome and encouraged.


Introduction
============

Geofencing combines awareness of the user's current location with awareness of
nearby features, defined as the user's proximity to locations that may be of
interest. To mark a location of interest, you specify its latitude and
longitude. To adjust the proximity for the location, you add a radius. The
latitude, longitude, and radius define a geofence.  You can add Wi-Fi SSID for detect connection.

Location Services treats a geofences as an area rather than as a points and
proximity. This allows it to detect when the user enters or exits a geofence.
For each geofence, you can ask Location Services to send you entrance events,
exit events, or both. You can also limit the duration of a geofence by
specifying an expiration duration in milliseconds. After the geofence expires,
Location Services automatically removes it.

To run this sample, **location must be enabled**.

Prerequisites
--------------

- Android API Level >v16
- Google Support Repository

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.
