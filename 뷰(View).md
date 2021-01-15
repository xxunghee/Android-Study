# 뷰(View)



### 뷰(View)

- 화면에 나타나는 모든 요소
- 필수 속성:  Width, Height
- TextView, Button, LinearLayout 등

- 상속 관계 및 크기 속성

  

  <center>
      <img src="https://user-images.githubusercontent.com/50495214/103965024-28705600-51a0-11eb-8304-b4f2734dd648.png" height="350" >
  </center>

  

  

  <center> <img src="https://user-images.githubusercontent.com/50495214/103965218-974daf00-51a0-11eb-87f5-732cfd95b27d.png" height="200"> </center>

  - Box: View가 차지하는 크기
  - Border: View의 테두리
  - Margin: Border 바깥쪽의 여백
  - Pading: Border 안쪽의 여백



### 뷰 그룹(ViewGroup)

- 여러 View들을 포함하고 있는 것
- View의 자식 컴포넌트로 View의 속성을 상속 받음

- View - ViewGroup 간 상속 관계



<center><img src="https://user-images.githubusercontent.com/50495214/103964390-afbcca00-519e-11eb-850f-b4397acab714.png" width="200"></center>



### 위젯(Widget)

- View 중에서 화면에 표시할 수 있는 것(= 눈에 보이는 것)
- TextView, Button 등



### 레이아웃(Layout)

- View 중에서 다른 View를 담을 수 있는 것
- Widget과 다르게 눈에 보이지 않음
- 레이아웃도 View이기 때문에 레이아웃 안에 레이아웃 담을 수 있음

- 종류

  - ConstraintLayout

    - 제약 조건(연결점, 연결선)으로 View들의 위치를 결정
  
    - RelativeLayout의 상대적 위치 관계에 따른 배치와 LinearLayout의 weight 속성 가짐
  
    - chain을 사용해 다른 레이아웃 없이 요소들의 그룹화 가능

    - 수평적인 구조로 성능 향상
    
      

  - LinearLayout

    - View들을 수직 또는 수평으로 나란히 배치 가능(orientation 속성 조절 - vertical/horizontal)

      i) vertial (수직 배치)

      <center><img src="https://user-images.githubusercontent.com/50495214/103966268-e5fc4880-51a2-11eb-80de-0517bb4379be.PNG" width="450"> </center>

      *보라색 버튼의 옆 공간은 다른 구성 요소가 위치할 수 없는 버려진 공간
  
      ii) horizontal (수평배치)
  
    <center>
          <img src="https://user-images.githubusercontent.com/50495214/103966249-d8df5980-51a2-11eb-81f1-0312d28462ac.PNG" width="450">
        <img src="https://user-images.githubusercontent.com/50495214/103966252-d977f000-51a2-11eb-995a-28066f77af7e.PNG" width="450">
      </center>

      *보라색 버튼의 너비 속성을 match_parent로 설정한 경우, 다른 두 버튼이 가려짐

      *버튼 아래 공간은 다른 구성 요소가 위치할 수 없는 버려진 공간
      
    
  
- RelativeLayout
  
  - 레이아웃 안의 View들이 서로 간의 상대적인 위치 관계에 따라 최종 표시될 영역 결정
  
    - 배치하고 싶은 View와 기준이 되는 View 사이의 below, above 설정을 통해 적용
  
    
  
- FrameLayout
  
  - 여러 개의 View가 중첩되어 있을 때, 하나의 View만 보이게 함
  
    - `<FrameLayout>` 태그를 통해 중첩 View들을 삽입한 후 `android:visibility=[visible | invisible]` 속성 정의로 화면에 보여질 요소 정의 가능
  
    - 일반적으로 가장 먼저 위치한 View가 가장 뒤쪽으로 배치됨
  
      <center>
                <img src="https://user-images.githubusercontent.com/50495214/103967144-b5b5a980-51a4-11eb-899d-7cf438834200.PNG" width="400">
            </center>
  
      
      
      *노란 별 이미지(imageView3)과 회색 별 이미지(imageView4)를 넣었을 때 회색 별 이미지만 보임
  
      
  
  - TableLayout
    - 엑셀 시트처럼 가로세로 격자를 몇 개 만들지 설정하고, 그 안에 View를 넣는 방식으로 동작
    - 자유도가 낮아 많이 사용되지 않음



### 스크롤 뷰(ScrollView)

- 위젯이나 레이아웃이 화면에 넘칠 때 사용
- 수직으로 스크롤 가능하고 수평으로 스크롤 할 때는 HorizontalScrollView 사용
- 스크롤 뷰 안에는 하나의 View만 넣을 수 있기 때문에, 일반적으로 LinearLayout을 넣고 그 안에 위젯을 여러 개 넣는는 방법 사용
