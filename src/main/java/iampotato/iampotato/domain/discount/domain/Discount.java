package iampotato.iampotato.domain.discount.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Discount {

    @Id @GeneratedValue
    @Column(name = "discount_id")
    private Long id;

    private int people;

    private int discountRatio;

    private String day; // 할인하는 요일

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
