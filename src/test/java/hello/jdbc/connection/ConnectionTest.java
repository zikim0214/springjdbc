package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;


/**
 * DriverManager 를 사용해서 커넥션을 획득하다가 HikariCP 같은
 * 커넥션 풀을 사용하도록 변경하면 커넥션을 획득하는 애플리케이션 코드도 함께 변경해야 한다
 * <p>
 * 자바에서는 이런 문제를 해결하기 위해 javax.sql.DataSource 라는 인터페이스를 제공한다.
 * DataSource 는 커넥션을 획득하는 방법을 추상화 하는 인터페이스이다.
 */
@Slf4j
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ConnectionTest {

    @Test
    @DisplayName("DriverManager 적용")
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    @DisplayName("DataSource 적용")
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource - 항상 새로운 커넥션 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,
                USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        Connection con3 = dataSource.getConnection();
        Connection con4 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
        log.info("connection={}, class={}", con3, con3.getClass());
        log.info("connection={}, class={}", con4, con4.getClass());
    }

    @Test
    @DisplayName("HikariDataSource 적용")
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target)
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);// pool 최대 사이즈
        dataSource.setPoolName("MyPool"); // pool 이름 지정

        useDataSource(dataSource);
        Thread.sleep(1000); //커넥션 풀에서 커넥션 생성 시간 대기. 쓰레드 풀에 커넥션이 생성되는 로그를 확인하기 위해
    }
}