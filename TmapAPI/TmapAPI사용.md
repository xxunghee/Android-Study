# T map API 사용

- API 사용 준비

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/104991463-2ccf2580-5a62-11eb-9079-42bef464bc43.png" >
  </center>

  

  1. [SK open API](https://openapi.sk.com/) 회원가입

  2. My Project 탭에서 새 프로젝트 생성

  3. 프로젝트 선택 후 Service 탭에서 이용할 API 선택 후 구매(무료버전 사용)

  4. Resources 탭에서 SDK&Tools 선택, Android TmapSDK 다운로드 후 압축 해제

  5. Android Project 생성

  6. com.skt.Tmap_1.67.jar 파일을 프로젝트의 lib 폴더에 붙여넣기

  7. 붙여넣은 파일(.jar) 우클릭 > "Add As Library" 선택하여 프로젝트에 추가

  8. AndroidManifest.xml에 권한 추가

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <manifest xmlns:android="http://schemas.android.com/apk/res/android"
         package="com.example.tmaptest">
     
         <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     
         <application>
             ...
         </application>
         ...
     </manifest>
     ```

  9. TMapView를 사용하는 첫 화면(MainActivity)에서 인증

     ```java
     TMapView tmapview = new TMapView(this);
     tmapview.setSKTMapApiKey("APPKEY_INPUT");
     ```



- 사용하기

  - [코드](https://github.com/xxunghee/Android-Study/tree/main/TmapAPI/TmapTest) 참조

  

- 결과

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/105012238-47fd5d80-5a81-11eb-9204-f90600988bff.png">
  </center>

  

