# 인텐트(Intent)

### 인텐트

- 정의
  - 어플리케이션의 구성 요소 간 데이터 전달을 가능하게 하는 메시징 객체



- 사용

  - Activity 시작

    Activity의 새 인스턴스를 시작하려면 Intent를 `startActivity()`의 인자로 전달

    Activity 띄울 때 응답이 필요한 경우에는 Intent를 `startActivityForResult()`의 인자로 전달

    Intent는 시작할 Activity에 대한 설명과 필수 데이터를 담고 있음

    

  - Service 시작

    Service를 시작하려면 Intent를 `startService()`의 인자로 전달

    Intent는 시작할 Service에 대한 설명과 필수 데이터를 담고 있음

    

  - Broadcast 전달

    모든 어플리케이션이 수신할 수 있는 메시지인 Broadcast를 전달하려면 Intent를 `sendBroadcast()` 또는 `sendOrderedBroadcast()`의 인자로 전달

    

- 유형

  - 명시적 인텐트

    - Intent를 충족하는 어플리케이션을 대상 앱의 패키지 이름 또는 클래스 이름으로 제공

    - 앱 안에서 구성 요소를 시작할 때 사용

    - 예시

      ``` java
      Intent downloadIntent = new Intent(this, DownloadService.class);
      downloadIntent.setData(Uri.parse(fileUrl));
      startService(downloadIntent);
      ```

      

  - 암시적 인텐트

    - 특정 구성 요소의 이름을 대지 않지만 일반적인 작업을 선언해 다른 앱의 구성 요소가 이를 처리할 수 있도록 함

    - 안드로이드 시스템에서 기기의 다른 앱의 Manifest 파일에서 선언된 인텐트 필터(IntentFilter: 해당 구성 요소가 수신하고자 하는 Intent 유형)와 비교하고, 일치하는 인텐트 필터가 있으면 실행하고 Intent 객체 전달

    - 예시

      ```java
      Intent sendIntent = new Intent();
      sendIntent.setAction(Intent.ACTION_SEND);
      sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
      sendIntent.setType("text/plain");
      
      if (sendIntent.resolveActivity(getPackageManager()) != null) {
          startActivity(sendIntent);
      }
      ```

      

- 구현

  - 새로 만드는 경우

    - `onCreate()` 메소드에서 `getIntent()` 사용

      

  - 액티비티를 재사용하는 경우

    - `onNewIntent()` 메소드 사용

      

  - 이전 화면으로 돌아갈 때

    - 새 화면에서 `finish()` 사용

    - `finish()` 전에 `setResult()`에 데이터를 넣으면 Intent에 데이터 넣어서 보낼 수 있음

      

- 부가데이터

  - Flag를 통한 전달

    - Intent의 메타데이터와 같은 역할

    - 안드로이드 시스템에 Activity 시작 방법에 대한 지침이나 시작 후 처리 방법에 대한 지침 전달 가능

    - 사용

      ```java
      // 스택의 맨 위에서 실행 중인 경우 시작하지 않음
      intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
      
      // 새 활동이 히스토리 스택에 유지되지 않음
      intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
      
      // 시작되는 활동이 이미 실행 중이면 해당 활동의 새 인스턴스를 실행하고, 다른 모든 활동 중지
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      
      // 여러 플래그를 전달하는 경우 '|'로 구분하여 전달
      intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
      ```

  - Bundle을 통한 전달

    - Intent 안의 Bundle에 부가데이터를 넣고 뺄 수 있음

      

### 인텐트 필터

- AndroidManifest.xml 파일의 Activity에`<intent-filter>` 태그로 추가

  ```xml
  <activity android:name="MainActivity">
      <!-- This activity is the main entry, should appear in app launcher -->
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
          <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
  </activity>
  
  <activity android:name="ShareActivity">
      <!-- This activity handles "SEND" actions with text data -->
      <intent-filter>
          <action android:name="android.intent.action.SEND"/>
          <category android:name="android.intent.category.DEFAULT"/>
          <data android:mimeType="text/plain"/>
      </intent-filter>
      <!-- This activity also handles "SEND" and "SEND_MULTIPLE" with media data -->
      <intent-filter>
          <action android:name="android.intent.action.SEND"/>
          <action android:name="android.intent.action.SEND_MULTIPLE"/>
          <category android:name="android.intent.category.DEFAULT"/>
          <data android:mimeType="application/vnd.google.panorama360+jpg"/>
          <data android:mimeType="image/*"/>
          <data android:mimeType="video/*"/>
      </intent-filter>
  </activity>
  ```





