package iampotato.iampotato.domain.store.exception;

import lombok.Getter;

@Getter
public enum StoreExceptionGroup {

    STORE_NULL(400, "S001", "가게가 없습니다.");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    StoreExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
