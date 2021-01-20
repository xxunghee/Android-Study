# 스피너(Spinner)

- 스피너

  - 여러 개의 값 중에서 하나를 선택하는 방법을 제공하는 것

  - 스피너 터치 시 선택 가능한 모든 값을 포함하는 드롭다운 메뉴 표시

    <center>
        <img src="https://user-images.githubusercontent.com/50495214/105133051-5609b780-5b2f-11eb-8d4c-04b2073cc934.png" width="300">
    </center>

  

- 사용

  1.  `activity_main.xml` 에 Spinner 추가

     ```xml
     <LinearLayout>
         ...
         <Spinner
              android:id="@+id/spinner"
              android:layout_width="300dp"
              android:layout_height="70dp"
              android:layout_gravity="center"
              android:background="#EAD9D9" />
         ...
     </LinearLayout>
     ```

     **배경색상, 너비, 높이 등은 마음대로 선택*

     

  2.  Spinner에 추가할 값 Adapter에 지정

     - 배열에서 가져오는 경우(`ArrayAdapter`)

       - **XML 파일로 배열 생성**

         1. `item_snack_array.xml`(경로: res/values/) 에서 `snack_array`배열 생성

            ```xml
            <resources>
                <string-array name="snack_array">
                    <item>선택하기</item>
                    <item>바나나킥</item>
                    <item>배배</item>
                    <item>눈을감자</item>
                    <item>고래밥</item>
                    <item>꼬북칩</item>
                </string-array>
            </resources>
            ```

            

         2. `MainActivity.java` 에서 Adapter 설정

            ```java
            import ...
            
            public class MainActivity extends AppCompatActivity {
                //--- dataBinding
                ActivityMainBinding binding;
            
                //--- Adapter
                ArrayAdapter<CharSequence> arrayAdapter;
            
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    ...
            
                    //--- Adapter 설정, snack_array 인자로 전달
                    arrayAdapter = ArrayAdapter.createFromResource(this, R.array.snack_array, android.R.layout.simple_spinner_item);
                   arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinner.setAdapter(arrayAdapter);
                }
            }
            ```

         

       - **`MainActivity.java`에서 배열 생성, Adapter 설정**

         ```java
         import ...
             
         public class MainActivity extends AppCompatActivity {
             //--- dataBinding
             ActivityMainBinding binding;
         
             //--- Adapter
             ArrayAdapter<String> arrayAdapter;
             
             @Override
             protected void onCreate(Bundle savedInstanceState) {
                 ...
         
                 //--- Adapter 설정, snackList 인자로 전달
                 arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, snackList);
                 binding.spinner.setAdapter(arrayAdapter);
             }
         }
         ```

       

     - Database에서 가져오는 경우(`CursorAdapter` 사용)

       - 정리 예정.

       

  3. `MainActivity.java`에서 항목 선택 시 이벤트 설정

     ```java
     import ...
         
     public class MainActivity extends AppCompatActivity {
     	...
             
         //--- drawable의 과자 이미지 배열에 저장
         ArrayList<Integer> Imgs = new ArrayList<>(Arrays.asList(new Integer[] {0, R.drawable.bananakick, R.drawable.baebae, R.drawable.nungam, R.drawable.goraebap, R.drawable.kkobuk}));
         
         ...
         
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             ...
     		//--- Spinner에서 Item 선택 시
             binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     setView(position);
                 }
                 @Override
                 public void onNothingSelected(AdapterView<?> parent) { }
             });
         }
     
         private void setView(int position) {
             binding.snackImg.setImageResource(Imgs.get(position));
             binding.snackName.setText(snackList.get(position) + " 선택!!");
         }
     }
     ```

     

- 결과

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/105138262-fd8ae800-5b37-11eb-9f04-370987980a55.PNG" width="200">
      <img src="https://user-images.githubusercontent.com/50495214/105138268-febc1500-5b37-11eb-96fe-96d221dbb770.PNG" width="200">
    <img src="https://user-images.githubusercontent.com/50495214/105138269-febc1500-5b37-11eb-85c1-6bc97ca2cde2.PNG" width="200">
  </center>
  
  
  