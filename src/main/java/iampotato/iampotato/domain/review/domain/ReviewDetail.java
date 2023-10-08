package iampotato.iampotato.domain.review.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ReviewDetail {

    GREAT_COFFEE(1, "커피가 맛있어요"),
    GREAT_BEVERAGE(1, "음료가 맛있어요"),
    GREAT_FOOD(1, "음식이 맛있어요"),
    MANY_OUTLET(1, "콘센트가 많아요");

    private static final Map<String, ReviewDetail> REVIEWCONTENT_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ReviewDetail::getContent, Function.identity())));


    private final int count;

    private final String content;

    ReviewDetail(int count, String content) {
        this.count = count;
        this.content = content;
    }

    public static ReviewDetail find(String content) {
        if (REVIEWCONTENT_MAP.containsKey(content)) {
            return REVIEWCONTENT_MAP.get(content);
        }
        throw new IllegalArgumentException("존재하지 않는 리뷰");
    }
}
