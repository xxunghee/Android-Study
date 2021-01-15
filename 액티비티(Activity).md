# 액티비티(Activity)

- 특징

  - 화면을 구성하는 가장 기본적인 구성 요소

  - 안드로이드 어플리케이션은 화면에 UI를 표시하기 위해 최소 하나 이상의 Activity를 가져야 함

  - Activity 하나 당 하나의 XML 파일을 가짐

  - 한 어플리케이션을 호출할 때 전체적으로 호출하지 않고 호출할 어플리케이션의 한 Activity를 호출

    

- 구현

  1. app/java에 Activity 파일 생성(SubActivity.java), 함수 오버라이딩

     ```java
     public class SubActivity extends AppCompatActivity {
         @Override
         protected void onCreate(Bundle savedInstanceState) {...}
         
         @Override
         protected void onStart() {...}
     
         @Override
         protected void onResume() {...}
     
         @Override
         protected void onPause() {...}
     
         @Override
         protected void onStop() {...}
     
         @Override
         protected void onDestroy() {...}
     }
     ```

     

  2. app/res/layout에 XML 파일(activity_sub.xml) 생성, Activity 파일의 레이아웃으로 등록

     ```java
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_sub); // layout 등록
     ```

  

- 생명 주기

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/104147533-11389f00-5412-11eb-8e19-8d772c115e73.png" width="450">
  </center>

  - Activity의 3가지 상태

    - 실행(Resumed) : Activity가 화면에 보이고, 사용자가 사용할 수 있는 상태
    - 일시정지(Paused) : 다른 Activity에 의해 화면에서 숨겨진 상태
    - 중지(Stopped) : 화면에 보이지 않으면서 중지된 상태

    

  - Activity 시작 시 `onCreate()` `onStart()` `onResume()` 순서대로 호출됨

    <center>
            <img src="https://user-images.githubusercontent.com/50495214/104148053-0bdc5400-5414-11eb-8cfb-a230df461b8b.PNG" width="700">
    </center>

    

  - Activity 종료 시 `onPause()` `onStop()` `onDestroy()` 순서대로 호출됨

    <center>
        <img src="https://user-images.githubusercontent.com/50495214/104148054-0c74ea80-5414-11eb-858a-213c9c4ec6a9.PNG" width="700">
    </center>

    

