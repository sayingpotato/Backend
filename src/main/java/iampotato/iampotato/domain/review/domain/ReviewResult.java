package iampotato.iampotato.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResult {

    private int allNum;

    private int greatCoffeeNum;

    private int greatBeverageNum;

    private int greatFoodNum;

    private int manyOutletNum;
}
