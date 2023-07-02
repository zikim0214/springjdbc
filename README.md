## # jdbc 표준 인터페이스

- java.sql.Connection - 연결
- java.sql.Statement - SQL을 담은 내용
- java.sql.ResultSet - SQL 요청 응답

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

## # assertThatThrownBy / assertThrows

1. assertThatThrownBy

AssertJ라는 자바 테스트 라이브러리에서 제공되는 메서드 중 하나입니다. 이 메서드는 예외 발생 여부를 테스트하는 데 사용됩니다.

```java
import static org.assertj.core.api.Assertions.*;

public class assertjTest {

    @Test
    public void testMethod() {
        assertThatThrownBy(() -> {
            // 예외를 발생시키는 동작
        }).isInstanceOf(ExpectedExceptionClass.class)
                .hasMessageContaining("expected message");
    }
}
```

assertThatThrownBy 메서드는 람다 표현식을 인자로 받습니다.
이 람다 표현식은 예외를 발생시키는 동작을 포함하고 있습니다.
예외가 발생하지 않으면 테스트가 실패하게 됩니다.

isInstanceOf 메서드는 예상되는 예외 클래스를 지정하여 예외의 타입을 검증합니다.
hasMessageContaining 메서드는 예상되는 메시지의 일부를 포함하는지를 확인합니다.

assertThatThrownBy 메서드를 사용하면 예외에 대한 자세한 검증을 수행할 수 있으며,
예외 발생 여부뿐만 아니라 예외의 타입 및 메시지까지 확인할 수 있습니다.
이를 통해 테스트 케이스의 완전성과 가독성을 높일 수 있습니다.

1. assertThrows

JUnit 프레임워크에서 제공되는 메서드로,
특정 코드 블록 실행 시 예외가 발생하는지를 테스트하는 데 사용됩니다.

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class junitAssertTest {
    @Test
    public void testMethod() {
        assertThrows(ExpectedExceptionClass.class, () -> {
            // 예외를 발생시키는 동작
        });
    }
}
```

위의 예제에서 assertThrows 메서드는 ExpectedExceptionClass라는 예외 타입을 지정하고,
예외가 발생해야 하는 코드 블록을 람다 표현식으로 전달합니다.
ExpectedExceptionClass 타입의 예외가 발생하지 않으면 테스트가 실패합니다.

assertThrows 메서드는 주로 예외가 발생하는 상황에서 테스트를 작성할 때 사용됩니다.
예외의 발생 여부를 확인하여 코드의 예외 처리 로직이 제대로 동작하는지 검증할 수 있습니다.