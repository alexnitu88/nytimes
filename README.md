### Stack:
MVP
Dagger2
RxJava2
Retrofit


### Run lint:
```
gradlew.bat lint
```

### Run tests:
```
gradlew.bat clean connectedAndroidTest testDebugUnitTest
```

### Run tests with coverage:
```
gradlew.bat clean jacocoTestReport
```
coverage will be generated in nytimes\app\build\reports\jacoco

### Run coverage and upload to SonarQube:
```
gradlew.bat clean jacocoTestReport sonarqube -Dsonar.host.url=YOUR_SONAR_URL
```

### Build APK:
```
gradlew.bat assembleDebug
```

### Install APK:
```
adb install nytimes\app\build\outputs\apk\debug\app-debug.apk
```
