# Backbase Mobile QA Assignment

## Technologies used in the project
Espresso
Firebase Test Labs

## Assumptions
I assume that,
- the search box is case insensitive (usability)
- users should be able to search using country code (For example NL)
- users should be able to search with `Contains` rather than `startswith`.
(Reason - Because users should be able to search using any text inside the city name)

## Run the project

### Pre-requirements
Make sure you have install fallowing to run the project:
- Java 11 or above
- Android Studio

### Dependency
- Add Espresso dependencies to build.gradle (module)
- Add Firebase Test Labs dependency to build.gradle (module)
- animationsDisabled property added to the testOptions in the build.gradle (module)

### Firebase Test Labs Configuration
- Create a firebase project (https://console.firebase.google.com/)
- Download the jason file and copy it to app folder in the project
- Go to Edit config in the android project and select ‘Android Instrumented Tests’
- Change the target to `Firebase Test Lab Devices Matrix`
- Sign in with google account
- Configure Matrix by creating a device
- Select the correct cloud project
- Run the project

<img width=600 src="./screenshots/create_firebase_project.png"/>
<img width=600 src="./screenshots/edit_configuration.png"/>
<img width=600 src="./screenshots/firebase_pass_report.png"/>
<img width=600 src="./screenshots/firebase_fail_report.png"/>
<img width=600 src="./screenshots/firebase_fail_video.png"/>

### Run the project in Local Machine
- Change the target to `User the device/snapshot drop down`

**Bonus Question:**
I fixed the map loading issue by adding a API Key ` android:value="AIzaSyA5lZ0zQETnglJmiGLKE17SMyl1Gmf4-dM"/>`
