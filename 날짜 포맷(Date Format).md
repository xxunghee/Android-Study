# 날짜 포맷

- 서버와의 통신 또는 기기 정보를 통해 시간을 가져올 때 원하는 날짜 형식으로 포맷한다.

  <br>

- 받아온 날짜 형식

  ``` java
  yyyyMMddTHHmmss // ex. 2021-02-17T11:08:47
  ```

- 원하는 날짜 형식

  ```java
  yyyyMMdd // ex. 2021-02-17
  ```

  <br>

- 구현

  - 모듈 import

    ```java
    import java.util.Date;
    import java.text.SimpleDateFormat;
  ```
  
  <br>
  
  - `String`형 날짜 포맷
  
    ```java
    
    String dateWithHours;  // String형 포맷 전 날짜 (ex. 2021-02-17T11:08:47)
    Date dateHours = null; // Date형 포맷 전 날짜
    String eventDate;      // 포맷 후 날짜 (ex. 2021-02-17)
    
    dateWithHours = "2021-02-17T11:08:47"; // 서버 등에서 받아온 날짜로 초기화 또는 직접 입력
    
    //=== String -> Date 파싱 ===
    SimpleDateFormat yyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try { // 날짜가 다른 형식일 경우를 대비한 try-catch 문
        dateHours = yyMMddHHmmss.parse(dateWithHours);
    } catch (ParseException e) {
    	e.printStackTrace();
  }
    
    //=== Date -> String 포맷 ===
  SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    eventDate = yyyyMMdd.format(dateHours);
    
    ```
    
    <br>
    
  - `Date`형 날짜 포맷
  
    ```java
    // 시스템에서 날짜를 받아온 경우
    
    Date curDate = new Date(System.currentTimeMillis()); // 포맷 전 날짜 (ex. 2021-02-17 11:08:47)
    String date; // 포맷 후 날짜 (ex. 2021-02-17)
    
    SimpleDateFormat yyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    date = yyMMdd.format(curDate);
    
    ```
  
    
