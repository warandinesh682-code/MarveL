# MARVEL V1

Simple Android real-estate app with property listing/details, add property, customer details, customer list and call action.

## GitHub APK build
The included GitHub Actions workflow installs Gradle 8.9 directly, so a `gradlew` wrapper is NOT required. Open GitHub Actions and run **Build MARVEL APK** (or push to main). The generated debug APK is uploaded as an artifact named `MARVEL-debug-apk`.

## Project structure
- app/src/main/java/com/marvel/app/MainActivity.java
- app/src/main/AndroidManifest.xml
- app/src/main/res/values/styles.xml
- .github/workflows/build.yml
