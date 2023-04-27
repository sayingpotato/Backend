package iampotato.iampotato.domain.discount.domain;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Discount <-> Store = N : 1
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Discount {

    @Id @GeneratedValue
    @Column(name = "discount_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private int people;

    private int discountRatio;

    @Enumerated(EnumType.STRING)
    private DiscountDay discountDay; // 할인하는 요일

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
