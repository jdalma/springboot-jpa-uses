package jpabook.jpashop.Controller;

import jpabook.jpashop.domain.item.Book;
import org.hibernate.internal.EntityManagerMessageLogger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemUpdateTest {
    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        // given
        Book book = em.find(Book.class , 1L);

        // when
        book.setName("변경 감지");

        // then
    }
}