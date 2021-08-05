Mopub Mediation With Admob and Facebook
=====

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bumptech.glide/glide/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.bumptech.glide/glide) ![Build Status](https://travis-ci.org/bumptech/glide.svg?branch=master)

Download
--------
```gradle
repositories {
  google()
  mavenCentral()
}

dependencies {
implementation 'com.mopub.mediation:admob:20.0.0.0'
implementation 'com.google.android.gms:play-services-ads:20.2.0'
implementation 'androidx.annotation:annotation:1.2.0'
implementation 'com.mopub.mediation:facebookaudiencenetwork:6.3.0.0'
implementation 'com.facebook.android:audience-network-sdk:6.5.0'
implementation('com.mopub:mopub-sdk:+@aar') {
    transitive = true
}
 }
 
```

Create Directory in resource folder named "XML" and create xml file named "network_security_config.xml" in xml file and paste this code in it :
--------

```xml
<network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
    ...
</network-security-config>
```
Menifest
--------
``` 
1 ) Add this line in your "application" tag

android:networkSecurityConfig="@xml/network_security_config" 
Like This  
<application
        android:allowBackup="true"
        android:icon="@drawable/icon"
        android:label="@string/app_name"
        android:networkSecurityConfig="@xml/network_security_config"
        
2 ) Add these tags in menifest file also

   <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="@string/your_orignal_admob_ad_app_id" /> // 
            
<activity  android:name="com.mopub.common.MoPubBrowser"
            android:configChanges="keyboardHidden|orientation|screenSize" />

        <activity android:name="com.facebook.ads.AudienceNetworkActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:hardwareAccelerated="true" />

3 ) Add these permission in menifest as well

  <uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

```

Copy Mopub Test Ad ID's to Your String.xml file?
-------------------
 like this:

```
   // mopub test ad ids
    <string name="mop_ub_banner_test_id" translatable="false">b195f8dd8ded45fe847ad89ed1d016da</string>
    <string name="mop_ub_interstitial_test_id" translatable="false">24534e1901884e398f1253216226017e</string>
    <string name="mop_ub_native_test_id" translatable="false">11a17b188668469fb0412708c3d16813</string>

```

Status
------
Version 4 is now released and stable. Updates are released periodically with new features and bug fixes.

Comments/bugs/questions/pull requests are always welcome! Please read [CONTRIBUTING.md][5] on how to report issues.

Compatibility
-------------

 * **Minimum Android SDK**: Requires a minimum API level of 19.
 * **Compile Android SDK**: Requires you to compile against API 26 or later , ( Recommended Use Latest Versions )

Development
-----------
Follow the steps in the above sections to setup the project and then edit the files however you wish.
[Android Studio][26] 

To open the project in Android Studio:

1. Go to *File* menu or the *Welcome Screen*
2. Click on *Open...*
3. Navigate to MopubMediation root directory.


Getting Help
------------
To report a specific problem or feature request, [open a new issue on Github][5]. For questions, suggestions, or
anything else, email Contact.ShahzaibAli@gmail.com.


Thanks
------
* The **Aftab Baber** and **Mohsin Ali** 

Author
------
-  Muhammad Faizan 
[![Linkedin](https://img.shields.io/badge/-LinkedIn-black?style=for-the-badge&logo=Linkedin)](https://www.linkedin.com/in/muhammadfaizan786/) 
-  Shahzaib Ali
[![Linkedin](https://img.shields.io/badge/-LinkedIn-black?style=for-the-badge&logo=Linkedin)](https://www.linkedin.com/in/realshahzaibali/)


Disclaimer
---------
This is not an official Mopub/Admob/Facebook product.


