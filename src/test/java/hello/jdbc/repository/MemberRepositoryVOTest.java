package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


@Slf4j
class MemberRepositoryVOTest {

    MemberRepositoryVO repository = new MemberRepositoryVO();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV2", 10000);
        repository.save(member);

        // findByID
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember = {}", findMember);
        log.info("member == findMember {}", member == findMember);
        // equals hashcode를 자동으로 만들어줌. 모든 필드를 가지고 equals를 만들어줌
        // log.info("member equals findMember {}", member.equals(findMember));
        // 우리가 찾은 맴버는 == member 객체와 같다.
        assertThat(findMember).isEqualTo(member);
    }

}