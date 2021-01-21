# 리스트 뷰(ListView)

- 리스트 뷰
  - 여러 개의 목록을 보여주는 위젯
  - 아이템을 Adapter에 전달하고, ListView에 Adapter를 전달하는 방식으로 동작
  - Adapter에 넣을 데이터의 타입은 사용자 정의 타입, 정수형, 문자 모두 가능함



- 커스텀 리스트 뷰 구현

  - Niniz 캐릭터들의 사진과 이름을 ListView에 출력

  - 캐릭터 항목을 선택했을 때 이벤트 구현

    

    1.  ListView 추가(`activity_main.xml`)

       ```xml
       <LinearLayout>
       
           	<!-- 선택한 캐릭터의 이미지, 이름을 띄울 공간 -->
               <LinearLayout
                   ...
               </LinearLayout>
       
           	<!-- ListView 추가 -->
               <ListView
                   android:id="@+id/list"
                   android:layout_width="match_parent"
                   android:layout_height="match_parent" />
       
           </LinearLayout>
       ```

       

    2.  사용자 데이터 정의(`Niniz.java`)

       ```java
       public class Niniz {
           private String name;
           private int imgID;
       
           Niniz(String name, int imgID) {
               this.name = name;
               this.imgID = imgID;
           }
       
           public String getName() {
               return name;
           }
       
           public int getImg() {
               return imgID;
           }
       }
       ```

       

    3.  ListView 아이템의 Layout 구성(`item_list.xml`)

       ```xml
       <LinearLayout>
       
           <!-- 이미지 보여줄 곳 -->
           <ImageView
               android:id="@+id/img"
               android:layout_width="0dp"
               android:layout_height="wrap_content"
               android:layout_weight="1"
               android:padding="10dp" />
       
           <!-- 이름 보여줄 곳 -->
           <TextView
               android:id="@+id/name"
               android:layout_width="0dp"
               android:layout_height="70dp"
               android:layout_weight="3"
               android:gravity="center_vertical"
               android:paddingLeft="20dp"
               android:textSize="20sp" />
       
       </LinearLayout>
       ```

       

    4.  Adapter 생성(`ListViewAdapter.java`)

       ```java
       public class ListViewAdapter extends BaseAdapter {
           private ArrayList<Niniz> ninizs = null;
           private Context context;
       
           //--- 전달 받은 객체로 ninizs 초기화
           public ListViewAdapter(ArrayList<Niniz> nini) {
               this.ninizs = nini;
           }
       
           @Override
           public int getCount() {
               return ninizs.size();
           }
       
           @Override
           public Niniz getItem(int position) {
               return ninizs.get(position);
           }
       
           @Override
           public long getItemId(int position) {
               return position;
           }
       
           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               context = parent.getContext();
       
               if(convertView == null) {
                   LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   convertView = inflater.inflate(R.layout.item_list, parent, false);
               }
       
               ImageView imageView = convertView.findViewById(R.id.img);
               TextView textView = convertView.findViewById(R.id.name);
       
               Niniz niniz = ninizs.get(position);
       
               imageView.setImageResource(niniz.getImg());
               textView.setText(niniz.getName());
       
               return convertView;
           }
       }
       ```

       

    5.  ListView에 Adapter 등록 및 데이터 넣기(`MainActivity.java`)

       ```java
       public class MainActivity extends AppCompatActivity {
       
           //--- 사용자 정의 자료형 Niniz(String name, ing ImgID)
           ArrayList<Niniz> ninizs;
       
           //--- Custom Adapter
           ListViewAdapter adapter;
       
           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       
               //--- 데이터 입력
               ninizs = new ArrayList<>();
               ninizs.add(new Niniz("스카피", R.drawable.scappy));
               ninizs.add(new Niniz("죠르디", R.drawable.jordy));
               ninizs.add(new Niniz("앙몬드", R.drawable.angmond));
               ninizs.add(new Niniz("케로 & 베로니", R.drawable.keroandberony));
               ninizs.add(new Niniz("콥", R.drawable.cob));
               ninizs.add(new Niniz("팬다 주니어", R.drawable.penda));
               ninizs.add(new Niniz("빠냐", R.drawable.bbanya));
       
               //--- Adapter 설정
               adapter = new ListViewAdapter(ninizs);
               binding.list.setAdapter(adapter);
       
               //--- ListView의 캐릭터 클릭 시 상단에 선택된 캐릭터의 이미지와 이름 나타냄
               binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       binding.clickedImg.setImageResource(ninizs.get(position).getImg());
                       binding.clickedTv.setText(ninizs.get(position).getName() + " 클릭!");
                   }
               });
           }
       }
       ```

       

- 결과

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/105326676-5aa69c80-5c11-11eb-897d-ebeb326a6c92.PNG" width="300">
      <img src="https://user-images.githubusercontent.com/50495214/105326684-5bd7c980-5c11-11eb-84f2-533f86ee301c.PNG" width="300">
  </center>

  