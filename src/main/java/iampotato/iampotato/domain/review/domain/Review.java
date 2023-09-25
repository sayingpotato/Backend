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

    @Builder.Default
    private ReviewStatus reviewStatus = ReviewStatus.NONE;

    // 여기서부터는 리뷰 내용들 입니다.
    // =============================

    @Builder.Default
    private int greatCoffee = 0;

    @Builder.Default
    private int greatBeverage = 0;

    @Builder.Default
    private int greatFood = 0;

    @Builder.Default
    private int manyOutlet = 0;
    // =============================

    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime modifiedDate;
}
