package iampotato.iampotato.domain.review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Reviews(Review... reviews) {
        this.reviews = List.of(reviews);
    }

    public int countGreatCoffee() {
        return reviews.stream()
                .mapToInt(Review::getGreatCoffee)
                .sum();
    }

    public int countGreatBeverage() {
        return reviews.stream()
                .mapToInt(Review::getGreatBeverage)
                .sum();
    }

    public int countGreatFood() {
        return reviews.stream()
                .mapToInt(Review::getGreatFood)
                .sum();
    }

    public int countManyOutlet() {
        return reviews.stream()
                .mapToInt(Review::getManyOutlet)
                .sum();
    }

    public int countAllOfCafe() {
        return countGreatBeverage() +
                countGreatFood() +
                countGreatCoffee() +
                countManyOutlet();
    }

    public ReviewResult getReviewResultOfCafe() {
        return ReviewResult.builder()
                .greatBeverageNum(countGreatBeverage())
                .greatCoffeeNum(countGreatCoffee())
                .greatFoodNum(countGreatFood())
                .manyOutletNum(countManyOutlet())
                .allNum(countAllOfCafe())
                .build();
    }
}
