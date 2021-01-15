

# 리사이클러뷰(RecyclerView)

- 특징

  - 많은 데이터 집합을 제한된 영역 내에서 유연하게 표시할 수 있게 하는 위젯
  - 레이아웃 매니저를 사용해 화면에서 개별 항목(item)의 위치를 정함
  - 한 화면에 표시되기 힘든 경우 스크롤 가능한 리스트로 표시
  - 화면에 보이지 않는 항목을 재사용할 시점 결정
  - View를 재사용 하려면 어댑터에 콘텐츠 교체 요청
  - 불필요한 View의 생성을 피하고 `findViewById()` 조회를 방지해 성능 향상

  

- 레이아웃 매니저

  - 아이템 뷰의 나열 형태를 관리하는 역할

    - LinearLayoutManager

      1차원 목록으로 정렬(ListView와 같은 기능 제공)

    - GridLayoutManager

      2차원 그리드로 정렬(GridView와 같은 기능 제공)

    - StaggeredGridLayoutManager

      만화, 신문 기사처럼 엇갈린 그리드로 정렬

    - MORE...

      `RecyclerView.LayoutManager` 추상 클래스 확장으로 레이아웃 매니저 생성 가능



- 유연성

  - 기존 ListView의 단점(수직 방향 고정, 동적 구성 어려움)을 보완해 item들의 수평 방향 나열을 지원하고, run-time 내에 아이템 뷰의 동적 구성을 바꿀 수 있음

  => RecyclerView의 구현 요소, 구현 결과물이 쉽게 변경/확장 가능

  

- 구성 요소

  - 어댑터(Adapter)

    데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 표시하기 위해 사용

  - 레이아웃 매니저(LayoutManager)

    아이템 뷰가 나열되는 형태를 관리하기 위해 사용

  - 뷰 홀더(ViewHolder)

    어댑터를 통해 만들어진 각 아이템 뷰가 저장되는 객체로 화면에 표시되는 것

    필요에 따라 생성하거나 재활용

    어댑터 클래스 안에 존재

  

- 구현

  1. activity_main.xml에 RecyclerView 추가

     ```xml
     <androidx.recyclerview.widget.RecyclerView
             android:id="@+id/recyclerView"
             android:layout_width="match_parent"
             android:layout_height="354dp"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent" />
     ```

     ** RecyclerView가 담긴 레이아웃의 height 속성은 match_parent로 설정*

     

  2. RecyclerView에 표시될 아이템 뷰 레이아웃 추가

     <center>
         <img src="https://user-images.githubusercontent.com/50495214/104537800-01ad9600-565e-11eb-858b-59ff770c4be1.png" height="100">
     </center>

     
** [item_info.xml](https://github.com/xxunghee/AndroidBasic/blob/main/MyRecycler/app/src/main/res/layout/item_info.xml) 생성 (코드 참고)*
     
** 이름과 나이를 받아오기 때문에 `String name`, `int age`를 변수로 갖는 [Info.java](https://github.com/xxunghee/AndroidBasic/blob/main/MyRecycler/app/src/main/java/com/example/recycler/Info.java) 클래스 생성*
     

     
3. Adapter 클래스 구현
  
   ```java
     public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
     
         //---- 데이터가 저장될 배열
         private ArrayList<Info> data = null;
     
         //---- 초기화
         InfoAdapter(ArrayList<Info> list) {
             data = list;
         }
     
         //---- 아이템 뷰를 저장하고 화면에 보여주는 ViewHolder 클래스 생성
         static class ViewHolder extends RecyclerView.ViewHolder {
             private TextView nameView;
             private TextView ageView;
     
             ViewHolder(View itemView) {
                 super(itemView);
                 nameView = (TextView) itemView.findViewById(R.id.tv_name);
                 ageView = (TextView) itemView.findViewById(R.id.tv_age);
             }
         }
     
         //---- ViewHolder 생성
         @NonNull
         @Override
         public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             //---- 인플레이션,
             Context context = parent.getContext();
             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View view = inflater.inflate(R.layout.item_info, parent, false);
     
             //---- 아이템 뷰를 위한 ViewHolder 객체 생성하여 리턴
             InfoAdapter.ViewHolder viewHolder = new InfoAdapter.ViewHolder(view);
             return viewHolder;
         }
     
         //---- 바인딩하여 데이터를 아이템 뷰에 표시
         @Override
         public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
             Info info = data.get(position);
             holder.nameView.setText(info.getName());
             holder.ageView.setText(info.getAge());
         }
     
         //---- 전체 데이터 수 리턴
         @Override
         public int getItemCount() {
             return data.size();
         }
     }
     ```
  
   
  
4. MainActivity.java에서 RecyclerView에 Adapter, LayoutManager 지정
  
   ```java
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
     
         ArrayList<Info> list = new ArrayList<Info>();
         list.add(new Info("ABC", 1));
         list.add(new Info("DEF", 2));
         list.add(new Info("GHI", 3));
         list.add(new Info("JKL", 4));
         list.add(new Info("MNO", 5));
         list.add(new Info("PQR", 6));
         list.add(new Info("STU", 7));
         list.add(new Info("VWX", 8));
         list.add(new Info("YZ", 9));
     
         //---- RecyclerView에 LayoutManager 지정
         RecyclerView recyclerView = findViewById(R.id.recyclerView);
         /** LinearLayout의 방향(기본: vertical)을 horizontal로 변경할 때
          *  recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;
          */
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
     
         //---- RecyclerView에 Adapter 지정
         InfoAdapter adapter = new InfoAdapter(list);
         recyclerView.setAdapter(adapter);
     }
     ```



- 결과

  RecyclerView 영역에 스크롤 가능한 이름, 나이 창이 보이는 것 확인

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/104537857-15f19300-565e-11eb-9db9-877a28ed4aaa.PNG" width="300">
      <img src="https://user-images.githubusercontent.com/50495214/104537859-17bb5680-565e-11eb-9489-761f78d70cd7.PNG" width="300">
  </center>

  

### + DataBinding 적용

- 구현

  1. build.gradle(app) 수정

     ```
     android {
     	... 생략
     	dataBinding {
     		enable true
     	}
     	... 생략
     }
     ```

     

  2. activity_main.xml 수정

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <layout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto">
     
         <data>
             <variable
                 name="main"
                 type="com.example.recycler.MainActivity" />
         </data>
     
         <!-- ConstraintLayout 태그 부분 -->
     
     </layout>
     ```

     

  3. MainActivity.java 수정

     ```java
     // ...생략
     import com.example.recycler.databinding.ActivityMainBinding;
     // ...생략
     
     public class MainActivity extends AppCompatActivity {
         //++++ Binding 객체 추가
         ActivityMainBinding binding;
         InfoAdapter adapter;
     
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
     
             //++++ Layout 설정
             binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
     
             // ==생략 == (ArrayList에 데이터 추가)
     
             //++++ RecyclerView에 LayoutManager 지정
             binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
     
             //++++ RecyclerView에 Adapter 지정
             adapter = new InfoAdapter(list);
             binding.recyclerView.setAdapter(adapter);
         }
     }
     ```

     

  4. item_info.xml 수정

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <layout xmlns:android="http://schemas.android.com/apk/res/android">
     
         <data>
             <variable
                 name="movie"
                 type="com.example.recycler.Info"/>
     
         </data>
         
         <!-- FrameLayout 태그 부분 -->
         
     </layout>
     ```

     

  5. InfoAdapter.java 수정

     ```java
     // ... 생략
     import com.example.recycler.databinding.ItemInfoBinding;
     // ... 생략
     
     public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
         //---- 데이터가 저장될 배열
         private ArrayList<Info> data = null;
     
         //---- 초기화
         InfoAdapter(ArrayList<Info> list) {
             data = list;
         }
     
         //---- 아이템 뷰를 저장하고 화면에 보여주는 ViewHolder 클래스 생성
         static class ViewHolder extends RecyclerView.ViewHolder {
             //++++ Binding 객체
             ItemInfoBinding binding;
     
             //++++ 생성자의 인자를 Binding 객체로 수정
             ViewHolder(ItemInfoBinding binding) {
                 super(binding.getRoot());
                 this.binding = binding;
             }
     
             //++++ 데이터 넣는 함수 분리하여 구현
             public void bind(Info info) {
                 binding.tvName.setText(info.getName());
                 binding.tvAge.setText(info.getAge());
             }
         }
     
         //---- ViewHolder 생성
         @NonNull
         @Override
         public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             //++++ 인플레이션
             ItemInfoBinding binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
     
             return new ViewHolder(binding);
         }
     
         //++++ 데이터를 bind()에 전달
         @Override
         public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
             Info info = data.get(position);
             holder.bind(info);
         }
     
         //---- 전체 데이터 수 리턴
         @Override
         public int getItemCount() {
             return data.size();
         }
     }
     ```

     

