package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV1Test {
    MemberRepositoryV1 repository;

    @BeforeEach
    @DisplayName("초기화")
    void beforeEach() throws Exception {
        // @Test를 실행하기전에 실행되는 메소드
        // 대체로 초기화에 사용
        // 기본 DriverManager - 항상 새로운 커넥션 획득
        // DriverManagerDataSource dataSource =
        // new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        // 커넥션 풀링: HikariProxyConnection -> JdbcConnection
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    @DisplayName("crud")
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV500", 10000);
        repository.save(member);

        // findByID
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember = {}", findMember);
        // log.info("member == findMember {}", member == findMember);
        // equals hashcode를 자동으로 만들어줌. 모든 필드를 가지고 equals를 만들어줌
        // log.info("member equals findMember {}", member.equals(findMember));
        // 우리가 찾은 맴버는 == member 객체와 같다.
        assertThat(findMember).isEqualTo(member);

        // update: money: 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

//        if (true){
//            throw new IllegalStateException(".......");
//        }

        // delete
        repository.delete(member.getMemberId());
        // nosuchfileException이 터지니까 데이터가 없으니까 삭제가 성공했네
        // assertThatThrownBy 예외가 터져야 한다.  해당 예외가 발생해야 검증에 성공한다.
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}