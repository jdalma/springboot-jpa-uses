package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // @Enumerated(EnumType.ORDINAL) ORDINAL은 숫자로 들어가니 꼭 STRING으로만 쓰자
    // Enum이 추가되어 숫자가 밀릴 가능성이 있다.
    private DeliveryStatus status; // READY , COMP
}
