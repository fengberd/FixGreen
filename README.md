# FixGreen
A xposed module to fix old android(lower than 7.0) image compressing bug.

# Usage
1. Make sure your android version is lower than 7.0,[image compressing bug is fixed in Android N](https://github.com/google/skia/commit/c7d01d3e1d3621907c27b283fb7f8b6e177c629d) so there's no need to use this module.
1. Download and install [Xposed Installer](http://repo.xposed.info/module/de.robv.android.xposed.installer) and install the framework.
2. Install the module,enable it in xposed installer and reboot your device
3. Make sure you see 2 green lines when you open this app.

# Compile
If you want to compile it by yourself,please make sure your XposedBridge.jar is "Provided" instanceof "Compile" in Android Studio settings.I wrote "provide" in build.grade and put the jar into libs so maybe there's no need to care about that.
