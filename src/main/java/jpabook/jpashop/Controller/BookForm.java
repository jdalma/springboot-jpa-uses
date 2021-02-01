package jpabook.jpashop.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookForm {

    private Long id;
    @NotEmpty(message = "상품명은 필수입니다.")
    private String name;
    @NotEmpty(message = "가격은 필수입니다.")
    private int price;
    @NotEmpty(message = "재고수량은 필수입니다.")
    private int stockQuantity;

    private String author;
    private String isbn;
}
