package iampotato.iampotato.domain.store.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreTopReview {

    private String firstGradeReviewName;
    private String secondGradeReviewName;
    private String thirdGradeReviewName;

    @ColumnDefault("0")
    private int firstGradeReviewNumber;
    @ColumnDefault("0")
    private int secondGradeReviewNumber;
    @ColumnDefault("0")
    private int thirdGradeReviewNumber;
}
