package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {
    private String memberId;  // 회원 아이디
    private int money;        // 회원 소유금액

    public Member() {
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
