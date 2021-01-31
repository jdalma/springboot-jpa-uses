package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false)
    // 테스트에서 Transactional은 Rollback 되기 때문에 Rollback을 false로 하여 commit되게 한다.
    public void 회원가입 () throws Exception {
        // given
        Member member = new Member();
        member.setName("jeong");

        // when
        Long saveId = memberService.join(member);

        // then
        //em.flush(); // Transactionl 떄문에 Rollback 되어야 하지만 강제로 반영
        Assert.assertEquals(member , memberService.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member = new Member();
        member.setName("jeong1");

        Member member2 = new Member();
        member2.setName("jeong1");
        // when
        memberService.join(member);

        // then
        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class ,() -> memberService.join(member2));
        Assertions.assertEquals("이미 존재하는 회원입니다." , exception.getMessage());
    }
}