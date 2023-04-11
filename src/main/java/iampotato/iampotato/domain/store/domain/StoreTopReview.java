package iampotato.iampotato.domain.store.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreTopReview {

    private String firstGradeReview;
    private String secondGradeReview;
    private String thirdGradeReview;

    @ColumnDefault("0")
    private int firstGradeReviewNumber;
    @ColumnDefault("0")
    private int secondGradeReviewNumber;
    @ColumnDefault("0")
    private int thirdGradeReviewNumber;
}
