package iampotato.iampotato.global.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 해당 예외는 @Valid 사용시 발생하는 예외에 대해 설정해주는곳입니다.
 * 만약 @Valid 를 사용해서 없는 예외라면 여기에 추가해주면 됩니다.
 */
@Getter
public enum ValidExceptionGroup {

    NOT_NULL("NotNull",400,"G001", "값이 비어있을 수 없습니다."),
    MIN_VALUE("Min",400,"G002", "해당 값을 최소값보다 작습니다."),
    TYPE_MISMATCH("typeMismatch",400,"G003", "요청한 타입은 존재하지 않습니다."),
    NONE("", 400, "G999", "설정되지 않은 에러");

    private final String validName;
    private final int httpCode;
    private final String errorCode;
    private final String message;

    private static final Map<String, ValidExceptionGroup> cashMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ValidExceptionGroup::getValidName, Function.identity())));

    ValidExceptionGroup(String validName, int httpCode, String errorCode, String message) {
        this.validName = validName;
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ValidExceptionGroup findValidError(String name) {
        return Optional.ofNullable(cashMap.get(name)).orElse(NONE);
    }

//    public ValidExceptionGroup findValidError(String name) {
//        return Arrays.stream(values())
//                .filter(ex -> ex.getValidName().equals(name))
//                .findAny()
//                .orElse(NONE);
//    }
}
