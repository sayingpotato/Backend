package iampotato.iampotato.domain.customer.exception;

import lombok.Getter;

@Getter
public enum CustomerExceptionGroup {

    CUSTOMER_ID_NULL(400, "C001", "고객이 없습니다."),
    CUSTOMER_PASSWORD_WRONG(400, "C002", "고객 비밀번호가 잘못되었습니다."),
    CUSTOMER_DUPLICATED_ID(400, "C003", "고객 아이디가 중복되었습니다."),
    CUSTOMER_DUPLICATED_NICKNAME(400, "C004", "고객 닉네임이 중복되었습니다."),
    CUSTOMER_IMAGE_NULL(400, "C005", "고객 학생증 이미지가 비어있습니다."),
    CUSTOMER_IMAGE_EXTENSION_NULL(400, "C006", "고객 학생증 이미지의 확장자가 없습니다."),
    CUSTOMER_IMAGE_EXTENSION_WRONG(400, "C007", "고객 학생증 이미지의 확장자가 지원하지 않는 형식입니다.");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    CustomerExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
