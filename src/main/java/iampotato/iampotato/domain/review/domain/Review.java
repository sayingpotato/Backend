package iampotato.iampotato.domain.review.domain;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Review <-> Store = N : 1
 */
@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private ReviewStatus reviewStatus;

    // 여기서부터는 리뷰 내용들 입니다.
    // =============================
    private int greatCoffee;

    private int greatBeverage;

    private int greatFood;

    private int manyOutlet;
    // =============================

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
