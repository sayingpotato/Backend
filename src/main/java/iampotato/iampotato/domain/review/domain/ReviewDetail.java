package iampotato.iampotato.domain.review.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ReviewDetail {

    GREAT_COFFEE(1, "☕ 커피가 맛있어요"),
    GREAT_BEVERAGE(1, "\uD83C\uDF79 음료가 맛있어요"),
    GREAT_FOOD(1, "\uD83E\uDD58 음식이 맛있어요"),
    MANY_OUTLET(1, "\uD83D\uDD0C 콘센트가 많아요"),
    GOOD_MOOD(1, "\uD83E\uDD29 분위기가 좋아요"),
    GOOD_PRICE(1, "\uD83D\uDCB8 가성비가 좋아요"),
    GOOD_WIFI(1, "\uD83D\uDCF6 와이파이가 잘 터져요"),
    GOOD_DESERT(1, "\uD83E\uDDC1 디저트가 맛있어요"),
    QUIET_MOOD(1, "\uD83E\uDD2B 조용해요"),
    CLEAN_TOILET(1, "\uD83D\uDEBD 화장실이 깨끗해요"),
    GOOD_SERVICE(1, "\uD83C\uDD93 서비스를 많이 줘요"),
    GOOD_KIND(1, "\uD83D\uDE07 친절해요");

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
