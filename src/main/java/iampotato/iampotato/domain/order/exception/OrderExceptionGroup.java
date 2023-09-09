package iampotato.iampotato.domain.order.exception;

import lombok.Getter;

@Getter
public enum OrderExceptionGroup {

    ORDER_NULL(400, "O001", "없는 주문입니다.");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    OrderExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
