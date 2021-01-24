package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.hibernate.boot.TempTableDdlTransactionHandling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    // EntityManager를 통한 모든 데이터 변경은 Transaction안에서 실행 되어야 한다.
    // Test코드 안에서 @Transaction을 작성하면 마지막에 롤백 한다.
    @Rollback(false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long aLong = memberRepository.save(member);
        Member findMember = memberRepository.find(aLong);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(aLong);
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // == 비교
        // [영속성 컨텍스트]
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
        // 출력
        // findMember = jpabook.jpashop.Member@6c2fea95
        // member = jpabook.jpashop.Member@6c2fea95
    }
}