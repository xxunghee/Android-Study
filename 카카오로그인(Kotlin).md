@@ -0,0 +1,223 @@
# 카카오 로그인(Java)

- [kakao developers](https://developers.kakao.com)에서 제공하는 API를 사용해 안드로이드 어플리케이션에 카카오 로그인 구현



### 구현

1.  프로젝트 생성

   1st Depth: MainActivity

   2nd Depth: HomeActivity

   

2.  [kakao developers](https://developers.kakao.com) 로그인 > 내 애플리케이션 > 애플리케이션 추가하기

   ![2-1](https://user-images.githubusercontent.com/50495214/106247276-a7622700-6252-11eb-8b99-cc1975c2a04e.png)

   

   ![2-2](https://user-images.githubusercontent.com/50495214/106247278-a8935400-6252-11eb-8c5c-32aa9c532037.png)

   

3.  앱 아이콘, 앱 이름, 사업자명(사업자명 || 내 이름) 설정

   ![3](https://user-images.githubusercontent.com/50495214/106247396-d24c7b00-6252-11eb-9a6b-b237be9448eb.PNG)

   

4.  플랫폼 설정하기 > Android 플랫폼 등록

   ![4-1](https://user-images.githubusercontent.com/50495214/106247917-91089b00-6253-11eb-975d-b9d956cf1611.png)

   

5.  프로젝트 패키지명 입력 후 저장

   ![4-2](https://user-images.githubusercontent.com/50495214/106247920-9239c800-6253-11eb-95cd-0ec6f5848d45.png)

   

6.   build.gradle 설정 (Project 단위)

   ```
   allprojects {
   	repositories {
   		...
   		
   		// 카카오 SDK 레포지토리 추가
   		maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/'}
   	}
   }
   ```

   

7. build.gradle 설정 (Module 단위)

   ```
   // 컴파일 옵션의 자바 버전 확인
   compileOptions {
           sourceCompatibility JavaVersion.VERSION_1_8
           targetCompatibility JavaVersion.VERSION_1_8
   }
   
   dependencies {
   	...
   	
       // Kakao SDK 모듈 라이브러리 추가
       implementation "com.kakao.sdk:v2-user:2.3.0" // 카카오 로그인
   }
   ```

   

8. `res/values/string.xml`에 `kakao_app_key` 설정(선택)

   ![8](https://user-images.githubusercontent.com/50495214/106249125-48ea7800-6255-11eb-8c3d-1e2f5fc95b36.png)

   

   네이티브 앱 키 복사 > `strings.xml`에 추가

   ```xml
   <string name="kakao_app_key">여기에_붙여넣기</string>
   ```

   

9.  `MyAppication.kt` 생성

   ```kotlin
   class MyApplication : Application() {
       override fun onCreate() {
           super.onCreate()
           KakaoSdk.init(this, getString(R.string.kakao_app_key))
           Log.d("KEYHASH", Utility.getKeyHash(this));
       }
   }
   ```



10. `AndroidManifest.xml` 설정

    ```xml
    <manifest ...>
        <!-- 인터넷 사용 권한 추가 -->
        <uses-permission android:name="android.permission.INTERNET" />
        
        <application
            android:name=".MyApplication"
            ... >
            <activity android:name=".MainActivity">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
    
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
            <activity> <!-- 그 외 액티비티들 --></activity>
            
            <!-- 추가 -->
            <activity android:name="com.kakao.sdk.auth.AuthCodeHandlerActivity">
                <intent-filter>
                    <action android:name="android.intent.action.VIEW" />
                    <category android:name="android.intent.category.DEFAULT" />
                    <category android:name="android.intent.category.BROWSABLE" />
    
                    <!-- Redirect URI: "kakao{NATIVE_APP_KEY}://oauth" -->
                    <data android:host="oauth"
                        android:scheme="kakao+네이티브 앱 키" />
                    <!-- ex) 네이티브 앱 키: 0123 => android:scheme="kakao0123" -->
                </intent-filter>
            </activity>
        </application>
                 
    </manifest>
    ```

    

11. 키 해시 등록 및 Redirect URI 설정

    - 키 해시 등록

      프로젝트 실행 후 Logcat에 KEYHASH 검색하여 출력된 키해시 복사

      앱설정 - 플랫폼 > Android > [수정] > "키 해시"에 붙여넣기

    - Redirect URI

      제품 설정 - 카카오 로그인 > Redirect URI > [수정] > 카카오 서버와 정보를 주고 받을 URI 입력

      

12.  `MainActivity.kt` (로그인 화면) 구현

    ```kotlin
    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    
            val callback: ((OAuthToken?, Throwable?) -> Unit) = { token, error ->
                if (error != null) { //Login Fail
                    Log.e("MainActivity", "Kakao Login Failed :", error)
                } else if (token != null) { //Login Success
                    Log.d("MainActivity", "Kakao Login Success")
                    startHomeActivity()
                }
            }
            
            // 로그인 버튼 클릭
            findViewById<ImageView>(R.id.btn_kakao_login).setOnClickListener {
                LoginClient.instance.run {
                    // 기기에 카카오톡이 설치되어 있는 경우, 카카오톡으로 로그인
                    if (isKakaoTalkLoginAvailable(this@MainActivity)) {
                        loginWithKakaoTalk(this@MainActivity, callback = callback)
                    }
                    // 기기에 카카오톡이 없는 경우, 카카오 계정으로 로그인
                    else {
                        loginWithKakaoAccount(this@MainActivity, callback = callback)
                    }
                }
            }
        }
    
        private fun startHomeActivity() {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
    ```









#### 전체 코드 [here](https://github.com/xxunghee/Android-Proj/tree/main/MyKakaoLoginKotlin)