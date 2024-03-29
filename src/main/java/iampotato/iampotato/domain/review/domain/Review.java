package iampotato.iampotato.domain.review.domain;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus = ReviewStatus.NONE;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "review_details", joinColumns = @JoinColumn(name = "review_id"))
    @Enumerated(EnumType.STRING)
    List<ReviewDetail> reviewDetails = new ArrayList<>();

    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime modifiedDate;

    public int getReviewDetailCount(ReviewDetail reviewDetail) {

        return reviewDetails.stream()
                .filter(rd -> rd == reviewDetail)
                .findAny()
                .map(ReviewDetail::getCount)
                .orElse(0);
    }

    public String getReviewDetailContent(ReviewDetail reviewDetail) {

        return reviewDetails.stream()
                .filter(rd -> rd == reviewDetail)
                .findAny()
                .map(ReviewDetail::getContent)
                .orElse(reviewDetail.getContent());
    }

    public void addReviewDetail(ReviewDetail reviewDetail) {
        this.reviewDetails.add(reviewDetail);
    }

    public void reviewContent() {
        this.reviewDetails.clear();
        this.reviewStatus = ReviewStatus.REVIEWING;
    }

    public void checkReview() {
        if (reviewDetails.size() == 0) {
            reviewStatus = ReviewStatus.NONE;
        }
    }
}
