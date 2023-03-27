package iampotato.iampotato.domain.review.domain;

import iampotato.iampotato.domain.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;


    // 여기서부터는 리뷰 내용들 입니다.
    // =============================
    private int greatCoffee;

    private int greatBeverage;

    private int manyOutlet;
    // =============================

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
