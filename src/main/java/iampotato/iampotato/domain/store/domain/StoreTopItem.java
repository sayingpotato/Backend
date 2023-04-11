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
public class StoreTopItem {

    private String firstGradeItem;
    private String secondGradeItem;
    private String thirdGradeItem;

    @ColumnDefault("0")
    private int firstGradeItemNumber;
    @ColumnDefault("0")
    private int secondGradeItemNumber;
    @ColumnDefault("0")
    private int thirdGradeItemNumber;
}
