# 브로드캐스트 리시버(Broadcast Receiver)

- 특징

  - 안드로이드에서 발생하는 많은 이벤트를 감지하여 해당 상황에서 메세지를 받을 수 있음
  - 배터리 부족이나 문자 메세지와 같은 이벤트에 대한 방송 신호(Broadcast)를 받아서 처리하는 방식으로 동작
  - Intent에 메세지를 담아 전달하는 방식 사용

  

- 구현

  - 정적 구현

    ** 문자 메세지를 받았을 때, 송신자의 번호와 문자 내용을 화면에 출력하는 프로그램 구현*

    1. app/java에 BroadcastReceiver 클래스를 상속받는 SmsReceiver.java 생성 후 ```onReceive()``` 오버라이딩

       ```java
       public class SmsReceiver extends BroadcastReceiver {
           @Override
           public void onReceive(Context context, Intent intent) { ... }
       }
       ```

       

    2. app/manifests/AndroidManifest.xml의 `<application>` 태그 안에 `receiver` 추가

       ```xml
       <!-- SMS 수신하기 위한 권한(RECEIVE_SMS) 추가 -->
       <uses-permission android:name="android.permission.RECEIVE_SMS" />
       
       <application ...>
           ...
           <receiver
                android:name=".SmsReceiver"
                android:enabled="true"
                android:exported="true">
               <!-- intent-filter 안에 수신하고자 하는 메세지 유형을 action으로 정의-->
                <intent-filter>
                    <action android:name="android.provider.Telephony.SMS_RECEIVED" />
                </intent-filter>
           </receiver>
           ...
       </application>
       ```

    

  - 동적 구현

    ** 버튼 클릭 시 Toast 메세지로 수신한 메세지 출력하는 프로그램 구현*

    - app/java/MainActivity.java에 다음과 같이 코드 작성

    ```java
    package ...
    import  ...
    
    public class MainActivity extends AppCompatActivity {
        int number = 0; // 전송한 메세지 수
    
        // 리시버 생성 및 수신 시 동작 설정
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int receivedData = intent.getIntExtra("msg", 0);
                Toast.makeText(context, "Data: "+receivedData, Toast.LENGTH_LONG).show();
    
                if(intent.getAction().equals("BroadcastReceive")) {
                    Toast.makeText(context, "Data: "+receivedData, Toast.LENGTH_LONG).show();
                }
            }
        };
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
            // IntentFilter 설정
            IntentFilter filter = new IntentFilter();
            filter.addAction("BroadcastReceive");
            registerReceiver(broadcastReceiver, filter); // 리시버 연결
    
            // 버튼 클릭 시 메세지 전달
            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("BroadcastReceive");
                    intent.putExtra("msg", number);
                    sendBroadcast(intent);
                    number++;
                }
            });
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(broadcastReceiver); // 리시버 연결 해제
        }
    }
    ```

    

- 정적 Receiver **vs** 동적 Receiver

  | 정적 Receiver                                                | 동적 Receiver                                                |
  | :----------------------------------------------------------- | :----------------------------------------------------------- |
  | - 한 번 등록하면 해체하기 어려움<br /> => 계속 유지<br />- 사용하고 싶은 특정 컴포넌트의 멤버 변수에 접근 불가 | - 등록과 해체가 유연 <br /> => 등록, 해체가 빈번한 경우 유리 <br />- 자신을 등록한 Activity의 생명 주기에 영향 받음<br />- 컴포넌트 안의 변수와 메소드에 접근 유연 |



- 주의

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/104155790-400f3f00-542b-11eb-9766-e39d916c4fb3.png" height="400">
  </center>

  - 하나씩 순차적으로 동작하기 때문에 여러 개의 BroadcastReceiver를 등록한 경우에는 마지막에 실행되는 BroadcastReceiver의 작업 지연이 심해질 수 있음(ANR 발생 가능)

    => Receiver는 간단한 작업만 하도록..

  - bindService 허용되지 않음