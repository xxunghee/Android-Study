# 인플레이션(Inflation)

### 인플레이션(Inflation)

- XML 레이아웃에 정의된 파일의 내용이 실제 메모리에 객체화되어 화면에 보여지는 과정

- 구현

  - `setContentView()` 활용 

    i) XML 레이아웃을 메모리 상에 객체화(인플레이션)

  ```java
  public void setContentView(int layoutResID)
  ```

  ​		ii) 화면에 보여줄View 지정

  ```java
  public void setContentView(View view, [ViewGroup.LayoutParams params])
  ```

- 예제

  - 새로운 View 추가

    ```java
    // xml에 'container'를 id로 갖는 LinearLayout 추가한 상태에서
    // container에 sub1.xml을 추가하는 함수
    public void addLayout() {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.sub1, container, true);
            Toast.makeText(this, "부분 화면이 추가되었습니다", Toast.LENGTH_LONG).show();
        }
    ```

    *container(LinearLayout)에 sub1이 추가되면, Toast 메시지 띄워서 알림