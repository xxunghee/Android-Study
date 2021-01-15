# 데이터바인딩(DataBinding)

- 특징

  - XML 파일에 Data를 연결하여 사용할 수 있게 도와주는 Android JetPack 라이브러리의 기능 중 하나

  - 불필요한 코드(글루 코드, ex. `findViewById()`)의 사용 최소화

    

- 구현

  1. DataBinding 적용 전 코드

     - activity_main.xml

       ```xml
       <?xml version="1.0" encoding="utf-8"?>
       <androidx.constraintlayout.widget.ConstraintLayout
           xmlns:android="http://schemas.android.com/apk/res/android"
           xmlns:app="http://schemas.android.com/apk/res-auto"
           xmlns:tools="http://schemas.android.com/tools"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           tools:context=".MainActivity">
       
           <TextView
               android:id="@+id/textView"
                ... 생략 ...
               />
       
           <Button
               android:id="@+id/btn_change"
                ... 생략 ...
               />
       
       </androidx.constraintlayout.widget.ConstraintLayout>
       ```

       

     - MainActivity.java

       ```java
       package com.example.databinding;
       
       import androidx.appcompat.app.AppCompatActivity;
       
       import //...생략
       
       public class MainActivity extends AppCompatActivity {
           Button ChangeBtn;
           TextView textView;
           int num;
           String str;
       
           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               //---- layout 설정
               setContentView(R.layout.activity_main);
       
               //---- XML 파일의 TextView, Button 받아오기
               textView = findViewById(R.id.textView);
               ChangeBtn = findViewById(R.id.btn_change);
       
               //---- 클릭 이벤트 설정(클릭 시 textView 숫자 ++)
               ChangeBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       num = Integer.parseInt((String)textView.getText());
                       str = Integer.toString(num+1);
                       textView.setText(str);
                   }
               });
           }
       }
       ```

       

  2. DataBinding 적용

     - build.gradle(app)에 dataBinding 추가 후 SyncNow

       ```
       android {
           ... 생략 ...
           
           dataBinding {
               enable true
           }
           
           ... 생략 ...
       }
       ```

       

     - 원래의 activity_main.xml을 `<layout>` 태그로 감싸고  `<data>` 추가

       ```xml
       <?xml version="1.0" encoding="utf-8"?>
       <layout xmlns:android="http://schemas.android.com/apk/res/android"
           xmlns:app="http://schemas.android.com/apk/res-auto"
           xmlns:tools="http://schemas.android.com/tools">
       
           <!-- 추가 -->
           <data>
               <variable
                   name="main"
                   type="com.example.databinding.MainActivity"/>
           </data>
           
           <androidx.constraintlayout.widget.ConstraintLayout
               android:layout_width="match_parent"
               android:layout_height="match_parent"
               tools:context=".MainActivity">
               <!-- 아래 항목은 위(<layout> 태그)와 중복되므로 삭제-->
               <!-- xmlns:android="http://schemas.android.com/apk/res/android"-->
               <!-- xmlns:app="http://schemas.android.com/apk/res-auto"-->
               <!-- xmlns:tools="http://schemas.android.com/tools"-->
               
               ... 생략 ...
           
           </androidx.constraintlayout.widget.ConstraintLayout>
       </layout>
       ```

       

     - MainActivity.java 수정

       ```java
       package com.example.databinding;
       
       import //...생략
       
       import com.example.databinding.databinding.ActivityMainBinding;
       
       public class MainActivity extends AppCompatActivity {
           int num;
           String str;
       
       //++++ 수정 1) Binding 객체 생성
           ActivityMainBinding binding;
       
           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
       //++++ 수정 2) layout 설정
               binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       
               //---- 클릭 이벤트 설정(클릭 시 textView 숫자 ++)
               binding.btnChange.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
       //++++ 수정 3) 생성한 binding 객체로 XML 파일 내의 View에 접근
                       num = Integer.parseInt((String)binding.textView.getText());
                       str = Integer.toString(num+1);
                       binding.textView.setText(str);
                   }
               });
           }
       }
       ```

       ** 불필요한 import 코드 삭제 가능*