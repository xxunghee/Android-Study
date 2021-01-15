# 프래그먼트(Fragment)

- 특징

  - 사용자 인터페이스(UI)의 일부로 Activiry를 본 떠 만든 것
  - 부분 화면을 독립적으로 만들어 줌
  - 여러 개의 Fragment를 하나의 Activity에 결합하여 창이 여러 개인 UI를 빌드할 수 있음
  - 하나의 Fragment를 여러 개의 Activity에 재사용 가능
  - Activity 위에서 동작하기 때문에 Activity의 수명 주기에 직접적인 영향을 받음
    Activity가 실행 중인 동안은 개별적으로 조작 가능

  

- 구현

  1. Fragment 생성

     (app/src/main/java/MainFragment) 

     ```java
     import androidx.fragment.app.Fragment;
     
     import android.view.LayoutInflater;
     import android.view.*;
     import android.widget.*;
     
     public class MainFragment extends Fragment {
     
         // xml 레이아웃에 있는 것을 인플레이션
         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
             ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
             Button button = rootView.findViewById(R.id.button);
             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     MainActivity activity = (MainActivity)getActivity();
                     activity.onFragmentChanged(1); // 클릭 시 함수 호출
                 }
             });
             return rootView;
         }
     }
     ```

     (app/src/main/res/layout/fragment_main.xml)

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:background="#FAE5D9"
         android:orientation="vertical"
         tools:context=".MainFragment">
     
         <TextView
             android:id="@+id/textView"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:text="Main Fragment"
             android:textSize="50sp" />
     
         <Button
             android:id="@+id/button"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:text="화면 전환"
             app:backgroundTint="#41C5B1" />
     </LinearLayout>
     ```

     

  2. Activity에 Fragment 추가(XML 사용)

     activity_main.xml의 `<Layout>`태그 안에 생성한 MainFragment 추가

     ```xml
     <fragment
             android:id="@+id/mainFragment"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:name="com.example.fragment.MainFragment"  />
     ```

     

  3. Fragment 초기화

     MainActivity.java의 `onCreate()`에 Fragment 추가

     ```java
     MainFragment mainFragment;
     MenuFragment menuFragment;
     
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
     
         mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
         menuFragment = (MenuFragment)getSupportFragmentManager().findFragmentById(R.id.menuFragment);
     }
     
     public void onFragmentChanged(int index) {
         if(index == 0) { // 전달 받은 index가 0인 경우 mainFragment 띄우기
             getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
         }
         else if(index == 1) { // 전달 받은 index가 1인경우 menuFragment 띄우기
             getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
         }
     }
     ```
     
     

- 생명 주기

  - Activity 위에서 실행되기 때문에 Activity의 생명 주기에 영향 받음

    ex. Activity 일시정지 => Fragment 일시정지, Activity 소멸 =>  Fragment 소멸

  - Activity가 실행 중인 동안에만 삭제, 추가 등의 동작 가능

    

    <center>
        <img src="https://user-images.githubusercontent.com/50495214/104140842-dc6b1e80-53f6-11eb-9419-a7abc4daafa0.png" width="300">
        <img src="https://user-images.githubusercontent.com/50495214/104141536-a29c1700-53fa-11eb-87cb-ba5e85ebd9a8.png" width="300">
    </center>

    

  - ```onAttach()``` : Fragment와 Activity가 연결된 경우 호출되는 함수로, Activity 전달됨

  - ```onCreateView()``` : Fragment와 연결된 View 계층을 생성할 때 호출되는 함수

  - ```onActivityCreated()``` : Activity의 ```onCreate()``` 메소드가 반환할 때 호출되는 함수

  - ```onDestroyView()``` : Fragment와 연결된 View 계층이 제거되는 중일 때 호출되는 함수

  - ```onDetach()``` : Fragment와 Activity 간 연결이 끊어지는 중일 때 호출되는 함수



- Activity vs Fragment

  | <center> </center> | <center>Activity</center>                                    |                  <center>Fragment</center>                   |
  | :----------------: | ------------------------------------------------------------ | :----------------------------------------------------------: |
  |   **UI&UX 구성**   | <center>제한적 </center>                                     |                 <center> 자유로움 </center>                  |
  |    **재사용성**    | <center>재사용 불가능 </center>                              |                <center>재사용 가능 </center>                 |
  |    **퍼포먼스**    | <center>무겁다 <br />액티비티를 액티비티 스택에 쌓는 방식으로 동작 </center>| <center>가볍다 <br /> Activity 내에서 상대적으로 가볍게 추가/제거 가능 <br />프래그먼트 백스택에서 프래그먼트를 관리하는 것이 메모리 측면에서 효율적 </center>|
  |  **데이터 공유**   | <center>Intent 사용</center>                                 |       <center>Activity 내에서 자유롭게 가능 </center>        |
