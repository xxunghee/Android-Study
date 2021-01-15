# 화면 회전(Screen Orientation)

- 특징

  - 화면 회전은 단말 방향에 따라 변화하게 할 수도 있고, 세로 또는 가로 방향으로 고정시킬 수도 있음

  - 화면이 회전될 때, 원래 화면이 Destroy 되고 새 화면이 Create 되며 동작함

    

- 구현(단말 방향에 따라)

  1. app/src/main/res 디렉토리에 layout-land 디렉토리 생성
  2. layout 디렉토리의 activity_main.xml 파일을 layout-land 안에 복사하여 붙여넣기

  

- 구현(가로 또는 세로 고정)

  1. 앱 화면 고정

     AndroidManifest.xml에 screenOrientation속성을 portrait 또는 landscape로 지정

     ```xml
     <activity
         android:name=".MainActivity"
         android:label="@string/app_name"
         android:theme="@style/AppTheme.NoActionBar"
         android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
         android:screenOrientation="portrait">
         <intent-filter>
             <action android:name="android.intent.action.MAIN" />
             <category android:name="android.intent.category.LAUNCHER" />
         </intent-filter>
     </activity>
     ```

  2. 앱 내에서 고정(using API)

     onCreate 메소드 안에서 setRequestedOrientation 메소드의 인자로 방향 지정

     ```java
     this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // 세로
     this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 가로
     ```

     화면 고정을 해제하고 단말 방향 감지 센서를 활성화하려면 다음과 같이 속성 정의

     ```java
     this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);  // 단말 방향
     ```

     