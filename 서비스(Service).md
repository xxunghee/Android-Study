# 서비스(Service)

- 특징

  - 백그라운드에서 오래 실행되는 작업을 수행할 수 있는 어플리케이션 구성 요소

  - 사용자 인터페이스(UI)를 제공하지 않음

  - 실행 중인 프로세스가 종료되어도 백그라운드에서 실행 중

  - ```Intent``` 사용

  - 시스템의 구성 요소가 ```startService()```로 서비스를 시작하면,

    ```stopSelf()```로 스스로 중단하거나 ```stopService()```로 서비스 중단 전까지 실행

  - 시스템의 다른 구성 요소에 바인딩 된 서비스의 경우에는 바인딩 된 동안만 실행



- 유형

  - 포그라운드(Foreground)

    - 사용자에게 잘 보이는 몇몇 작업 수행

    - 알림표시

  - 백그라운드(Background)

    - 사용자에게 직접 보이지 않는 작업 수행

  - 바인드(Bind)

    - 어플리케이션 구성 요소의 ```bindService()``` 호출로 해당 서비스에 바인딩
    - 구성요소-서비스 간 상호작용 

    

- 서비스 생성

1. 서비스 생성

   - Service의 하위 클래스 생성

   - 콜백 메소드 오버라이딩

     ```java
     public void onCreate(); 		 // 서비스 생성
     public void onStartCommand(); 	 // 서비스 시작
     public IBinder onBind(); 	 	 // 다른 구성 요소와 바인딩
     public void onDestroy(); 	 	 // 서비스 소멸
     ```

   

2. 서비스 시작

   1) 시작할 서비스를 ```Intent``` 생성으로 지정하여 ```startService```에 전달

   2) 안드로이드 시스템이 서비스의 ```onStartCommand()```호출하고 ```Intent```전달

     ** 서비스 시작 요청 수만큼 ```onStartCommand()```호출됨*

   

3.  서비스 중단

   - 시스템이 서비스를 중단하거나 소멸시키지 않기 때문에 시작된 서비스는 자신의 수명 주기를 직접 관리

   - 중지 메소드

     ```java
     public final void stopSelf(); // 스스로 중단
     public abstract boolean stopService (Intent service); // 다른 구성 요소가 중단
     ```

   

- 서비스 생명 주기

  - 시작된 서비스
    - ```startService()``` -> ```onCreate()``` -> ```onStartCommand()``` -> Running -> ```stopSelf()``` | ```stopService()``` -> ```onDestroy()```
  - 바인딩된 서비스
    - ```bindService()``` ->  ```onCreate()``` -> ```onBind()``` -> Running -> ```onUnbind()``` -> ```onDestroy()```
      <img src="https://user-images.githubusercontent.com/50495214/103865960-51450c80-5108-11eb-8a78-89dcbb455ca4.png">


