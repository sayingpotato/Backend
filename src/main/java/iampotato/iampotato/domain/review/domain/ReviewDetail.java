package iampotato.iampotato.domain.review.domain;

import lombok.Getter;

@Getter
public enum ReviewDetail {

    GREAT_COFFEE(1, "커피가 맛있어요"),
    GREAT_BEVERAGE(1, "음료가 맛있어요"),
    GREAT_FOOD(1, "음식이 맛있어요"),
    MANY_OUTLET(1, "콘센트가 많아요");

    private int count;

    private String content;

    ReviewDetail(int count, String content) {
        this.count = count;
        this.content = content;
    }
}
