package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {
    /* jdbc가 표준 인터페이스가 제공해주는 Connection */
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            // connection.getClass() : 클래스 정보 출력
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
