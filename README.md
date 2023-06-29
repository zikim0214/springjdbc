## # jdbc 원리

## # SQL Mapper vs ORM 기술

## # @BeforeEach / @AfterEach

1. @BeforeEach

각각의 테스트 메서드가 실행되기 전에 실행되어야 하는 코드 블록을 정의합니다. 
이를 통해 테스트 메서드를 실행하기 전에 초기화 작업이나 설정 작업을 수행할 수 있습니다.

2. @AfterEach

각각의 테스트 메서드가 실행된 후에 실행되어야 하는 코드 블록을 정의합니다. 
이를 통해 테스트 메서드 실행 이후에 정리 작업이나 리소스 해제와 같은 마무리 작업을 수행할 수 있습니다.


```java
public class DatabaseTest {

    @BeforeEach
    public void setUp() {
        // 데이터베이스 연결 설정
    }

    @AfterEach
    public void tearDown() {
        // 데이터베이스 연결 해제
    }

    @Test
    public void testDatabaseOperation1() {
        // 데이터베이스 작업 테스트 1
    }

    @Test
    public void testDatabaseOperation2() {
        // 데이터베이스 작업 테스트 2
    }
}
```