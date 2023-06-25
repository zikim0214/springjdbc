package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;


@Slf4j
class DBConnectionUtilTest {
    @Test
    void Connection() {
        Connection connection = DBConnectionUtil.getConnection();
        /* null이 아니면 성공. (assetj를 쓸 것) */
        assertThat(connection).isNotNull();
    }
}