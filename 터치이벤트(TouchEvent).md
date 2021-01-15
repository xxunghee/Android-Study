# 터치 이벤트(TouchEvent)

- 특징

  - MotionEvent 객체를 사용해 화면을 터치했을 때 "누름, 움직임, 뗌"의 세 가지 상태 구분

  - 구현된 메소드 중 하나만 실행되는 것이 아닌 여러 개를 불러옴

    ex) 2초 동안 클릭했을 때 `onDown() -> onShowPress() -> onLongPress()` 실행

  

- 구현 방법

  - ```onTouchEvent``` 오버라이딩

    ```java
    @Override
    public boolean onTouchEvent(MotionEvent event) { 
        Log.d( TAG, "onTouchEvent onView" ); 
        boolean ret = false; // 리턴 값
        // true: 지금 도착한 이벤트에 대한 후속 이벤트 보내 | 이벤트 처리했으니 추가 작업 하지마
        // false: 아무것도 안 할래 이벤트 보내지 마
        
        float x = event.getX(); // 손가락의 X 좌표
        float y = event.getY(); // 손가락의 Y 좌표
        
        switch( event.getActionMasked() ) {
            //====화면에 손가락이 닿았을 때========
            case MotionEvent.ACTION_DOWN: 
                Log.d( TAG, "onTouch Down ACTION_DOWN : (" + x +", " + y + ")" );
                ret = true; 
                break;
            //====화면에서 손가락을 뗌============
            case MotionEvent.ACTION_UP: 
                Log.d( TAG, "onTouch Down ACTION_UP: (" + x +", " + y + ")" );
                performClick();
                ret = true; 
                break;
            //====화면에 손가락이 닿은 채로 움직임==
            case MotionEvent.ACTION_MOVE: 
                Log.d( TAG, "onTouch Down ACTION_MOVE: (" + x +", " + y + ")" );
                ret = true; 
                break; 
        } 
        return ret; 
    }
    ```

  

  - ```setOnTouchListner()```함수로 인터페이스가 적용된 객체 지정

    ```java
    // Activity 생성
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        
        GestureDetector detector;
    
        View view = findViewById(R.id.view);
        
        // View 터치 감지 시 동작할 터치 리스너 구현
        view.setOnTouchListener(new View.OnTouchListener() {   
        	@Override
        	public boolean onTouch(View v, MotionEvent event) {
    	        int action = event.getAction(); // 어떤 상태에서 호출된 건지 구분
        	    float curX = event.getX();		// X 좌표
            	float curY = event.getY();		// Y 좌표
            
    	        // 손가락이 눌린 상태
        	    if(action == MotionEvent.ACTION_DOWN) { 
            	    println("손가락 눌림: (" + curX + ", " + curY + ")");
            	}
            
           		// 손가락이 움직인 상태
            	else if(action == MotionEvent.ACTION_MOVE) {            
                	println("손가락 움직임: (" + curX + ", " + curY + ")");
            	}
            
            	// 손가락이 떨어진 상태
            	else if(action == MotionEvent.ACTION_UP) { 
                	println("손가락 뗌: (" + curX + ", " + curY + ")");
            	}
            	return true;
        	}
    	});
        
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            // 눌렀을 때
            @Override
            public boolean onDown(MotionEvent e) { 
                println("onDown 호출");
                return true;
               }
    
            @Override
            public void onShowPress(MotionEvent e) {       
            }
    
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
            }
            
            // 스크롤 된 거리
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { 
                return false;
            }
            
            // 길게 눌렀을 때
            @Override
            public void onLongPress(MotionEvent e) { 
                println("onLongPress 호출");
            }
            
            // 속도 계산(e1: 처음 위치, e2: 스크롤 된 위치)
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { 
                println("onFling 호출: " + velocityX + ", " + velocityY);
                return true;
            }
        });
    }
    
    // Back 버튼 눌렀을 때의 함수 오버라이딩
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            println("시스템 [BACK] 버튼 눌렸어요.");
            return true;
        }
        return false;
    }
    ```

    

    *MainActivity.java의 MainActivity 클래스 안에 오버라이딩 된 ```onCreate()```함수에 위와 같이 구현

    *`view.setOnTouchListener()`에서 Event 객체를 인자로 받는 `onTouch()` 오버라이딩

    *`onTouch()`는 손가락이 눌리거나, 눌린 상태에서 움직이거나, 손가락을 뗐을 때 호출되는 함수로

    호출된 액션이 무엇인지에 따라 동작 지정 가능

    *`GestureDetector`형 변수를 생성하면 움직인 속도와 거리 계산 가능

    *`onKeyDown` 메소드를 오버라이딩 해 시스템 키를  `keyCode`로 받고, 키 별 동작 지정 가능











