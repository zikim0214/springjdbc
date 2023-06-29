package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
        //  DriverManagerDataSource dataSource =
        //  new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        // 커넥션 풀링: HikariProxyConnection -> JdbcConnection

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

    //DriverManager 를 사용하다가 DataSource 기반의 커넥션 풀을 사용하도록 변경하면
    //관련 코드를 다 고쳐야 한다. 이런 문제를 해결하기 위해 스프링은 DriverManager 도 DataSource 를 통
    //해서 사용할 수 있도록 DriverManagerDataSource 라는 DataSource 를 구현한 클래스를 제공한다.
    //DriverManagerDataSource 를 통해서
    //DriverManager 를 사용하다가 커넥션 풀을 사용하도록 코드를 변경해도 애플리케이션 로직은 변경하지 않아도 된다

    @Test
    @DisplayName("crud맴버")
    void crud() throws SQLException {

        log.info("start");

        //save
        Member member = new Member("memberV500", 10000);
        repository.save(member);

        //findById
        Member memberById = repository.findById(member.getMemberId());
        assertThat(memberById).isNotNull();
        log.info("findMember = {}", memberById);

        //update: money: 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}