package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryVO {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection con = null; // 커넥션이 있어야 연결 가능
        PreparedStatement pstmt = null; // 이걸로 database에 쿼리를 날림
        /*바인딩 기능이 추가된 것일 뿐 그것을 제외하고 statement클래스와는 기능이 동일 상속받은 것이다.*/
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId()); // 파라미터 바인딩 쿼리 ? 부분
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate(); // 쿼리 실행 (숫자를 반환 >> 영향받은 row수)
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e; // 로그정도 남기고 exception 다시 밖으로 던지기 (throws SQLException)
        } finally {
            /*
            * 시작과 역순으로 close처리
            * 항상 보장되도록 finally로
            */
            close(con, pstmt, null);
        }

    }

    /**
     *
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e); // 뭘 할 수있는게 없어서 단순 로그로만 남긴다.
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e); // 뭘 할 수있는게 없어서 단순 로그로만 남긴다.
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e); // 뭘 할 수있는게 없어서 단순 로그로만 남긴다.
            }
        }
    }

    /**
     * connection 갖고오는 함수
     */
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
