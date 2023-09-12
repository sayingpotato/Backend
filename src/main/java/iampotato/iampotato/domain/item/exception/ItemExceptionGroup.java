package iampotato.iampotato.domain.item.exception;

import lombok.Getter;

@Getter
public enum ItemExceptionGroup {

    ITEM_NULL(400, "I001", "아이템이 없습니다.");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    ItemExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
