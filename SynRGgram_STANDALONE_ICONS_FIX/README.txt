SynRGgram standalone icon fix

Upload the TMessagesProj_AppStandalone folder into the repository root and replace existing files.
This package targets the resources used by AndroidManifest_standalone.xml:
- ic_launcher_sa: downloaded APK and main icon
- icon_2_launcher_sa through icon_6_launcher_sa: icon choices in Chat Settings
- mipmap-anydpi-v26 XML: adaptive icons on Android 8+

The main AndroidManifest.xml does not need to be changed.
