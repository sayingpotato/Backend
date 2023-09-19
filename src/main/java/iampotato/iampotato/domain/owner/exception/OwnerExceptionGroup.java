package iampotato.iampotato.domain.owner.exception;

import lombok.Getter;

@Getter
public enum OwnerExceptionGroup {

    OWNER_DUPLICATION(400, "OW001", "이미 가입된 점주입니다."),

    OWNER_NULL(400, "OW002", "인증을 요청한 점주가 없습니다");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    OwnerExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
